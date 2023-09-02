package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.size
import androidx.glance.layout.width
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance
import com.eva.androidweatherapp.widgets.utils.roundedCorners

@Composable
@GlanceComposable
fun WeatherTileExtraSmall(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
    isAmerican: Boolean = isCurrentLocalAmericanGlance()
) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        WeatherTileTopBar(
            model = model,
            modifier = GlanceModifier.fillMaxWidth()
        )
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .defaultWeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = GlanceModifier
                    .roundedCorners(
                        color = GlanceTheme.colors.primaryContainer,
                        resId = R.drawable.shape_rounded_container
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    provider = ImageProvider(model.image),
                    contentDescription = model.condition,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                    modifier = GlanceModifier
                        .size(48.dp)
                        .padding(4.dp)
                )
            }
            Spacer(modifier = GlanceModifier.width(12.dp))
            CurrentTemperatureText(
                temp = if (isAmerican) model.tempInFahrenheit else model.tempInCelsius,
                units = if (isAmerican) WeatherUnits.TEMP_FAHRENHEIT else WeatherUnits.TEMP_CELSIUS,
                style = GlanceTextStyles.largeTextStyleWithOnSurfaceVariantColor()
            )
        }
    }
}
