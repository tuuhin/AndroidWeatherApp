package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
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
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherForecastBasicModel

@Composable
fun WeatherTileMedium(
    modifier: GlanceModifier = GlanceModifier,
    model: CurrentWeatherForecastBasicModel,
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .background(GlanceTheme.colors.surfaceVariant)
    ) {
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.End
        ) {
            Image(
                provider = ImageProvider(R.drawable.ic_location_symbol),
                contentDescription = "Location Symbol",
                modifier = GlanceModifier.size(16.dp).padding(horizontal = 2.dp),
                colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
            )
            Spacer(modifier = GlanceModifier.width(4.dp))
            Text(
                text = model.searchedLocationModel.name,
                style = TextStyle(color = GlanceTheme.colors.onSurfaceVariant, fontSize = 16.sp)
            )
            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Image(
                    provider = ImageProvider(R.drawable.ic_refresh_symbol),
                    contentDescription = "Refresh Symbol",
                    modifier = GlanceModifier
                        .size(16.dp)
                    // TODO: Add A on Click to update the current state
                    ,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant)
                )
            }
        }
        Box(
            modifier = GlanceModifier.fillMaxWidth()
        ) {
            Box(
                modifier = GlanceModifier.wrapContentSize(),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    provider = ImageProvider(model.currentWeatherModel.image),
                    contentDescription = model.currentWeatherModel.condition,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onSurfaceVariant),
                    modifier = GlanceModifier.size(50.dp)
                )
            }
            Box(
                modifier = GlanceModifier.wrapContentSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${model.currentWeatherModel.feelsLikeInCelsius}",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = GlanceTheme.colors.onSurfaceVariant
                    )
                )
            }
        }
        Row(
            modifier = GlanceModifier
                .fillMaxWidth()
                .background(GlanceTheme.colors.primaryContainer)
                .cornerRadius(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WeatherDataBlock(
                image = R.drawable.ic_precipitation,
                data = "${model.currentWeatherModel.precipitationMM}",
                unit = "mm",
                modifier = GlanceModifier.padding(horizontal = 2.dp)
            )
            WeatherDataBlock(
                image = R.drawable.ic_humidity,
                data = "${model.currentWeatherModel.humidity}",
                unit = "%",
                modifier = GlanceModifier.padding(horizontal = 2.dp)
            )
            WeatherDataBlock(
                image = R.drawable.ic_wind_speed,
                data = "${model.currentWeatherModel.windSpeedInKmh}",
                unit = "km/h",
                modifier = GlanceModifier.padding(horizontal = 2.dp)
            )
        }
    }
}