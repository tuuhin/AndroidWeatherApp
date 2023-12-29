package com.eva.androidweatherapp.presentation.feature_daily.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.presentation.composables.WeatherPropertyItem
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.WeatherPropertyStyle
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

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
            title = stringResource(id = R.string.wind_speed),
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
            title = stringResource(id = R.string.humidity),
            value = "${dayForecast.avgHumidity} %",
            background = background,
            onBackground = onBackground,
            style = style
        )
        WeatherPropertyItem(
            image = R.drawable.ic_precipitation,
            value = "${dayForecast.rainPercentage} %",
            title = stringResource(id = R.string.precipitation),
            background = background,
            onBackground = onBackground,
            style = style
        )
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherPropertiesRowPreview() = AndroidWeatherAppTheme {
    WeatherPropertiesRow(
        dayForecast = PreviewFakeData.fakeWeatherDayDataModel,
        modifier = Modifier.fillMaxWidth(),
        arrangement = Arrangement.SpaceAround
    )
}
