package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxHeight
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance

@Composable
@GlanceComposable
fun WeatherTileMedium(
    modifier: GlanceModifier = GlanceModifier,
    model: CurrentWeatherBasicModel,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .background(GlanceTheme.colors.surfaceVariant)
    ) {
        WeatherTopBarExtended(model = model)
        Spacer(modifier = GlanceModifier.height(4.dp))
        Row(
            modifier = GlanceModifier.fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                modifier = GlanceModifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = GlanceModifier.defaultWeight(),
                    contentAlignment = Alignment.Center
                ) {
                    CurrentTemperature(
                        celsius = model.currentWeather.tempInCelsius,
                        fahrenheit = model.currentWeather.tempInFahrenheit
                    )
                }
                Text(
                    text = "Feels like: ",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = GlanceTheme.colors.secondary
                    )
                )
                if (isCurrentLocalAmericanGlance())
                    Text(
                        text = "${model.currentWeather.feelsLikeFahrenheit}" + "\u00B0",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GlanceTheme.colors.onPrimaryContainer
                        )
                    )
                else
                    Text(
                        text = "${model.currentWeather.feelsLikeInCelsius}" + "\u00B0",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = GlanceTheme.colors.onPrimaryContainer
                        )
                    )

            }
            Spacer(modifier = GlanceModifier.width(4.dp))
            Box(
                modifier = GlanceModifier
                    .defaultWeight()
                    .fillMaxHeight()
                    .cornerRadius(10.dp)
                    .background(GlanceTheme.colors.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                WeatherBasicInfoGrid(model = model)
            }
        }
    }
}