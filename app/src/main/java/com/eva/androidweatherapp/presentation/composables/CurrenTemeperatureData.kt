package com.eva.androidweatherapp.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun CurrentTemperatureData(
    model: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    vertical: Arrangement.Vertical = Arrangement.spacedBy(4.dp),
    tempTextStyle: TextStyle = MaterialTheme.typography.displaySmall
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
        verticalArrangement = vertical,
        horizontalAlignment = horizontal
    ) {
        if (localeAmerican)
            Text(
                text = "${model.current.tempInFahrenheit} F",
                style = tempTextStyle,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        else
            Text(
                text = "${model.current.tempInCelsius} C",
                style = tempTextStyle,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_upward),
                    contentDescription = "Max temperature",
                    colorFilter = ColorFilter.tint(iconColor),
                )
                if (localeAmerican)
                    Text(
                        text = "${model.forecast.first().maxTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                else
                    Text(
                        text = "${model.forecast.first().maxTempInCelsius} C",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_downward),
                    contentDescription = "Minimum Temperature",
                    colorFilter = ColorFilter.tint(iconColor),
                )
                if (localeAmerican)
                    Text(
                        text = "${model.forecast[0].minTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                else
                    Text(
                        text = "${model.forecast[0].minTempInCelsius} C",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

            }
        }
    }
}

@Composable
fun CurrentTemperatureData(
    forecastDay: WeatherDayForecastModel,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    vertical: Arrangement.Vertical = Arrangement.spacedBy(4.dp),
    tempTextStyle: TextStyle = MaterialTheme.typography.displaySmall
) {
    Column(
        modifier = modifier.width(IntrinsicSize.Max),
        verticalArrangement = vertical,
        horizontalAlignment = horizontal
    ) {
        if (localeAmerican)
            Text(
                text = "${forecastDay.maxTempInFahrenheit} F",
                style = tempTextStyle,
                fontWeight = FontWeight.Bold
            )
        else
            Text(
                text = "${forecastDay.maxTempInCelsius} C",
                style = tempTextStyle,
                fontWeight = FontWeight.Bold
            )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_upward),
                    contentDescription = "Max temperature",
                    colorFilter = ColorFilter.tint(iconColor),
                )
                if (localeAmerican)
                    Text(
                        text = "${forecastDay.maxTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    Text(
                        text = "${forecastDay.maxTempInCelsius} C",
                        style = MaterialTheme.typography.bodyMedium
                    )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_downward),
                    contentDescription = "Minimum Temperature",
                    colorFilter = ColorFilter.tint(iconColor),
                )
                if (localeAmerican)
                    Text(
                        text = "${forecastDay.minTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    Text(
                        text = "${forecastDay.minTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CurrentTemperatureDataPreview() {
    CurrentTemperatureData(model = PreviewFakeData.fakeForeCastModel)
}