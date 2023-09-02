package com.eva.androidweatherapp.widgets.composables

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.Text
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel
import com.eva.androidweatherapp.widgets.theme.GlanceTextStyles

@Composable
@GlanceComposable
fun WeatherTileTopBar(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier,
    context: Context = LocalContext.current
) {
    Row(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_location_symbol),
            contentDescription = context.getString(R.string.location_symbol),
            modifier = GlanceModifier.size(24.dp),
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer)
        )
        Spacer(modifier = GlanceModifier.width(4.dp))
        Column(
            modifier = GlanceModifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = model.name,
                style = GlanceTextStyles.smallTitleTextStyle()
            )
            Text(
                text = model.condition,
                style = GlanceTextStyles.smallSubTitleTextStyle()
            )
        }
    }
}