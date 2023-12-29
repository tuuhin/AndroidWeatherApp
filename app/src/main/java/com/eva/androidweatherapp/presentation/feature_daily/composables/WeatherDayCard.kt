package com.eva.androidweatherapp.presentation.feature_daily.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.data.mappers.image
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.CurrentTemperatureData
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import com.eva.androidweatherapp.utils.toDayMonthFormat
import java.time.LocalDate

@Composable
fun WeatherDayCard(
    forecastModel: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    unSelectedColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    onSelectedColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    onUnSelectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    shape: Shape = CardDefaults.elevatedShape,
) {
    val isCurrentDay by remember {
        derivedStateOf {
            forecastModel.date == LocalDate.now()
        }
    }

    val formattedDate by remember { derivedStateOf(forecastModel.date::toDayMonthFormat) }

    val cardColors = if (isCurrentDay) CardDefaults.cardColors(
        containerColor = selectedColor,
        contentColor = onSelectedColor,
    ) else CardDefaults.cardColors(
        containerColor = unSelectedColor,
        contentColor = onUnSelectedColor,
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = cardColors,
        shape = shape
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
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
                color = if (isCurrentDay) onSelectedColor else onUnSelectedColor
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 2.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    WeatherImage(
                        res = forecastModel.image,
                        background = MaterialTheme.colorScheme.secondaryContainer,
                        onBackGround = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.sizeIn(maxHeight = 64.dp, maxWidth = 64.dp),
                        iPadding = PaddingValues(8.dp)

                    )
                    Text(
                        text = forecastModel.weather,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                CurrentTemperatureData(
                    forecastDay = forecastModel,
                    horizontal = Alignment.CenterHorizontally
                )

            }
            WeatherPropertiesRow(
                dayForecast = forecastModel,
                modifier = Modifier.fillMaxWidth(),
                arrangement = Arrangement.SpaceAround,
                background = MaterialTheme.colorScheme.secondaryContainer,
                onBackground = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        }
    }
}

private class WeatherDayPreviewParams :
    CollectionPreviewParameterProvider<WeatherDayForecastModel>(
        listOf(
            PreviewFakeData.fakeWeatherDayDataModel,
            PreviewFakeData.fakeWeatherDayDataModel.copy(date = LocalDate.now())
        )
    )


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherDayCardPreview(
    @PreviewParameter(WeatherDayPreviewParams::class)
    day: WeatherDayForecastModel
) = AndroidWeatherAppTheme {
    WeatherDayCard(forecastModel = day)
}