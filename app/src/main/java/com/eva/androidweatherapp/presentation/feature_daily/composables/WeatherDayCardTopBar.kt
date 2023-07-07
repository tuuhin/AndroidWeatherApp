package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherDayDataModel
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import java.time.format.DateTimeFormatter

@Composable
fun WeatherDayCardTopBar(
    dayModel: WeatherDayDataModel,
    modifier: Modifier = Modifier
) {
    val formattedDate by remember {
        derivedStateOf {
            dayModel.date.format(DateTimeFormatter.ofPattern("dd MMMM"))
        }
    }
    Row(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = dayModel.date.dayOfWeek.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = dayModel.dayCycle.weather,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.End,
            )
            dayModel.dayCycle.quality?.let { quality ->
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                            append("Air Quality: ")
                        }
                        append(quality.option)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.End,
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherDayCardTopBarPreview() {
    WeatherDayCardTopBar(
        dayModel = PreviewFakeData.fakeWeatherDayDataModel,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    )
}