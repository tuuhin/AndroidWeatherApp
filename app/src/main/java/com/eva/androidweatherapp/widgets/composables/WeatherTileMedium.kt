package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
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
import androidx.glance.text.Text
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance
import com.eva.androidweatherapp.widgets.utils.roundedCorners

@Composable
@GlanceComposable
fun WeatherTileMedium(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
    isAmerican: Boolean = isCurrentLocalAmericanGlance(),
) {
    Column(
        modifier = modifier.padding(10.dp)
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
                    CurrentTemperatureText(
                        temp = if (isAmerican) model.tempInFahrenheit else model.tempInCelsius,
                        units = if (isAmerican) WeatherUnits.TEMP_FAHRENHEIT else WeatherUnits.TEMP_CELSIUS,
                        style = GlanceTextStyles.extraLargeTextStyleWithOnSurfaceVariantColor()
                    )
                }
                Text(
                    text = "Feels like: ",
                    style = GlanceTextStyles.smallTextStyleWithSecondaryColor()
                )
                CurrentTemperatureText(
                    temp = if (isAmerican) model.feelsLikeFahrenheit else model.feelsLikeInCelsius,
                    units = if (isAmerican) WeatherUnits.TEMP_FAHRENHEIT else WeatherUnits.TEMP_CELSIUS,
                    style = GlanceTextStyles.smallTextStyleWithOnSurfaceVariantColor()
                )
            }
            Spacer(modifier = GlanceModifier.width(4.dp))
            Box(
                modifier = GlanceModifier
                    .defaultWeight()
                    .fillMaxHeight()
                    .roundedCorners(
                        color = GlanceTheme.colors.primaryContainer,
                        resId = R.drawable.shape_rounded_container
                    ),
                contentAlignment = Alignment.Center
            ) {
                WeatherExtraInfo(
                    model = model,
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp)
                )
            }
        }
    }
}