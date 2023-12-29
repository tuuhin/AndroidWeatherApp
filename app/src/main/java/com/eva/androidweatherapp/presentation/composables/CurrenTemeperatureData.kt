package com.eva.androidweatherapp.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@Composable
fun CurrentTemperatureData(
    model: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(4.dp),
    tempTextStyle: TextStyle = MaterialTheme.typography.displaySmall,
    minMaxTempTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    minMaxTempTextColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    val tempText by remember(localeAmerican) {
        derivedStateOf {
            if (localeAmerican) "${model.current.tempInFahrenheit} F"
            else "${model.current.tempInCelsius} C"
        }
    }

    val maxTempText by remember(localeAmerican) {
        derivedStateOf {
            if (localeAmerican) "${model.forecast.first().maxTempInFahrenheit} F"
            else "${model.forecast.first().maxTempInCelsius} C"
        }
    }

    val minTempText by remember(localeAmerican) {
        derivedStateOf {
            if (localeAmerican) "${model.forecast.first().minTempInFahrenheit} F"
            else "${model.forecast.first().minTempInCelsius} C"
        }
    }


    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Text(
            text = tempText,
            style = tempTextStyle,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            modifier = Modifier.wrapContentWidth(),
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
                Text(
                    text = maxTempText,
                    style = minMaxTempTextStyle,
                    color = minMaxTempTextColor
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

                Text(
                    text = minTempText,
                    style = minMaxTempTextStyle,
                    color = minMaxTempTextColor
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
    tempTextStyle: TextStyle = MaterialTheme.typography.displaySmall,
    minMaxTempTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    minMaxTempTextColor: Color = MaterialTheme.colorScheme.onSurface,
) {

    val maxTempText by remember(localeAmerican) {
        derivedStateOf {
            if (localeAmerican) "${forecastDay.maxTempInFahrenheit} F"
            else "${forecastDay.maxTempInCelsius} C"
        }
    }


    val minTempText by remember(localeAmerican) {
        derivedStateOf {
            if (localeAmerican) "${forecastDay.minTempInFahrenheit} F"
            else "${forecastDay.minTempInCelsius} C"
        }
    }

    Column(
        modifier = modifier.width(IntrinsicSize.Max),
        verticalArrangement = vertical,
        horizontalAlignment = horizontal
    ) {
        Text(
            text = maxTempText,
            style = tempTextStyle,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier.wrapContentWidth(),
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
                Text(
                    text = maxTempText,
                    style = minMaxTempTextStyle,
                    color = minMaxTempTextColor
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

                Text(
                    text = minTempText,
                    style = minMaxTempTextStyle,
                    color = minMaxTempTextColor
                )
            }
        }
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
fun CurrentTemperatureDataPreview() = AndroidWeatherAppTheme {
    CurrentTemperatureData(model = PreviewFakeData.fakeForeCastModel)
}