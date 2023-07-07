package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import com.eva.androidweatherapp.domain.models.WeatherDayDataModel
import com.eva.androidweatherapp.presentation.composables.CurrentTemperatureData
import com.eva.androidweatherapp.presentation.composables.CurrentWeatherProperties
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import java.time.LocalDate

@Composable
fun WeatherDayCard(
    dayModel: WeatherDayDataModel,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primaryContainer,
    unSelectedColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    onSelectedColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onUnSelectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val isCurrentDay by remember {
        derivedStateOf {
            dayModel.date == LocalDate.now()
        }
    }

    Card(
        modifier = modifier.aspectRatio(1.5f),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentDay) selectedColor else unSelectedColor,
            contentColor = if (isCurrentDay) onSelectedColor else onUnSelectedColor
        ), shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            WeatherDayCardTopBar(
                dayModel = dayModel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
            )
            Divider(
                color = if (isCurrentDay) onSelectedColor else onUnSelectedColor,
                modifier = Modifier.padding(vertical = 2.dp)
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .weight(.4f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherImage(
                    res = dayModel.dayCycle.image,
                    background = if (isCurrentDay)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.primaryContainer,
                    color = if (isCurrentDay)
                        MaterialTheme.colorScheme.onSecondaryContainer
                    else
                        MaterialTheme.colorScheme.onPrimaryContainer
                )
                CurrentTemperatureData(
                    dayModel = dayModel.dayCycle,
                    horizontal = Alignment.CenterHorizontally
                )
            }
            CurrentWeatherProperties(
                forecast = dayModel.dayCycle,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

class WeatherDayPreviewParams :
    CollectionPreviewParameterProvider<WeatherDayDataModel>(
        listOf(
            PreviewFakeData.fakeWeatherDayDataModel,
            PreviewFakeData.fakeWeatherDayDataModel.copy(date = LocalDate.now())
        )
    )


@Preview
@Composable
fun WeatherDayCardPreview(
    @PreviewParameter(WeatherDayPreviewParams::class) day: WeatherDayDataModel
) {
    WeatherDayCard(dayModel = day)
}