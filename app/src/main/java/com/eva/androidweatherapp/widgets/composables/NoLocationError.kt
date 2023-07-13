package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
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
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.R

@Composable
@GlanceComposable
fun NoLocationError(
    modifier: GlanceModifier = GlanceModifier,
    action: Action,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_no_location),
            contentDescription = "Refresh Symbol",
            modifier = GlanceModifier
                .size(40.dp),
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.no_location_title_widget),
            style = TextStyle(
                color = GlanceTheme.colors.onSurfaceVariant,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium, textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = GlanceModifier.height(4.dp))
        Text(
            text = stringResource(id = R.string.no_location_desc_widget),
            style = TextStyle(
                color = GlanceTheme.colors.onSurfaceVariant,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = GlanceModifier.height(4.dp))

        Row(
            modifier = GlanceModifier.fillMaxWidth().clickable(action),
            horizontalAlignment = Alignment.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_refresh_symbol),
                contentDescription = "Refresh Icon",
                modifier = GlanceModifier
                    .size(20.dp),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
            )
            Spacer(modifier = GlanceModifier.width(4.dp))
            Text(
                text = "Refresh",
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }

}