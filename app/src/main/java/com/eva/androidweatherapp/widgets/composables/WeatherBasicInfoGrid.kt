package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.lazy.GridCells
import androidx.glance.appwidget.lazy.LazyVerticalGrid
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel

@Composable
@GlanceComposable
fun WeatherBasicInfoGrid(
    modifier: GlanceModifier = GlanceModifier,
    model: CurrentWeatherBasicModel
) {

    LazyVerticalGrid(gridCells = GridCells.Fixed(2), modifier = modifier) {
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_precipitation,
                data = "${model.currentWeather.precipitationMM}",
                unit = "mm"
            )
        }
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_humidity,
                data = "${model.currentWeather.humidity}",
                unit = "%"
            )
        }
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_wind_speed,
                data = "${model.currentWeather.windSpeedInKmh}",
                unit = "km/h"
            )
        }
        item {
            WeatherBasicInformation(
                image = R.drawable.ic_pressure,
                data = "${model.currentWeather.pressureMilliBar}",
                unit = "mBar"
            )
        }
    }
}