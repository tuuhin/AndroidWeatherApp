package com.eva.androidweatherapp.presentation.feature_current.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.CurrentWeatherProperties
import com.eva.androidweatherapp.presentation.util.PreviewFakeData

@Composable
fun CurrentDayWeatherProperties(
    forecast: WeatherDayForecastModel,
    current: CurrentWeatherModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            CurrentWeatherProperties(
                forecastDay = forecast,
                current = current,
                modifier = Modifier.fillMaxWidth(),
                background = MaterialTheme.colorScheme.primaryContainer,
                onBackground = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Divider(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .5f),
                    thickness = 1.dp
                )
                Text(
                    text = "Astronomical Data",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 8.dp)
                )
            }
            WeatherAstronomicalData(
                forecast = forecast,
                background = MaterialTheme.colorScheme.primaryContainer,
                onBackground = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview
@Composable
fun CurrentWeatherDayPropertiesPreview() {
    CurrentDayWeatherProperties(
        forecast = PreviewFakeData.fakeWeatherDayDataModel,
        current = PreviewFakeData.fakeCurrentWeatherModel
    )
}