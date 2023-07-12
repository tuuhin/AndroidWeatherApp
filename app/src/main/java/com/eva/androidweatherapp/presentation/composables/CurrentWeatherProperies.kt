package com.eva.androidweatherapp.presentation.composables

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
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun CurrentWeatherProperties(
    forecastDay: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    current: CurrentWeatherModel? = null,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    background: Color = MaterialTheme.colorScheme.secondaryContainer,
    onBackground: Color = MaterialTheme.colorScheme.onSecondaryContainer,
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
                image = R.drawable.ic_wind_speed,
                title = "Wind Speed",
                value = if (localeAmerican)
                    if (current != null) "${current.windSpeedInKmh} kmph"
                    else "${forecastDay.maxWindSpeedInKmpH} kmph"
                else
                    if (current != null) "${current.windSpeedInMh} mph"
                    else "${forecastDay.maxWindSpeedInMph} mph",
                background = background,
                onBackground = onBackground
            )
            WeatherPropertyItem(
                image = if (current != null) R.drawable.ic_pressure else R.drawable.ic_ultraviolet,
                title = "Pressure",
                value = if (current != null) "${current.pressureMilliBar} mBar" else "${forecastDay.ultralight}",
                background = background,
                onBackground = onBackground
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            WeatherPropertyItem(
                image = R.drawable.ic_humidity,
                title = "Humidity",
                value = if (current != null)
                    "${current.humidity} %"
                else
                    "${forecastDay.avgHumidity} %",
                background = background,
                onBackground = onBackground
            )
            WeatherPropertyItem(
                image = R.drawable.ic_precipitation,
                title = "Precipitation",
                value = "${forecastDay.totalPrecipitationInMm} mm",
                background = background,
                onBackground = onBackground
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CurrentWeatherPropertiesPreview() {
    CurrentWeatherProperties(
        forecastDay = PreviewFakeData.fakeWeatherDayDataModel,
    )
}