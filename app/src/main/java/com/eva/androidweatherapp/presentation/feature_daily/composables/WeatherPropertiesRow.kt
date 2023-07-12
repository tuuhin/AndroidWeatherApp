package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.WeatherPropertyItem
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.WeatherPropertyStyle
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun WeatherPropertiesRow(
    dayForecast: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    arrangement: Arrangement.Horizontal = Arrangement.spacedBy(4.dp),
    isAmerican: Boolean = isCurrentLocaleAmerican(),
    background: Color = MaterialTheme.colorScheme.secondaryContainer,
    onBackground: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    style: WeatherPropertyStyle = WeatherPropertyStyle.COLUMN_LINEAR
) {
    Row(
        modifier = modifier,
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherPropertyItem(
            image = R.drawable.ic_wind_speed,
            title = "Wind Speed",
            value = if (isAmerican)
                "${dayForecast.maxWindSpeedInMph} mph"
            else
                "${dayForecast.maxWindSpeedInKmpH} kmph",
            background = background,
            onBackground = onBackground,
            style = style
        )
        WeatherPropertyItem(
            image = R.drawable.ic_humidity,
            title = "Humidity",
            value = "${dayForecast.avgHumidity} %",
            background = background,
            onBackground = onBackground,
            style = style
        )
        WeatherPropertyItem(
            image = R.drawable.ic_precipitation,
            value = "${dayForecast.rainPercentage} %",
            title = "Rainfall",
            background = background,
            onBackground = onBackground,
            style = style
        )
    }
}


@Preview(showBackground = true)
@Composable
fun WeatherPropertiesRowPreview() {
    WeatherPropertiesRow(
        dayForecast = PreviewFakeData.fakeWeatherDayDataModel,
        modifier = Modifier.fillMaxWidth(),
        arrangement = Arrangement.SpaceAround
    )
}
