package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.utils.WeatherUnits

@Composable
@GlanceComposable
fun CurrentTemperatureText(
    temp: Float,
    modifier: GlanceModifier = GlanceModifier,
    units: WeatherUnits,
    style: TextStyle = TextStyle()
) {
    Text(
        text = "$temp ${units.text}",
        style = style,
        modifier = modifier
    )
}