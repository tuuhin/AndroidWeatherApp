package com.eva.androidweatherapp.presentation.feature_search.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.data.mappers.image
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import com.eva.androidweatherapp.utils.toDateTimeFormat

@Composable
fun WeatherForecastCard(
    model: SavedWeatherModel,
    modifier: Modifier = Modifier,
    isAmerican: Boolean = isCurrentLocaleAmerican(),
    onRemove: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {

    val tempText by remember {
        derivedStateOf {
            if (isAmerican) "${model.tempInFahrenheit} F"
            else "${model.tempInCelsius} C"
        }
    }
    val feelsLikeTempText by remember {
        derivedStateOf {
            if (isAmerican)
                "${model.feelsLikeFahrenheit} F"
            else "${model.feelsLikeInCelsius} C"
        }
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = buildAnnotatedString {
                            if (model.region.isNotEmpty()) {
                                withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                                    append(model.region)
                                }
                                append(",")
                            }
                            append(model.country)
                        },
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal
                    )
                }
                TextButton(
                    onClick = onRemove,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Remove")
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = stringResource(id = R.string.remove_button_text))
                }
            }
            Divider(color = MaterialTheme.colorScheme.outline)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(.5f),
                    horizontalAlignment = Alignment.Start
                ) {
                    WeatherImage(
                        res = model.image,
                        modifier = Modifier.size(60.dp),
                        background = MaterialTheme.colorScheme.tertiaryContainer,
                        onBackGround = MaterialTheme.colorScheme.onTertiaryContainer,
                        shape = MaterialTheme.shapes.medium
                    )
                    Text(
                        text = model.condition,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 3,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.weight(.05f))
                Column(
                    modifier = Modifier.weight(.4f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = tempText,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = buildAnnotatedString {
                            val feelsLikeText = stringResource(id = R.string.feels_like_text)

                            append(feelsLikeText)
                            append(feelsLikeTempText)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            CityWeatherProperties(
                model = model,
                modifier = Modifier.fillMaxWidth(),
                background = MaterialTheme.colorScheme.tertiaryContainer,
                onBackGround = MaterialTheme.colorScheme.onTertiaryContainer,
            )
            model.lastUpdate?.let { time ->
                val formattedTime by remember {
                    derivedStateOf(time::toDateTimeFormat)
                }
                Divider(color = MaterialTheme.colorScheme.outline)


                Text(
                    text = buildAnnotatedString {
                        val dateTimeText =
                            stringResource(R.string.saved_location_cards_date_time_text)
                        append(dateTimeText)
                        append(formattedTime)
                    },
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeatherForecastCardPreview() = AndroidWeatherAppTheme {
    WeatherForecastCard(
        model = PreviewFakeData.fakeSavedWeatherModel,
        onRemove = {}
    )
}