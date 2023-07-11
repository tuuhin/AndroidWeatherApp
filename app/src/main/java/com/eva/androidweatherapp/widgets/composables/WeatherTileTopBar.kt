package com.eva.androidweatherapp.widgets.composables

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
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

@Composable
@GlanceComposable
fun WeatherTileTopBar(
    modifier: GlanceModifier = GlanceModifier,
    model: WidgetWeatherModel,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            provider = ImageProvider(R.drawable.ic_location_symbol),
            contentDescription = "Location Symbol",
            modifier = GlanceModifier.size(24.dp),
            colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
        )
        Spacer(modifier = GlanceModifier.width(4.dp))
        Column(
            modifier = GlanceModifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = model.name,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = model.condition,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
//        Row(
//            modifier = GlanceModifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.End
//        ) {
//            Image(
//                provider = ImageProvider(R.drawable.ic_refresh_symbol),
//                contentDescription = "Refresh Symbol",
//                modifier = GlanceModifier
//                    .size(20.dp)
//                    .clickable(action),
//                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
//            )
//        }
    }
}