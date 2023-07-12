package com.eva.androidweatherapp

import android.app.Application
import com.eva.androidweatherapp.di.appModule
import com.eva.androidweatherapp.workers.DataBaseSyncWorker
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(appModule)
        }

        DataBaseSyncWorker.start(applicationContext)
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}