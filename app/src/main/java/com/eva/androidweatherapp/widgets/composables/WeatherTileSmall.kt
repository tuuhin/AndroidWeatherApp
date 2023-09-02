package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
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
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance
import com.eva.androidweatherapp.widgets.utils.roundedCorners

@Composable
@GlanceComposable
fun WeatherTileSmall(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
    isAmerican: Boolean = isCurrentLocalAmericanGlance(),
) {
    Column(
        modifier = modifier.padding(8.dp)
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
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        provider = ImageProvider(model.image),
                        contentDescription = model.condition,
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
                        modifier = GlanceModifier.padding(4.dp)
                            .size(60.dp)
                            .roundedCorners(
                                color = GlanceTheme.colors.primaryContainer,
                                resId = R.drawable.shape_rounded_container
                            ),
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = GlanceModifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    CurrentTemperatureText(
                        temp = if (isAmerican) model.tempInFahrenheit else model.tempInCelsius,
                        units = if (isAmerican) WeatherUnits.TEMP_FAHRENHEIT else WeatherUnits.TEMP_CELSIUS,
                        style = GlanceTextStyles.extraLargeTextStyleWithOnSurfaceVariantColor()
                    )
                }
            }
            Spacer(modifier = GlanceModifier.height(8.dp))
            WeatherTileBottomSimple(model = model)
        }
    }
}