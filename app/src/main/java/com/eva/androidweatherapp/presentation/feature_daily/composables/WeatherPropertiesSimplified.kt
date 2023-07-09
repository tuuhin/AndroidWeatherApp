package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun WeatherPropertiesSimplified(
    dayForecast: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.spacedBy(4.dp),
    isAmerican: Boolean = isCurrentLocaleAmerican(),
) {
    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherPropertySimple(
            image = R.drawable.ic_wind_speed,
            title = "Wind Speed",
            value= if (isAmerican) "${dayForecast.maxWindSpeedInMph}" else "${dayForecast.maxWindSpeedInKmpH}"
        )
        WeatherPropertySimple(
            image = R.drawable.ic_humidity,
            title = "Humidity",
            value = "${dayForecast.maxWindSpeedInKmpH}"
        )
        WeatherPropertySimple(
            image = R.drawable.ic_precipitation,
            value = if (isAmerican) "${dayForecast.rainPercentage}" else "${dayForecast.rainPercentage}",
            title = "Rainfall %"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherPropertiesSimplifiedPreview() {
    WeatherPropertiesSimplified(
        dayForecast = PreviewFakeData.fakeWeatherDayDataModel,
        modifier = Modifier.fillMaxWidth(),
        arrangement = Arrangement.SpaceAround
    )
}
