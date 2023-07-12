package com.eva.androidweatherapp.presentation.feature_current.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
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
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.composables.CurrentTemperatureData
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun CurrentWeatherData(
    modifier: Modifier = Modifier,
    model: WeatherForeCastModel,
    localeAmerican: Boolean = isCurrentLocaleAmerican()
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(.5f)
                .fillMaxHeight()
                .padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(vertical = 2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = model.current.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                            append(model.current.region)
                        }
                        append(" , ")
                        append(model.current.country)
                    },
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            CurrentTemperatureData(
                model = model,
                horizontal = Alignment.Start,
                vertical = Arrangement.spacedBy(2.dp)
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = model.current.condition,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)
                        ) {
                            append("Feels Like: ")
                        }
                        if (localeAmerican)
                            append("${model.current.feelsLikeFahrenheit} F")
                        else
                            append("${model.current.feelsLikeInCelsius} C")
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium, color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        WeatherImage(
            res = model.current.image,
            background = MaterialTheme.colorScheme.primaryContainer,
            onBackGround = MaterialTheme.colorScheme.onPrimaryContainer,
            iPadding = PaddingValues(40.dp),
            modifier = Modifier
                .weight(.45f)
                .align(Alignment.CenterVertically)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CurrentWeatherDataPreview() {
    CurrentWeatherData(
        model = PreviewFakeData.fakeForeCastModel,
        modifier = Modifier
            .aspectRatio(1.25f)
            .padding(10.dp)
    )
}