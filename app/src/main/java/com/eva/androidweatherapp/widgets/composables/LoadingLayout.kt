package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalSize
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.widgets.utils.AvailableSizes

@Composable
@GlanceComposable
fun LoadingLayout(
    modifier: GlanceModifier = GlanceModifier,
    size: DpSize = LocalSize.current
) {
    when (size) {
        AvailableSizes.WIDGET_SIZE_SMALL -> Row(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(color = GlanceTheme.colors.onPrimaryContainer)
            Spacer(modifier = GlanceModifier.width(8.dp))
            Text(
                text = "Loading..",
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
        }

        else -> Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(color = GlanceTheme.colors.onPrimaryContainer)
            Spacer(modifier = GlanceModifier.height(8.dp))
            Text(
                text = "Loading..",
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            )
        }
    }

}