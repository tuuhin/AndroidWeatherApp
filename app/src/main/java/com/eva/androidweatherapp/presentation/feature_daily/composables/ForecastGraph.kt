package com.eva.androidweatherapp.presentation.feature_daily.composables

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_daily.GraphInteractionEvents
import com.eva.androidweatherapp.presentation.feature_daily.GraphInteractionState
import com.eva.androidweatherapp.presentation.feature_daily.WeatherGraphType
import com.eva.androidweatherapp.presentation.util.GraphPoints
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import kotlin.math.abs
import kotlin.random.Random

@OptIn(ExperimentalTextApi::class)
@Composable
fun ForeCastGraph(
    forecast: WeatherForeCastModel,
    state: GraphInteractionState,
    onEvents: (GraphInteractionEvents) -> Unit,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    axisColor: Color = MaterialTheme.colorScheme.tertiary,
    chartColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    numberStyle: TextStyle = MaterialTheme.typography.labelMedium,
) {

    val listOfTypes = remember { WeatherGraphType.values() }

    var dropDownOffset by remember { mutableStateOf(DpOffset.Zero) }

    val textMeasurer = rememberTextMeasurer()

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Graph",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Box(
                modifier = Modifier.wrapContentWidth()
            ) {
                TextButton(
                    onClick = { onEvents(GraphInteractionEvents.OnToggleDropDown) },
                    modifier = Modifier
                        .pointerInput(Unit) {
                            detectTapGestures { offset ->
                                dropDownOffset =
                                    DpOffset(x = offset.x.toDp(), y = offset.y.toDp())
                            }
                        }
                ) {
                    Text(
                        text = state.graph.typeWithUnit(localeAmerican),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
                DropdownMenu(
                    expanded = state.isDropDownOpen,
                    onDismissRequest = { onEvents(GraphInteractionEvents.OnToggleDropDown) },
                    offset = dropDownOffset
                ) {
                    listOfTypes.forEach { type ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = type.typeWithUnit(localeAmerican),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            onClick = { onEvents(GraphInteractionEvents.OngraphSelect(type)) },
                            colors = MenuDefaults.itemColors(
                                textColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        )
                    }
                }
            }
        }
        OutlinedCard(
            modifier = modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary)
        ) {
            Crossfade(
                targetState = state.graph,
                modifier = Modifier.fillMaxWidth(),
                label = "Graph Transitions"
            ) { graph ->
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .fillMaxSize()
                        .drawWithCache {

                            val listOffset = mutableListOf<GraphPoints>()

                            val minOfType = graph.minOfForecast(forecast, localeAmerican)
                            val maxOfType = graph.maxOfForecast(forecast, localeAmerican)


                            val blockWidth = size.width / (forecast.forecast.size)

                            val maxOfTypeAbs = abs(maxOfType)
                            val minOfTypeAbs = abs(minOfType)

                            val range = maxOfTypeAbs + minOfTypeAbs

                            val path = Path()
                                .apply {
                                    forecast.forecast.forEachIndexed { idx, weather ->
                                        val currentValue =
                                            graph.valueFromForeCast(weather, localeAmerican)

                                        val graphHeight =
                                            size.height - ((currentValue + minOfType) * size.height / range)

                                        if (idx == 0)
                                            moveTo(x = 0f, y = graphHeight)

                                        lineTo(blockWidth * idx, graphHeight)

                                        listOffset.add(
                                            GraphPoints(
                                                value = currentValue,
                                                coordinate =
                                                Offset(x = blockWidth * idx, y = graphHeight)
                                            )
                                        )
                                    }
                                }

                            onDrawBehind {

                                drawLine(
                                    color = axisColor,
                                    start = Offset(0f, 0f),
                                    end = Offset(0f, size.height + 10),
                                    cap = StrokeCap.Round,
                                    strokeWidth = 4f,
                                )
                                drawLine(
                                    color = axisColor,
                                    start = Offset(-10f, size.height),
                                    end = Offset(size.width + 10f, size.height),
                                    cap = StrokeCap.Round,
                                    strokeWidth = 4f
                                )

                                drawText(
                                    textMeasurer = textMeasurer,
                                    text = "Scale/Date",
                                    topLeft = Offset(-20f, size.height),
                                    style = numberStyle.copy(color = textColor),
                                    maxLines = 1,
                                    overflow = TextOverflow.Visible,
                                    softWrap = true
                                )

                                if (minOfType <= 0f) {
                                    val yAxis = (maxOfTypeAbs / range) * size.height
                                    drawLine(
                                        color = axisColor,
                                        start = Offset(-10f, yAxis),
                                        end = Offset(size.width + 10f, yAxis),
                                        cap = StrokeCap.Round,
                                        strokeWidth = 2f,
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

                                if (!listOffset.all { it.value == 0f }) {
                                    listOffset.forEach { point ->
                                        drawCircle(
                                            color = chartColor,
                                            radius = 6f,
                                            center = point.coordinate
                                        )

                                        drawText(
                                            textMeasurer = textMeasurer,
                                            text = "${point.value}",
                                            topLeft = point.coordinate,
                                            style = numberStyle,
                                            maxLines = 1,
                                            overflow = TextOverflow.Visible,
                                            softWrap = false
                                        )

                                    }
                                } else {
                                    drawText(
                                        textMeasurer = textMeasurer,
                                        text = "No data for these days",
                                        topLeft = Offset(center.x, center.y),
                                        style = numberStyle.copy(color = textColor),
                                        maxLines = 1,
                                        overflow = TextOverflow.Visible,
                                        softWrap = false
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
        PreviewFakeData.fakeForeCastModel
            .copy(forecast = List(7) { idx ->
                PreviewFakeData.fakeWeatherDayDataModel.copy(
                    avgHumidity = Random(idx).nextInt(0, 100).toFloat()
                )
            }
            )
    )
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
        state = GraphInteractionState(), onEvents = {}
    )
}