package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherForecastBasicModel

@Composable
fun WeatherTileBottomSimple(
    modifier: GlanceModifier = GlanceModifier,
    model: CurrentWeatherForecastBasicModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(GlanceTheme.colors.primaryContainer)
            .cornerRadius(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherDataBlock(
            image = R.drawable.ic_precipitation,
            data = "${model.currentWeatherModel.precipitationMM}",
            unit = "mm",
            modifier = GlanceModifier.defaultWeight()
        )
        WeatherDataBlock(
            image = R.drawable.ic_humidity,
            data = "${model.currentWeatherModel.humidity}",
            unit = "%",
            modifier = GlanceModifier.defaultWeight()
        )
        WeatherDataBlock(
            image = R.drawable.ic_wind_speed,
            data = "${model.currentWeatherModel.windSpeedInKmh}",
            unit = "kmph",
            modifier = GlanceModifier.defaultWeight()
        )
    }
}