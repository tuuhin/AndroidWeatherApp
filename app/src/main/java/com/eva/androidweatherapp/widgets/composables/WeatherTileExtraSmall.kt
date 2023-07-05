package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel

@Composable
@GlanceComposable
fun WeatherTileExtraSmall(
    model: CurrentWeatherBasicModel,
    modifier: GlanceModifier = GlanceModifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(GlanceTheme.colors.surfaceVariant)
    ) {
        WeatherTileTopBar(model = model)
        Spacer(modifier = GlanceModifier.height(4.dp))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = GlanceModifier
                    .wrapContentSize()
                    .cornerRadius(10.dp)
                    .size(36.dp)
                    .background(GlanceTheme.colors.primaryContainer),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    provider = ImageProvider(model.currentWeather.image),
                    contentDescription = model.currentWeather.condition,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                    modifier = GlanceModifier.padding(4.dp)
                )
            }
            Spacer(modifier = GlanceModifier.width(8.dp))
            CurrentTemperature(
                celsius = model.currentWeather.tempInCelsius,
                fahrenheit = model.currentWeather.tempInFahrenheit
            )
        }
    }
}
