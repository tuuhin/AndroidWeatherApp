package com.eva.androidweatherapp.workers

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.eva.androidweatherapp.widgets.utils.UpdateWidgetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Duration

class WidgetRefreshWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) { UpdateWidgetWork.doWork(context) }

    companion object {

        private const val oneTimeSync = "RefreshWidgetWorker"

        private val constraints =
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        private val oneTimeWorkRequest = OneTimeWorkRequestBuilder<WidgetSyncWorker>()
            .setInitialDelay(Duration.ofSeconds(5))
            .setConstraints(constraints)
            .setBackoffCriteria(BackoffPolicy.LINEAR, Duration.ofSeconds(10))
            .build()

        fun start(context: Context, policy: ExistingWorkPolicy = ExistingWorkPolicy.REPLACE) {
            val manager = WorkManager.getInstance(context)
            manager.enqueueUniqueWork(oneTimeSync, policy, oneTimeWorkRequest)
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(oneTimeSync)

        }
    }
}