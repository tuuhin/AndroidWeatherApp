package com.eva.androidweatherapp.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import androidx.glance.state.GlanceStateDefinition
import androidx.work.ExistingWorkPolicy
import com.eva.androidweatherapp.presentation.MainActivity
import com.eva.androidweatherapp.widgets.composables.LoadingLayout
import com.eva.androidweatherapp.widgets.composables.NoLocationError
import com.eva.androidweatherapp.widgets.composables.WeatherErrorLayout
import com.eva.androidweatherapp.widgets.composables.WeatherWidgetLayouts
import com.eva.androidweatherapp.widgets.utils.roundedCorners
import com.eva.androidweatherapp.widgets.data.SerializedResource
import com.eva.androidweatherapp.widgets.data.toModel
import com.eva.androidweatherapp.widgets.theme.WeatherAppWidgetTheme
import com.eva.androidweatherapp.widgets.utils.AvailableSizes
import com.eva.androidweatherapp.workers.WidgetRefreshWorker

object WeatherAppWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode
        get() = SizeMode.Responsive(AvailableSizes.sizes)

    override val stateDefinition: GlanceStateDefinition<SerializedResource>
        get() = WeatherDataDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            val state = currentState<SerializedResource>()

            WeatherAppWidgetTheme {
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .roundedCorners(color = GlanceTheme.colors.surface)
                        .appWidgetBackground(),
                    contentAlignment = Alignment.Center
                ) {
                    when (state) {
                        is SerializedResource.Error -> WeatherErrorLayout(
                            error = state.message,
                            action = actionRunCallback<RefreshAction>()
                        )

                        is SerializedResource.IsLoading -> LoadingLayout()

                        is SerializedResource.Success ->
                            WeatherWidgetLayouts(
                                model = state.data.toModel(),
                                modifier = GlanceModifier
                                    .clickable(actionStartActivity<MainActivity>())
                            )

                        is SerializedResource.NoLocationFound -> NoLocationError(
                            action = actionRunCallback<RefreshAction>(),
                            modifier = GlanceModifier
                                .clickable(actionStartActivity<MainActivity>())
                        )
                    }
                }
            }
        }
    }
}

class RefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        updateAppWidgetState(context, WeatherDataDefinition, glanceId) {
            SerializedResource.IsLoading
        }

        WeatherAppWidget.update(context, glanceId)
        WidgetRefreshWorker.start(context, policy = ExistingWorkPolicy.REPLACE)
    }
}
