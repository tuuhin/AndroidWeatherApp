package com.eva.androidweatherapp.presentation.feature_search.composables

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.presentation.composables.WeatherImage
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican

@Composable
fun WeatherForecastCard(
    model: SavedWeatherModel,
    modifier: Modifier = Modifier,
    isAmerican: Boolean = isCurrentLocaleAmerican(),
    onRemove: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
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
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = buildAnnotatedString {
                            if (model.region.isNotEmpty()) {
                                withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                                    append(model.region)
                                }
                                append(" , ")
                            }
                            append(model.country)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Normal
                    )
                }
                TextButton(
                    onClick = onRemove,
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Remove")
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(text = "Remove")
                }
            }
            Divider()
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
                        background = MaterialTheme.colorScheme.secondaryContainer,
                        onBackGround = MaterialTheme.colorScheme.onSecondaryContainer
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
                        text = if (isAmerican) "${model.tempInFahrenheit} F" else "${model.tempInCelsius} C",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 4.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Medium)) {
                                append("Feels Like: ")
                            }
                            if (isAmerican)
                                append("${model.feelsLikeFahrenheit} F")
                            else
                                append("${model.feelsLikeInCelsius} C")
                        },
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
        CityWeatherProperties(
            model = model,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun WeatherForecastCardPreview() {
    WeatherForecastCard(
        model = PreviewFakeData.fakeSavedWeatherModel,
        onRemove = {}
    )
}