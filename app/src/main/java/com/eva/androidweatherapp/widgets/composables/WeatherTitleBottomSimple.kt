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
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance

@Composable
@GlanceComposable
fun WeatherTileBottomSimple(
    modifier: GlanceModifier = GlanceModifier,
    model: WidgetWeatherModel,
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
            data = "${model.precipitationMM} mm",
            modifier = GlanceModifier.defaultWeight()
        )
        WeatherBasicInformation(
            image = R.drawable.ic_humidity,
            data = "${model.humidity} %",
            modifier = GlanceModifier.defaultWeight()
        )
        WeatherBasicInformation(
            image = R.drawable.ic_wind_speed,
            data = if (isAmerican) "${model.windSpeedInMh} kph" else "${model.windSpeedInKmh} kph",
            modifier = GlanceModifier.defaultWeight()
        )
    }
}