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
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel

@Composable
@GlanceComposable
fun WeatherTopBarExtended(
    modifier: GlanceModifier = GlanceModifier,
    model: CurrentWeatherBasicModel,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = GlanceModifier.width(80.dp)
        ) {
            Box(
                modifier = GlanceModifier
                    .wrapContentSize()
                    .size(60.dp)
                    .background(GlanceTheme.colors.primaryContainer)
                    .cornerRadius(10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    provider = ImageProvider(model.currentWeather.image),
                    contentDescription = model.currentWeather.condition,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                    modifier = GlanceModifier.padding(8.dp)
                )
            }
        }
        Column(
            modifier = GlanceModifier.defaultWeight(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = model.location.name,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = model.currentWeather.condition,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}