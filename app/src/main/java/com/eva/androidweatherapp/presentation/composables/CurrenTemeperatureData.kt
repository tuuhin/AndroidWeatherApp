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
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.models.WeatherForecastDayModel
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
                text = "${model.currentWeatherModel.tempInFahrenheit} F",
                style = tempTextStyle,
                fontWeight = FontWeight.Bold
            )
        else
            Text(
                text = "${model.currentWeatherModel.tempInCelsius} C",
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
                        text = "${model.forecast.first().dayCycle.maxTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    Text(
                        text = "${model.forecast.first().dayCycle.maxTempInCelsius} C",
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
                        text = "${model.forecast[0].dayCycle.minTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    Text(
                        text = "${model.forecast[0].dayCycle.minTempInCelsius} C",
                        style = MaterialTheme.typography.bodyMedium
                    )

            }
        }
    }
}

@Composable
fun CurrentTemperatureData(
    dayModel: WeatherForecastDayModel,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    isCurrentDay: Boolean = false,
    onSelectedColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onUnSelectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
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
                text = "${dayModel.maxTempInFahrenheit} F",
                style = tempTextStyle,
                fontWeight = FontWeight.Bold
            )
        else
            Text(
                text = "${dayModel.maxTempInCelsius} C",
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
                    colorFilter = ColorFilter.tint(
                        if (isCurrentDay) onSelectedColor else onUnSelectedColor
                    ),
                )
                if (localeAmerican)
                    Text(
                        text = "${dayModel.maxTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    Text(
                        text = "${dayModel.maxTempInCelsius} C",
                        style = MaterialTheme.typography.bodyMedium
                    )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_downward),
                    contentDescription = "Minimum Temperature",
                    colorFilter = ColorFilter.tint(
                        if (isCurrentDay) onSelectedColor else onUnSelectedColor
                    ),
                )
                if (localeAmerican)
                    Text(
                        text = "${dayModel.minTempInFahrenheit} F",
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    Text(
                        text = "${dayModel.minTempInFahrenheit} F",
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