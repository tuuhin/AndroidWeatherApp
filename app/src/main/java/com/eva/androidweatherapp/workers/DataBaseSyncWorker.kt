package com.eva.androidweatherapp.workers

import android.content.Context
import android.util.Log
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.eva.androidweatherapp.data.local.AppDataBase
import com.eva.androidweatherapp.data.mappers.toDbModel
import com.eva.androidweatherapp.data.mappers.toEntity
import com.eva.androidweatherapp.data.remote.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException
import java.sql.SQLException
import java.time.Duration

class DataBaseSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params), KoinComponent {

    private val dataBase: AppDataBase by inject()
    private val weatherApi: WeatherApi by inject()

    private val outKey = "MESSAGE_KEY"

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            try {
                Log.d(outKey, "STARTED WORKER")
                val models = dataBase.dao.getSavedWeatherEntitiesAsList()
                    .associateBy { it.id }
                    .map { (key, location) ->
                        async {
                            weatherApi.getCurrentData(query = location.name)
                                .toDbModel(key)
                        }
                    }.awaitAll()

                models
                    .map { model -> async { dataBase.dao.upsertWeatherEntity(model.toEntity()) } }
                    .awaitAll()
                Log.d(outKey, "DONE JOB")
                Result.success(workDataOf(outKey to "Completed Successfully"))
            } catch (e: SQLException) {
                Result.failure(workDataOf(outKey to e.message))
            } catch (e: HttpException) {
                Result.failure(workDataOf(outKey to e.message))
            } catch (e: Exception) {
                Result.failure(workDataOf(outKey to e.message))
            }
        }
    }

    companion object {

        private const val uniqueWorker = "DATABASE_SYNCER"

        private val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        private val instance = PeriodicWorkRequestBuilder<DataBaseSyncWorker>(
            repeatInterval = Duration.ofHours(12)
        )
            .setInitialDelay(Duration.ofSeconds(30))
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(10))
            .setConstraints(constraints)
            .build()

        fun start(
            context: Context,
            policy: ExistingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP
        ) {
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(uniqueWorker, policy, instance)
        }

        fun stop(context: Context) {
            WorkManager.getInstance(context)
                .cancelUniqueWork(uniqueWorker)
        }
    }
}


