package com.eva.androidweatherapp.widgets

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalSize
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.fillMaxSize
import com.eva.androidweatherapp.domain.models.CurrentWeatherForecastBasicModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.widgets.composables.WeatherTileExtraSmall
import com.eva.androidweatherapp.widgets.composables.WeatherTileMedium
import com.eva.androidweatherapp.widgets.composables.WeatherTileSmall
import com.eva.androidweatherapp.widgets.theme.WeatherAppWidgetTheme
import com.eva.androidweatherapp.widgets.utils.AvailableSizes

object WeatherAppWidget : GlanceAppWidget() {

    override val sizeMode: SizeMode
        get() = SizeMode.Responsive(
            setOf(
                AvailableSizes.WIDGET_SIZE_SMALL_RECT,
                AvailableSizes.WIDGET_SIZE_SMALL,
                AvailableSizes.WIDGET_SIZE_MEDIUM,
                AvailableSizes.WIDGET_SIZE_LARGE
            )
        )

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // suspend call can be made here
        provideContent {
            val size = LocalSize.current
            WeatherAppWidgetTheme {
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(GlanceTheme.colors.surfaceVariant)
                        .appWidgetBackground(),
                    contentAlignment = Alignment.Center
                ) {
                    when (size) {
                        AvailableSizes.WIDGET_SIZE_SMALL_RECT -> WeatherTileExtraSmall(
                            model = CurrentWeatherForecastBasicModel(
                                currentWeatherModel = PreviewFakeData.fakeCurrentWeatherModel,
                                searchedLocationModel = PreviewFakeData.fakeSearchedLocationModel
                            ), action = actionRunCallback<BasicAction>()
                        )

                        AvailableSizes.WIDGET_SIZE_SMALL -> WeatherTileSmall(
                            model = CurrentWeatherForecastBasicModel(
                                currentWeatherModel = PreviewFakeData.fakeCurrentWeatherModel,
                                searchedLocationModel = PreviewFakeData.fakeSearchedLocationModel
                            ), action = actionRunCallback<BasicAction>()
                        )

                        else -> WeatherTileMedium(
                            model = CurrentWeatherForecastBasicModel(
                                currentWeatherModel = PreviewFakeData.fakeCurrentWeatherModel,
                                searchedLocationModel = PreviewFakeData.fakeSearchedLocationModel
                            )
                        )
                    }

                }
            }
        }
    }
}

class BasicAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        WeatherAppWidget.update(context, glanceId)
    }

}