package com.eva.androidweatherapp.presentation.feature_current.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.CurrentWeatherProperties
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@Composable
fun CurrentDayWeatherProperties(
    forecast: WeatherDayForecastModel,
    current: CurrentWeatherModel,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    shape: Shape = MaterialTheme.shapes.medium
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.weather_properties_card_padding)),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            CurrentWeatherProperties(
                forecastDay = forecast,
                current = current,
                modifier = Modifier.fillMaxWidth(),
                background = MaterialTheme.colorScheme.primaryContainer,
                onBackground = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Divider(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(id = R.string.astronomical_data),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .background(color = containerColor)
                        .padding(horizontal = 8.dp)
                )
            }
            WeatherAstronomicalData(
                forecast = forecast,
                background = MaterialTheme.colorScheme.primaryContainer,
                onBackground = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CurrentWeatherDayPropertiesPreview() = AndroidWeatherAppTheme {
    CurrentDayWeatherProperties(
        forecast = PreviewFakeData.fakeWeatherDayDataModel,
        current = PreviewFakeData.fakeCurrentWeatherModel
    )
}