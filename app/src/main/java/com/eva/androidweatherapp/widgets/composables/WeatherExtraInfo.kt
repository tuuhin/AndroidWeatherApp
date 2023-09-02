package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance

@Composable
@GlanceComposable
fun WeatherExtraInfo(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
    isAmerican: Boolean = isCurrentLocalAmericanGlance()
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
                value = if (isAmerican) "${model.precipitationInch}" else "${model.precipitationMM}",
                units = if (isAmerican) WeatherUnits.PRECIPITATION_IN_INCH else WeatherUnits.PRECIPITATION_IN_MM,
                modifier = GlanceModifier.defaultWeight()
            )
            WeatherBasicInformation(
                image = R.drawable.ic_humidity,
                value = "${model.humidity}",
                units = WeatherUnits.HUMIDITY_PERCENTAGE,
                modifier = GlanceModifier.defaultWeight()
            )
        }
        Spacer(modifier = GlanceModifier.defaultWeight())
        Column(
            modifier = GlanceModifier.defaultWeight()
        ) {
            WeatherBasicInformation(
                image = R.drawable.ic_wind_speed,
                value = if (isAmerican) "${model.windSpeedInMh}" else "${model.windSpeedInKmh}",
                units = if (isAmerican) WeatherUnits.SPEED_KMH else WeatherUnits.SPEED_MPH,
                modifier = GlanceModifier.defaultWeight()
            )

            WeatherBasicInformation(
                image = R.drawable.ic_pressure,
                value = "${model.pressureMilliBar}",
                units = WeatherUnits.PRESSURE_MILLI_BAR,
                title = "Pressure",
                modifier = GlanceModifier.defaultWeight()
            )
        }
    }
}