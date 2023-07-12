package com.eva.androidweatherapp.presentation.feature_current.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.WeatherPropertyItem
import com.eva.androidweatherapp.presentation.util.PreviewFakeData

@Composable
fun WeatherAstronomicalData(
    forecast: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.primaryContainer,
    onBackground: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            WeatherPropertyItem(
                image = R.drawable.ic_sunrise,
                title = "Sunrise",
                value = forecast.sunrise,
                background = background,
                onBackground = onBackground
            )
            WeatherPropertyItem(
                image = R.drawable.ic_sunset,
                title = "Sunset",
                value = forecast.sunset,
                background = background,
                onBackground = onBackground
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            WeatherPropertyItem(
                image = R.drawable.ic_moon_rise,
                title = "Moon Rise",
                value = forecast.moonRise,
                background = background,
                onBackground = onBackground
            )
            WeatherPropertyItem(
                image = R.drawable.ic_moon_set,
                title = "Moon Set",
                value = forecast.moonSet,
                background = background,
                onBackground = onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherAstronomicalDataPreview() {
    WeatherAstronomicalData(
        forecast = PreviewFakeData.fakeWeatherDayDataModel,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}