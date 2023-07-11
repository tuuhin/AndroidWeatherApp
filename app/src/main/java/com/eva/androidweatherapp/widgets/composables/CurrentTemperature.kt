package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance

@Composable
@GlanceComposable
fun CurrentTemperatureText(
    celsius: Float,
    fahrenheit: Float,
    modifier: GlanceModifier = GlanceModifier,
    isAmerican: Boolean = isCurrentLocalAmericanGlance()
) {
    Text(
        text = if (isAmerican) "$fahrenheit \u00B0" else "$celsius \u00B0",
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = GlanceTheme.colors.onSurfaceVariant
        ),
        modifier = modifier
    )
}