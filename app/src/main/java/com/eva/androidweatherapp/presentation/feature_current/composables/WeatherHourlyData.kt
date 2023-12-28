package com.eva.androidweatherapp.presentation.feature_current.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import java.time.LocalDateTime

@Composable
fun WeatherHourlyData(
    hourlyWeather: List<WeatherHourModel>,
    onForecast: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.hourly_weather_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            TextButton(
                onClick = onForecast,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            ) {
                Text(
                    text = stringResource(id = R.string.forecast_text),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Divider(
            modifier = Modifier.padding(vertical = 2.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(items = hourlyWeather) { _, model ->
                WeatherHourCard(
                    hour = model,
                    modifier = Modifier.aspectRatio(.7f)
                )
            }
        }
    }
}


class WeatherHourlyDataPreviewParams : CollectionPreviewParameterProvider<List<WeatherHourModel>>(
    listOf(
        buildList {
            repeat(2) { add(PreviewFakeData.fakeWeatherHourModel) }
            add(PreviewFakeData.fakeWeatherHourModel.copy(date = LocalDateTime.now()))
            repeat(6) { add(PreviewFakeData.fakeWeatherHourModel) }
        },
        List(10) {
            PreviewFakeData.fakeWeatherHourModel
        }
    ),
)


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherHourlyDataPreview(
    @PreviewParameter(WeatherHourlyDataPreviewParams::class)
    hourly: List<WeatherHourModel>
) = AndroidWeatherAppTheme {
    WeatherHourlyData(
        hourlyWeather = hourly,
        onForecast = {}, modifier = Modifier.height(200.dp)
    )
}