package com.eva.androidweatherapp.widgets.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.Action
import androidx.glance.action.clickable
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles

@Composable
@GlanceComposable
fun NoLocationError(
    action: Action,
    modifier: GlanceModifier = GlanceModifier,
    context: Context = LocalContext.current
) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_no_location),
            contentDescription = context.getString(R.string.location_symbol),
            modifier = GlanceModifier
                .size(40.dp),
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.no_location_title_widget),
            style = GlanceTextStyles.mediumTextStyleWithOnSurfaceVariantColor()
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.no_location_desc_widget),
            style = GlanceTextStyles.smallTextStyleWithOnSurfaceVariantColor()
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .clickable(action),
            horizontalAlignment = Alignment.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_refresh_symbol),
                contentDescription = context.getString(R.string.refresh),
                modifier = GlanceModifier
                    .size(20.dp),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
            )
            Spacer(modifier = GlanceModifier.width(4.dp))
            Text(
                text = context.getString(R.string.refresh),
                style = GlanceTextStyles.smallTextStyleWithOnSurfaceVariantColor()
            )
        }
    }

}