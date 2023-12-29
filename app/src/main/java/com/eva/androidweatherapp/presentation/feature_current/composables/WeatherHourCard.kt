package com.eva.androidweatherapp.presentation.feature_current.composables

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.data.mappers.image
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import com.eva.androidweatherapp.utils.WeatherUnits
import com.eva.androidweatherapp.utils.toHourAmOrPm
import java.time.LocalDateTime

@Composable
fun WeatherHourCard(
    hour: WeatherHourModel,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    unSelectedColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    onSelectedColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    onUnSelectedColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    localeAmerican: Boolean = isCurrentLocaleAmerican()
) {
    val isCurrentHour by remember {
        derivedStateOf {
            hour.date.hour == LocalDateTime.now().hour
        }
    }

    val formattedTime by remember {
        derivedStateOf(hour.date::toHourAmOrPm)
    }

    val cardColor = if (isCurrentHour) CardDefaults.cardColors(
        containerColor = selectedColor,
        contentColor = onSelectedColor
    ) else CardDefaults.cardColors(
        containerColor = unSelectedColor,
        contentColor = onUnSelectedColor
    )

    val imageColor = if (isCurrentHour) ColorFilter.tint(onSelectedColor)
    else ColorFilter.tint(onUnSelectedColor)

    Card(
        modifier = modifier.aspectRatio(2f / 3f),
        colors = cardColor,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = formattedTime.uppercase(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(.2f))
            Image(
                painter = painterResource(hour.image),
                contentDescription = " Current Hour Image :${hour.image}",
                colorFilter = imageColor,
                modifier = Modifier
                    .weight(0.5f)
                    .sizeIn(maxHeight = 120.dp, maxWidth = 120.dp)
            )
            Spacer(modifier = Modifier.weight(.2f))
            Text(
                text = buildAnnotatedString {
                    if (localeAmerican) {
                        append("${hour.tempF}")
                        append(WeatherUnits.TEMP_FAHRENHEIT.text)
                    } else {
                        append("${hour.tempC}")
                        append(WeatherUnits.TEMP_CELSIUS.text)
                    }

                },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
    }
}


class WeatherHourPreviewParams : CollectionPreviewParameterProvider<WeatherHourModel>(
    listOf(
        PreviewFakeData.fakeWeatherHourModel,
        PreviewFakeData.fakeWeatherHourModel.copy(date = LocalDateTime.now())
    )
)

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherHourCardPreview(
    @PreviewParameter(WeatherHourPreviewParams::class)
    hourModel: WeatherHourModel
) = AndroidWeatherAppTheme {
    WeatherHourCard(hour = hourModel, modifier = Modifier.height(100.dp))
}