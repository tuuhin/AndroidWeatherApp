package com.eva.androidweatherapp.presentation.feature_search.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.feature_daily.composables.WeatherPropertySimple
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun WeatherForecastCard(
    model: SavedWeatherModel,
    modifier: Modifier = Modifier,
    isAmerican: Boolean = isCurrentLocaleAmerican()
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = buildAnnotatedString {
                        if (model.region.isNotEmpty()) {
                            withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                                append(model.region)
                            }
                            append(" , ")
                        }
                        append(model.country)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal
                )
            }
            Divider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    WeatherImage(res = model.image, modifier = Modifier.size(60.dp))
                    Text(
                        text = model.condition,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isAmerican) "${model.tempInFahrenheit} F" else "${model.tempInCelsius} C",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                                append("Feels Like: ")
                            }
                            if (isAmerican)
                                append("${model.feelsLikeFahrenheit} F")
                            else
                                append("${model.feelsLikeInCelsius} C")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            WeatherPropertySimple(
                image = R.drawable.ic_wind_speed,
                value = if (isAmerican) "${model.windSpeedInKmh} mph" else "${model.windSpeedInKmh} kmph",
                title = "WInd Speed"
            )
            WeatherPropertySimple(
                image = R.drawable.ic_humidity,
                value = "${model.humidity}",
                title = "WInd Speed"
            )
            WeatherPropertySimple(
                image = R.drawable.ic_precipitation,
                value = if (isAmerican) "${model.precipitationInch} in" else "${model.precipitationMM} mm",
                title = "WInd Speed"
            )
            WeatherPropertySimple(
                image = R.drawable.ic_pressure,
                value = "${model.pressureMilliBar} mBar",
                title = "WInd Speed"
            )
        }

    }
}


@Preview
@Composable
fun WeatherForecastCardPreview() {
    WeatherForecastCard(
        model = PreviewFakeData.fakeSavedWeatherModel
    )
}