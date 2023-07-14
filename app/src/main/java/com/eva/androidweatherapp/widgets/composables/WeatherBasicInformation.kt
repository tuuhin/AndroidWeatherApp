package com.eva.androidweatherapp.widgets.composables

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle

@Composable
@GlanceComposable
fun WeatherBasicInformation(
    @DrawableRes image: Int,
    contentDescription: String? = null,
    data: String,
    title: String? = null,
    modifier: GlanceModifier = GlanceModifier,
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
                text = title, style = TextStyle(
                    fontSize = 12.sp,
                    color = GlanceTheme.colors.onPrimaryContainer,
                    fontWeight = FontWeight.Medium
                )
            )
        }
        Text(
            text = data,
            style = TextStyle(
                fontSize = 10.sp,
                color = GlanceTheme.colors.onPrimaryContainer,
                fontWeight = FontWeight.Medium
            )
        )
    }
}