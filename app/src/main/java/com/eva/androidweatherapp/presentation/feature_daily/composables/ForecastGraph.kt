package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_daily.WeatherGraphType
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import kotlin.math.abs
import kotlin.random.Random

@OptIn(ExperimentalTextApi::class)
@Composable
fun ForeCastGraph(
    forecast: WeatherForeCastModel,
    onTypeChanged: (WeatherGraphType) -> Unit,
    isDropdownExpanded: Boolean,
    onDropDownDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    axisColor: Color = MaterialTheme.colorScheme.secondary,
    chartColor: Color = MaterialTheme.colorScheme.primary,
    type: WeatherGraphType = WeatherGraphType.AVG_TEMPERATURE,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    numberStyle: TextStyle = MaterialTheme.typography.labelMedium,
) {

    val listOfTypes = remember { WeatherGraphType.values() }

    var dropDownOffset by remember { mutableStateOf(DpOffset.Zero) }

    val textMeasurer = rememberTextMeasurer()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Graph", style = MaterialTheme.typography.titleMedium)
            Box(
                modifier = Modifier
            ) {
                TextButton(
                    onClick = onDropDownDismiss,
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures { offset ->
                            dropDownOffset = DpOffset(x = offset.x.toDp(), y = offset.y.toDp())
                        }
                    }
                ) {
                    Text(text = type.type)
                }
                DropdownMenu(
                    expanded = isDropdownExpanded,
                    onDismissRequest = onDropDownDismiss,
                    offset = dropDownOffset
                ) {
                    listOfTypes.forEach { type ->
                        DropdownMenuItem(text = {
                            Text(
                                text = type.type,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }, onClick = { onTypeChanged(type) })
                    }
                }
            }
        }
        Card(
            modifier = modifier.fillMaxSize()
        ) {
            Crossfade(targetState = type) { graph ->
                Spacer(modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize()
                    .drawWithCache {

                        val listOffset = mutableListOf<GraphPoints>()

                        val minOfType = when (graph) {
                            WeatherGraphType.AVG_TEMPERATURE -> {
                                if (localeAmerican)
                                    forecast.forecast.minOf { it.avgTempInFahrenheit }
                                else forecast.forecast.minOf { it.avgTempInCelsius }
                            }

                            WeatherGraphType.MAX_TEMPERATURE -> {
                                if (localeAmerican) forecast.forecast.minOf { it.maxTempInFahrenheit }
                                else forecast.forecast.minOf { it.maxTempInCelsius }
                            }

                            WeatherGraphType.MIN_TEMPERATURE -> {
                                if (localeAmerican) forecast.forecast.minOf { it.minTempInFahrenheit }
                                else forecast.forecast.minOf { it.minTempInCelsius }
                            }

                            WeatherGraphType.PRECIPITATION -> forecast.forecast.minOf { it.totalPrecipitationInMm }

                            WeatherGraphType.WIND_SPEED -> {
                                if (localeAmerican)
                                    forecast.forecast.minOf { it.maxWindSpeedInMph }
                                else forecast.forecast.minOf { it.maxWindSpeedInKmpH }
                            }

                            WeatherGraphType.HUMIDITY -> forecast.forecast.minOf { it.avgHumidity }
                        }

                        val maxOfType = when (graph) {
                            WeatherGraphType.AVG_TEMPERATURE -> {
                                if (localeAmerican)
                                    forecast.forecast.maxOf { it.avgTempInFahrenheit }
                                else forecast.forecast.maxOf { it.avgTempInCelsius }
                            }

                            WeatherGraphType.MAX_TEMPERATURE -> {
                                if (localeAmerican) forecast.forecast.maxOf { it.maxTempInFahrenheit }
                                else forecast.forecast.maxOf { it.maxTempInCelsius }
                            }

                            WeatherGraphType.MIN_TEMPERATURE -> {
                                if (localeAmerican) forecast.forecast.minOf { it.minTempInFahrenheit }
                                else forecast.forecast.maxOf { it.minTempInCelsius }
                            }

                            WeatherGraphType.PRECIPITATION -> forecast.forecast.maxOf { it.totalPrecipitationInMm }

                            WeatherGraphType.WIND_SPEED -> {
                                if (localeAmerican)
                                    forecast.forecast.maxOf { it.maxWindSpeedInMph }
                                else forecast.forecast.maxOf { it.maxWindSpeedInKmpH }
                            }

                            WeatherGraphType.HUMIDITY -> forecast.forecast.maxOf { it.avgHumidity }
                        }


                        val blockWidth = size.width / (forecast.forecast.size)

                        val maxOfTypeAbs = abs(maxOfType)
                        val minOfTypeAbs = abs(minOfType)
                        val range = maxOfTypeAbs + minOfTypeAbs


                        val path = Path().apply {
                            forecast.forecast.forEachIndexed { idx, weather ->
                                val currentValue = when (graph) {
                                    WeatherGraphType.AVG_TEMPERATURE -> if (localeAmerican)
                                        weather.avgTempInFahrenheit
                                    else weather.avgTempInCelsius

                                    WeatherGraphType.MAX_TEMPERATURE -> if (localeAmerican)
                                        weather.maxTempInFahrenheit else weather.maxTempInCelsius

                                    WeatherGraphType.MIN_TEMPERATURE -> if (localeAmerican)
                                        weather.minTempInFahrenheit
                                    else weather.minTempInCelsius

                                    WeatherGraphType.PRECIPITATION -> if (localeAmerican)
                                        weather.totalPrecipitationInInch
                                    else weather.totalPrecipitationInMm

                                    WeatherGraphType.WIND_SPEED -> if (localeAmerican)
                                        weather.maxWindSpeedInMph
                                    else weather.maxWindSpeedInKmpH

                                    WeatherGraphType.HUMIDITY -> weather.avgHumidity
                                }

                                val graphHeight =
                                    size.height - ((currentValue + minOfType) * size.height / range)
                                if (idx == 0)
                                    moveTo(x = 0f, y = graphHeight)
                                else
                                    lineTo(x = blockWidth * idx, graphHeight)
                                listOffset.add(
                                    GraphPoints(
                                        text = currentValue,
                                        offset = Offset(
                                            x = blockWidth * idx,
                                            y = graphHeight
                                        )
                                    )
                                )
                            }
                        }

                        onDrawWithContent {

                            drawLine(
                                color = axisColor,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height + 10),
                                cap = StrokeCap.Round,
                                strokeWidth = 2f,
                            )
                            drawLine(
                                color = axisColor,
                                start = Offset(-10f, size.height),
                                end = Offset(size.width + 10f, size.height),
                                cap = StrokeCap.Round,
                                strokeWidth = 2f
                            )

                            if (minOfType <= 0f) {
                                val yAxis = (maxOfTypeAbs / range) * size.height
                                drawLine(
                                    color = axisColor,
                                    start = Offset(-10f, yAxis),
                                    end = Offset(size.width + 10f, yAxis),
                                    cap = StrokeCap.Round,
                                    strokeWidth = 1f,
                                    alpha = 0.8f
                                )
                            }


                            drawPath(
                                path = path,
                                color = chartColor,
                                style = Stroke(
                                    width = 4f,
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Bevel
                                )
                            )

                            listOffset.forEach { point ->

                                drawText(
                                    textMeasurer = textMeasurer,
                                    text = "${point.text}",
                                    topLeft = point.offset,
                                    style = numberStyle,
                                    maxLines = 1,
                                    overflow = TextOverflow.Visible,
                                    softWrap = true
                                )
                                drawCircle(
                                    color = chartColor,
                                    radius = 6f,
                                    center = point.offset
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

class PreviewForeCastParams : CollectionPreviewParameterProvider<WeatherForeCastModel>(
    listOf(
        PreviewFakeData.fakeForeCastModel.copy(forecast = List(5) { idx ->
            PreviewFakeData.fakeWeatherDayDataModel.copy(
                avgHumidity = Random(idx).nextInt(0, 100).toFloat()
            )
        }
        )
    )
)


data class GraphPoints(
    val text: Float,
    val offset: Offset
)


@Preview(showBackground = true)
@Composable
fun ForeCastGraphPreview(
    @PreviewParameter(PreviewForeCastParams::class)
    forecast: WeatherForeCastModel
) {
    ForeCastGraph(
        forecast = forecast,
        modifier = Modifier
            .aspectRatio(1.5f)
            .padding(10.dp),
        type = WeatherGraphType.HUMIDITY,
        onTypeChanged = {},
        isDropdownExpanded = true,
        onDropDownDismiss = {}
    )
}