package com.eva.androidweatherapp.presentation.feature_search.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.presentation.composables.WeatherPropertyItem
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.WeatherPropertyStyle
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun CityWeatherProperties(
    model: SavedWeatherModel,
    modifier: Modifier = Modifier,
    style: WeatherPropertyStyle = WeatherPropertyStyle.COLUMN_LINEAR,
    background: Color = MaterialTheme.colorScheme.secondaryContainer,
    onBackGround: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    isAmerican: Boolean = isCurrentLocaleAmerican()
) {
    Row(
        modifier = modifier
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        WeatherPropertyItem(
            image = R.drawable.ic_wind_speed,
            value = if (isAmerican) "${model.windSpeedInKmh} mph" else "${model.windSpeedInKmh} Kmph",
            title = "Wind Speed",
            style = style,
            background = background,
            onBackground = onBackGround
        )
        WeatherPropertyItem(
            image = R.drawable.ic_humidity,
            value = "${model.humidity}%",
            title = "Humidity",
            style = style,
            background = background,
            onBackground = onBackGround
        )
        WeatherPropertyItem(
            image = R.drawable.ic_precipitation,
            value = if (isAmerican) "${model.precipitationInch} in" else "${model.precipitationMM} mm",
            title = "Rainfall",
            style = style,
            background = background,
            onBackground = onBackGround
        )
        WeatherPropertyItem(
            image = R.drawable.ic_pressure,
            value = "${model.pressureMilliBar} mBar",
            title = "Pressure",
            style = style,
            background = background,
            onBackground = onBackGround
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CityWeatherPropertiesPreview() {
    CityWeatherProperties(model = PreviewFakeData.fakeSavedWeatherModel)
}