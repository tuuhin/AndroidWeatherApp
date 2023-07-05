package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel

@Composable
@GlanceComposable
fun WeatherTileSmall(
    modifier: GlanceModifier = GlanceModifier,
    model: CurrentWeatherBasicModel,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(GlanceTheme.colors.surfaceVariant)
    ) {
        WeatherTileTopBar(model = model)
        Column(
            modifier = GlanceModifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .defaultWeight()
                    .padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        provider = ImageProvider(model.currentWeather.image),
                        contentDescription = model.currentWeather.condition,
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
                        modifier = GlanceModifier.padding(4.dp)
                            .cornerRadius(10.dp)
                            .size(60.dp)
                            .background(GlanceTheme.colors.primaryContainer),
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = GlanceModifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    CurrentTemperature(
                        celsius = model.currentWeather.tempInCelsius,
                        fahrenheit = model.currentWeather.tempInFahrenheit
                    )
                }
            }
            Spacer(modifier = GlanceModifier.height(8.dp))
            WeatherTileBottomSimple(model = model)
        }
    }
}