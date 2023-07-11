package com.eva.androidweatherapp.widgets

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.eva.androidweatherapp.workers.WidgetRefreshWorker
import com.eva.androidweatherapp.workers.WidgetSyncWorker

class WeatherAppWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WeatherAppWidget

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        WidgetSyncWorker.start(context = context)
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        WidgetRefreshWorker.cancel(context)
        WidgetSyncWorker.cancel(context)
        super.onDeleted(context, appWidgetIds)
    }
}