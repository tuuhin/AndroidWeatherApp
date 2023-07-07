package com.eva.androidweatherapp.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherForecastDayModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun CurrentWeatherProperties(
    forecast: WeatherForecastDayModel,
    modifier: Modifier = Modifier,
    current: CurrentWeatherModel? = null,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    columns: GridCells = GridCells.Fixed(2),
    horizontal: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween,
    vertical: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(2.dp),
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = columns,
        horizontalArrangement = horizontal,
        verticalArrangement = vertical,
        userScrollEnabled = false,
    ) {

        item {
            WeatherPropertyItem(
                image = R.drawable.ic_wind_speed,
                title = "Wind Speed",
                value = if (localeAmerican)
                    if (current != null) "${current.windSpeedInKmh} kmph"
                    else "${forecast.maxWindSpeedInKmpH} kmph"
                else
                    if (current != null) "${current.windSpeedInMh} mph"
                    else "${forecast.maxWindSpeedInMph} mph",
                modifier = Modifier.size(50.dp)
            )
        }
        item {
            WeatherPropertyItem(
                image = if (current != null) R.drawable.ic_pressure else R.drawable.ic_ultraviolet,
                title = "Pressure",
                value = if (current != null) "${current.pressureMilliBar} mBar" else "${forecast.ultralight}",
                modifier = Modifier.size(50.dp)
            )
        }
        item {
            WeatherPropertyItem(
                image = R.drawable.ic_humidity,
                title = "Wind Speed",
                value = if (current != null) "${current.humidity} %" else "${forecast.avgHumidity} %",
                modifier = Modifier.size(50.dp)
            )
        }
        item {
            WeatherPropertyItem(
                image = R.drawable.ic_precipitation,
                title = "Precipitation",
                value = "${forecast.totalPrecipitationInMm} mm",
                modifier = Modifier.size(50.dp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun CurrentWeatherPropertiesPreview() {
    CurrentWeatherProperties(
        forecast = PreviewFakeData.fakeWeatherForecastDayModel,
    )
}