package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.GridCells
import androidx.glance.appwidget.lazy.LazyVerticalGrid
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

@Composable
@GlanceComposable
fun WeatherBasicInfoGrid(
    modifier: GlanceModifier = GlanceModifier,
    model: WidgetWeatherModel,
) {
    LazyVerticalGrid(
        gridCells = GridCells.Fixed(2),
        modifier = modifier
    ) {
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_precipitation,
                data = "${model.precipitationMM}",
                unit = "mm"
            )
        }
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_humidity,
                data = "${model.humidity}",
                unit = "%"
            )
        }
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_wind_speed,
                data = "${model.windSpeedInKmh}",
                unit = "km/h"
            )
        }
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_pressure,
                data = "${model.pressureMilliBar}",
                unit = "mBar"
            )
        }
    }
}