package com.eva.androidweatherapp.widgets.composables

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.ColorFilter
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

@Composable
@GlanceComposable
fun WeatherTileExtraSmall(
    model: WidgetWeatherModel,
    modifier: GlanceModifier = GlanceModifier
) {
    val imageBackground = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        GlanceModifier
            .background(GlanceTheme.colors.surfaceVariant)
            .cornerRadius(10.dp)
    else GlanceModifier.background(ImageProvider(R.drawable.shape_rounded_container))

    val backGround = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        GlanceModifier
            .background(GlanceTheme.colors.surfaceVariant)
            .cornerRadius(10.dp)
    else GlanceModifier.background(ImageProvider(R.drawable.shape_rounded_surface))


    Column(
        modifier = modifier
            .padding(8.dp)
            .then(backGround)
    ) {
        WeatherTileTopBar(model = model)
        Spacer(modifier = GlanceModifier.height(4.dp))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = GlanceModifier
                    .wrapContentSize()
                    .size(36.dp)
                    .then(imageBackground),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    provider = ImageProvider(model.image),
                    contentDescription = model.condition,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                    modifier = GlanceModifier.padding(4.dp)
                )
            }
            Spacer(modifier = GlanceModifier.width(8.dp))
            CurrentTemperatureText(
                celsius = model.tempInCelsius,
                fahrenheit = model.tempInFahrenheit
            )
        }
    }
}
