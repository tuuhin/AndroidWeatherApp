package com.eva.androidweatherapp.widgets.composables

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance

@Composable
@GlanceComposable
fun WeatherTileBottomSimple(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
    isAmerican: Boolean = isCurrentLocalAmericanGlance(),
) {
    val background = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        GlanceModifier
            .background(GlanceTheme.colors.primaryContainer)
            .cornerRadius(10.dp)
    else GlanceModifier.background(ImageProvider(R.drawable.shape_rounded_container))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .then(background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally
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
        WeatherBasicInformation(
            image = R.drawable.ic_wind_speed,
            value = if (isAmerican) "${model.windSpeedInMh}" else "${model.windSpeedInKmh}",
            units = if (isAmerican) WeatherUnits.SPEED_KMH else WeatherUnits.SPEED_MPH,
            modifier = GlanceModifier.defaultWeight()
        )
    }
}