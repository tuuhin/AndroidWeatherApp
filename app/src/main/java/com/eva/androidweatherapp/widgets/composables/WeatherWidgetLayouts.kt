package com.eva.androidweatherapp.widgets.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.glance.GlanceComposable
import androidx.glance.GlanceModifier
import androidx.glance.LocalSize
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel
import com.eva.androidweatherapp.widgets.utils.AvailableSizes

@Composable
@GlanceComposable
fun WeatherWidgetLayouts(
    model: CurrentWeatherBasicModel,
    modifier: GlanceModifier = GlanceModifier,
    size: DpSize = LocalSize.current
) {
    when (size) {
        AvailableSizes.WIDGET_SIZE_SMALL -> WeatherTileExtraSmall(
            model = model,
            modifier = modifier
        )

        AvailableSizes.WIDGET_SIZE_MEDIUM -> WeatherTileSmall(
            model = model,
            modifier = modifier
        )

        else -> WeatherTileMedium(
            model = model,
            modifier = modifier
        )
    }
}