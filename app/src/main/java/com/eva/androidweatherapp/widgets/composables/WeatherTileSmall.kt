package com.eva.androidweatherapp.widgets.composables

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
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
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

@Composable
@GlanceComposable
fun WeatherTileSmall(
    modifier: GlanceModifier = GlanceModifier,
    model: WidgetWeatherModel,
) {
    val imageBackGround = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        GlanceModifier
            .cornerRadius(10.dp)
            .background(GlanceTheme.colors.surfaceVariant)
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
        Column(
            modifier = GlanceModifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .defaultWeight()
                    .padding(horizontal = 10.dp)
            ) {
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        provider = ImageProvider(model.image),
                        contentDescription = model.condition,
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.primary),
                        modifier = GlanceModifier.padding(4.dp)
                            .size(60.dp)
                            .then(imageBackGround),
                        contentScale = ContentScale.Fit
                    )
                }
                Box(
                    modifier = GlanceModifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    CurrentTemperatureText(
                        celsius = model.tempInCelsius,
                        fahrenheit = model.tempInFahrenheit
                    )
                }
            }
            Spacer(modifier = GlanceModifier.height(8.dp))
            WeatherTileBottomSimple(model = model)
        }
    }
}