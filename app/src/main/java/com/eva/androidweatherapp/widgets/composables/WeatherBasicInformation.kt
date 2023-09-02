package com.eva.androidweatherapp.widgets.composables

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.Text
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles

@Composable
@GlanceComposable
fun WeatherBasicInformation(
    @DrawableRes image: Int,
    value: String,
    units: WeatherUnits,
    modifier: GlanceModifier = GlanceModifier,
    contentDescription: String? = null,
    title: String? = null,
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.Horizontal.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(image),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
            modifier = GlanceModifier.size(40.dp)
        )
        title?.let {
            Text(
                text = it,
                style = GlanceTextStyles.smallTextStyleWithOnPrimaryContainerColor()
            )
        }
        Text(
            text = buildString {
                append(value)
                append(units.text)
            },
            style = GlanceTextStyles.extraSmallTextStyleWithOnPrimaryContainerColor()
        )
    }
}