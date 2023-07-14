package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

@Composable
@GlanceComposable
fun WeatherExtraInfo(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier
) {
    Row(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = GlanceModifier.defaultWeight()
        ) {
            WeatherBasicInformation(
                image = R.drawable.ic_rain,
                data = "${model.precipitationMM} mm",
                title = "Rain",
                modifier = GlanceModifier.defaultWeight()
            )
            WeatherBasicInformation(
                image = R.drawable.ic_humidity,
                data = "${model.humidity} %",
                title = "Humidity",
                modifier = GlanceModifier.defaultWeight()
            )
        }
        Spacer(modifier = GlanceModifier.defaultWeight())
        Column(
            modifier = GlanceModifier.defaultWeight()
        ) {
            WeatherBasicInformation(
                image = R.drawable.ic_wind_speed,
                data = "${model.windSpeedInKmh} kmph",
                title = "Wind",
                modifier = GlanceModifier.defaultWeight()
            )

            WeatherBasicInformation(
                image = R.drawable.ic_pressure,
                data = "${model.pressureMilliBar} mbar",
                title = "Pressure",
                modifier = GlanceModifier.defaultWeight()
            )
        }
    }
}