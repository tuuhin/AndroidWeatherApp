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
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.Text
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles
import com.eva.androidweatherapp.widgets.utils.roundedCorners

@Composable
@GlanceComposable
fun WeatherTopBarExtended(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = GlanceModifier
                .wrapContentSize()
                .roundedCorners(
                    color = GlanceTheme.colors.primaryContainer,
                    resId = R.drawable.shape_rounded_container
                )
                .size(60.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Image(
                provider = ImageProvider(model.image),
                contentDescription = model.condition,
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                modifier = GlanceModifier.padding(8.dp)
            )
        }
        Column(
            modifier = GlanceModifier.defaultWeight(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = model.name,
                style = GlanceTextStyles.largeTextStyleWithOnSurfaceVariantColor()
            )
            Text(
                text = model.condition,
                style = GlanceTextStyles.mediumTextStyleWithOnSurfaceVariantColor()
            )
        }
    }
}