package com.eva.androidweatherapp.widgets.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.width
import androidx.glance.text.Text
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles
import com.eva.androidweatherapp.widgets.utils.AvailableSizes
import com.eva.androidweatherapp.widgets.utils.toWidgetSize

@Composable
@GlanceComposable
fun LoadingLayout(
    modifier: GlanceModifier = GlanceModifier,
    dpSize: DpSize = LocalSize.current,
    context: Context = LocalContext.current
) {
    when (dpSize.toWidgetSize()) {
        AvailableSizes.WIDGET_SIZE_SMALL -> Row(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                color = GlanceTheme.colors.secondary
            )
            Spacer(modifier = GlanceModifier.width(8.dp))
            Text(
                text = context.getString(R.string.loading_text),
                style = GlanceTextStyles.mediumTextStyleWithOnSurfaceVariantColor()
            )
        }

        else -> Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularProgressIndicator(
                color = GlanceTheme.colors.secondary
            )
            Spacer(modifier = GlanceModifier.height(8.dp))
            Text(
                text = context.getString(R.string.loading_text),
                style = GlanceTextStyles.mediumTextStyleWithOnSurfaceVariantColor()
            )
        }
    }

}