package com.eva.androidweatherapp.presentation.feature_current.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.data.mappers.image
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.composables.CurrentTemperatureData
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

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
                .padding(10.dp),
            verticalArrangement = Arrangement
                .spacedBy(space = dimensionResource(id = R.dimen.current_weather_topic_spacing))
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            CurrentTemperatureData(
                model = model,
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp)
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
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
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


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CurrentWeatherDataPreview() = AndroidWeatherAppTheme {
    CurrentWeatherData(
        model = PreviewFakeData.fakeForeCastModel,
        modifier = Modifier
            .wrapContentHeight()
            .padding(10.dp)
    )
}