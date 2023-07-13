package com.eva.androidweatherapp.widgets.composables

import android.os.Build
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
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

@Composable
@GlanceComposable
fun WeatherTopBarExtended(
    modifier: GlanceModifier = GlanceModifier,
    model: WidgetWeatherModel,
) {
    val background = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        GlanceModifier
            .background(GlanceTheme.colors.surfaceVariant)
            .cornerRadius(10.dp)
    else GlanceModifier.background(ImageProvider(R.drawable.shape_rounded_container))

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
                    .then(background),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    provider = ImageProvider(model.image),
                    contentDescription = model.condition,
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
                text = model.name,
                style = TextStyle(
                    color = GlanceTheme.colors.onSurfaceVariant,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = model.condition,
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