package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.background
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.padding
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.Action
import androidx.glance.appwidget.cornerRadius
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentSize
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.eva.androidweatherapp.domain.models.CurrentWeatherForecastBasicModel
import com.eva.androidweatherapp.widgets.utils.isCurrentLocalAmericanGlance

@Composable
fun WeatherTileExtraSmall(
    model: CurrentWeatherForecastBasicModel,
    action: Action,
    modifier: GlanceModifier = GlanceModifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .background(GlanceTheme.colors.surfaceVariant)
    ) {
        WeatherTileTopBar(action = action, model = model)
        Spacer(modifier = GlanceModifier.height(4.dp))
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = GlanceModifier
                    .wrapContentSize()
                    .cornerRadius(10.dp)
                    .size(36.dp)
                    .background(GlanceTheme.colors.primaryContainer),
                contentAlignment = Alignment.TopStart
            ) {
                Image(
                    provider = ImageProvider(model.currentWeatherModel.image),
                    contentDescription = model.currentWeatherModel.condition,
                    colorFilter = ColorFilter.tint(GlanceTheme.colors.onPrimaryContainer),
                    modifier = GlanceModifier.padding(4.dp)
                )
            }
            Spacer(modifier = GlanceModifier.width(8.dp))
            if (isCurrentLocalAmericanGlance())
                Text(
                    text = "${model.currentWeatherModel.tempInFahrenheit}" + "\u00B0F",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GlanceTheme.colors.onSurfaceVariant
                    )
                )
            else
                Text(
                    text = "${model.currentWeatherModel.tempInCelsius}" + "\u00B0C",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = GlanceTheme.colors.onSurfaceVariant
                    )
                )
        }
    }
}
