package com.eva.androidweatherapp.presentation.feature_current.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.WeatherPropertyItem
import com.eva.androidweatherapp.presentation.util.PreviewFakeData

@Composable
fun WeatherAstronomicalData(
    dayModel: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    columns: GridCells = GridCells.Fixed(2),
    horizontal: Arrangement.HorizontalOrVertical = Arrangement.SpaceBetween,
    vertical: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(2.dp),
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Astronomical Data",
            style = MaterialTheme.typography.titleMedium
        )
        LazyVerticalGrid(
            columns = columns,
            horizontalArrangement = horizontal,
            verticalArrangement = vertical,
            userScrollEnabled = false
        ) {
            item {
                WeatherPropertyItem(
                    image = R.drawable.ic_sunrise,
                    title = "Sunrise",
                    value = dayModel.sunrise,
                    modifier = Modifier.size(50.dp)
                )
            }
            item {
                WeatherPropertyItem(
                    image = R.drawable.ic_sunset,
                    title = "Sunset",
                    value = dayModel.sunset,
                    modifier = Modifier.size(50.dp)
                )
            }
            item {
                WeatherPropertyItem(
                    image = R.drawable.ic_moon_rise,
                    title = "Moon Rise",
                    value = dayModel.moonRise,
                    modifier = Modifier.size(50.dp)
                )
            }
            item {
                WeatherPropertyItem(
                    image = R.drawable.ic_moon_set,
                    title = "Moon Set",
                    value = dayModel.moonSet,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherAstronomicalDataPreview() {
    WeatherAstronomicalData(
        dayModel = PreviewFakeData.fakeWeatherDayDataModel,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}