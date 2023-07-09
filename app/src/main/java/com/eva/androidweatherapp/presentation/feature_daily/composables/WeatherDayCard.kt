package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.CurrentTemperatureData
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDayCard(
    forecastModel: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primaryContainer,
    unSelectedColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    onSelectedColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onUnSelectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val isCurrentDay by remember {
        derivedStateOf {
            forecastModel.date == LocalDate.now()
        }
    }

    val formattedDate by remember {
        derivedStateOf {
            forecastModel.date.format(DateTimeFormatter.ofPattern("dd MMMM"))
        }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentDay) selectedColor else unSelectedColor,
            contentColor = if (isCurrentDay) onSelectedColor else onUnSelectedColor
        ), shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = forecastModel.date.dayOfWeek.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.titleSmall
                )

            }
            Divider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = if (isCurrentDay)
                    MaterialTheme.colorScheme.onPrimaryContainer
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherImage(
                    res = forecastModel.image,
                    background = if (isCurrentDay)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.primaryContainer,
                    color = if (isCurrentDay)
                        MaterialTheme.colorScheme.onSecondaryContainer
                    else
                        MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.sizeIn(maxHeight = 100.dp, maxWidth = 100.dp)
                )
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    CurrentTemperatureData(
                        forecastDay = forecastModel,
                        horizontal = Alignment.CenterHorizontally
                    )

                    Text(
                        text = forecastModel.weather,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
            WeatherPropertiesSimplified(
                dayForecast = forecastModel,
                modifier = Modifier.fillMaxWidth(),
                arrangement = Arrangement.SpaceAround
            )
        }
    }
}

class WeatherDayPreviewParams :
    CollectionPreviewParameterProvider<WeatherDayForecastModel>(
        listOf(
            PreviewFakeData.fakeWeatherDayDataModel,
            PreviewFakeData.fakeWeatherDayDataModel.copy(date = LocalDate.now())
        )
    )


@Preview
@Composable
fun WeatherDayCardPreview(
    @PreviewParameter(WeatherDayPreviewParams::class)
    day: WeatherDayForecastModel
) {
    WeatherDayCard(forecastModel = day)
}