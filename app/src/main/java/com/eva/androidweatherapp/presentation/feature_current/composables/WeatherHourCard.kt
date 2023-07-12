package com.eva.androidweatherapp.presentation.feature_current.composables

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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WeatherHourCard(
    modifier: Modifier = Modifier,
    hour: WeatherHourModel,
    selectedColor: Color = MaterialTheme.colorScheme.primaryContainer,
    unSelectedColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    onSelectedColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onUnSelectedColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    localeAmerican: Boolean = isCurrentLocaleAmerican()
) {
    val isCurrentHour by remember {
        derivedStateOf {
            hour.date.hour == LocalDateTime.now().hour
        }
    }

    val formattedTime by remember {
        derivedStateOf {
            hour.date.format(DateTimeFormatter.ofPattern("hh a"))
        }
    }
    Card(
        modifier = modifier.aspectRatio(2f / 3f),
        colors = CardDefaults.cardColors(
            containerColor = if (isCurrentHour) selectedColor else unSelectedColor,
            contentColor = if (isCurrentHour) onSelectedColor else onUnSelectedColor
        ), shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formattedTime.uppercase(),
                style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.weight(.1f))
            Image(
                painter = painterResource(hour.image),
                contentDescription = " Current hour Weather",
                colorFilter = ColorFilter.tint(if (isCurrentHour) onSelectedColor else onUnSelectedColor),
                modifier = Modifier
                    .weight(0.5f)
                    .sizeIn(maxHeight = 120.dp, maxWidth = 120.dp)
            )
            Spacer(modifier = Modifier.weight(.1f))
            if (localeAmerican)
                Text(
                    text = buildAnnotatedString {
                        append("${hour.tempF}")
                        withStyle(SpanStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)) {
                            append(" F")
                        }
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            else
                Text(
                    text = buildAnnotatedString {
                        append("${hour.tempC}")
                        withStyle(SpanStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize)) {
                            append(" C")
                        }
                    },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
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

@Preview
@Composable
fun WeatherHourCardPreview(
    @PreviewParameter(WeatherHourPreviewParams::class)
    hourModel: WeatherHourModel
) {
    WeatherHourCard(hour = hourModel, modifier = Modifier.height(120.dp))
}