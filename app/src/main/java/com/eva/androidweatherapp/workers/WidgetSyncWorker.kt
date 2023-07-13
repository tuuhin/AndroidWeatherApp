package com.eva.androidweatherapp.workers

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.eva.androidweatherapp.widgets.utils.UpdateWidgetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration

class WidgetSyncWorker(
    private val context: Context,
    workerParameters: WorkerParameters,
) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) { UpdateWidgetWork.doWork(context) }

    companion object {

        private const val periodicSync = "PeriodicSyncWorker"

        private val periodicConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()


        private val startPeriodicSync =
            PeriodicWorkRequestBuilder<WidgetSyncWorker>(repeatInterval = Duration.ofHours(2))
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofMinutes(10))
                .setInitialDelay(Duration.ofSeconds(10))
                .setConstraints(periodicConstraints)
                .build()

        fun start(
            context: Context,
            policy: ExistingPeriodicWorkPolicy = ExistingPeriodicWorkPolicy.KEEP
        ) {
            val manager = WorkManager.getInstance(context)
            manager.enqueueUniquePeriodicWork(periodicSync, policy, startPeriodicSync)
        }


        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(periodicSync)
        }

    }
}