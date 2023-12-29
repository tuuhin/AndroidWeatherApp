package com.eva.androidweatherapp.presentation.feature_daily.composables

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_daily.WeatherGraphType
import com.eva.androidweatherapp.presentation.util.GraphPoints
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.isCurrentLocaleAmerican
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import com.eva.androidweatherapp.utils.weekDayShort
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun ForeCastGraph(
    forecast: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    localeAmerican: Boolean = isCurrentLocaleAmerican(),
    axisColor: Color = MaterialTheme.colorScheme.tertiary,
    chartColor: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    numberStyle: TextStyle = MaterialTheme.typography.labelLarge,
    axisStyle: TextStyle = MaterialTheme.typography.labelMedium,
) {

    val textMeasurer = rememberTextMeasurer()

    var selectedGraph by rememberSaveable(saver = CustomSaver) {
        mutableStateOf(WeatherGraphType.AVG_TEMPERATURE)
    }

    var isDropDownOpen by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
    ) {
        ForecastGraphHeader(
            isDropDownOpen = isDropDownOpen,
            selected = selectedGraph,
            onToggleGraphState = { isDropDownOpen = !isDropDownOpen },
            onGraphTypeChanged = { selectedGraph = it },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedCard(
            modifier = modifier.fillMaxSize(),
            shape = MaterialTheme.shapes.small,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
            )
        ) {
            Crossfade(
                targetState = selectedGraph,
                modifier = Modifier.fillMaxWidth(),
                label = "Graph Transitions"
            ) { graph ->
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .fillMaxSize()
                        .drawWithCache {

                            val graphPoints = mutableListOf<GraphPoints>()

                            val minOfType = graph.minOfForecast(forecast, localeAmerican)
                            val maxOfType = graph.maxOfForecast(forecast, localeAmerican)

                            val extraChunkHeight = 10f

                            val availableHeight = size.height - extraChunkHeight

                            val blockWidth = size.width / (forecast.forecast.size)

                            val maxOfTypeAbs = abs(maxOfType)
                            val minOfTypeAbs = abs(minOfType)

                            val range = maxOfTypeAbs + minOfTypeAbs

                            val path = Path().apply {
                                forecast.forecast.forEachIndexed { idx, weather ->
                                    val currentValue =
                                        graph.valueOfForecast(weather, localeAmerican)

                                    val graphHeight =
                                        size.height - ((currentValue + minOfType) * availableHeight / range)

                                    if (idx == 0) moveTo(x = blockWidth * .5f, y = graphHeight)


                                    val text = textMeasurer.measure(
                                        text = "$currentValue",
                                        density = Density(density),
                                        style = numberStyle.copy(color = textColor),
                                        maxLines = 1,
                                        overflow = TextOverflow.Visible,
                                        softWrap = false
                                    )
                                    val textWidth = text.size.width
                                    val textHeight = text.size.height

                                    val pointOffset = Offset(
                                        x = blockWidth * idx + blockWidth * .5f,
                                        y = graphHeight
                                    )

                                    val textOffset = Offset(
                                        x = blockWidth * idx + blockWidth * .5f - textWidth * .5f,
                                        y = graphHeight - textHeight
                                    )

                                    graphPoints.add(
                                        GraphPoints(
                                            value = currentValue,
                                            pointCoordinate = pointOffset,
                                            textCoordinate = textOffset
                                        )
                                    )
                                    lineTo(pointOffset.x, pointOffset.y)
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

                                forecast.forecast.forEachIndexed { idx, weather ->
                                    val weekDayText = weather.date.weekDayShort
                                    val weekDayMeasure = textMeasurer.measure(
                                        weekDayText,
                                        style = axisStyle.copy(color = axisColor),
                                        maxLines = 1,
                                    )

                                    val textSizeOffset =
                                        Offset(x = weekDayMeasure.size.width * .5f, 0f)

                                    val textPos = Offset(
                                        x = blockWidth * idx + blockWidth * .5f,
                                        y = size.height
                                    )

                                    drawText(
                                        weekDayMeasure,
                                        topLeft = textPos - textSizeOffset
                                    )

                                }

                                if (!graphPoints.all { it.value == 0f }) {
                                    graphPoints.forEach { point ->
                                        drawCircle(
                                            color = chartColor,
                                            radius = 6f,
                                            center = point.pointCoordinate
                                        )

                                        drawText(
                                            textMeasurer = textMeasurer,
                                            text = "${point.value}",
                                            topLeft = point.textCoordinate,
                                            style = numberStyle.copy(color = textColor),
                                            maxLines = 1,
                                            overflow = TextOverflow.Visible,
                                            softWrap = false
                                        )

                                    }
                                } else {

                                    val noDataText = textMeasurer.measure(
                                        "Zero for the next three days",
                                        style = numberStyle.copy(color = textColor),
                                        maxLines = 1,
                                        overflow = TextOverflow.Visible,
                                        softWrap = false
                                    )

                                    val textSizeOffset = Offset(
                                        noDataText.size.width * .5f,
                                        noDataText.size.height * .5f
                                    )

                                    drawText(
                                        noDataText,
                                        topLeft = Offset(center.x, center.y) - textSizeOffset,
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

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun ForeCastGraphPreview(
    @PreviewParameter(PreviewForeCastParams::class)
    forecast: WeatherForeCastModel
) = AndroidWeatherAppTheme {

    ForeCastGraph(
        forecast = forecast,
        modifier = Modifier
            .aspectRatio(1.5f)
            .padding(10.dp),
    )
}

object CustomSaver : Saver<MutableState<WeatherGraphType>, String> {
    override fun restore(value: String): MutableState<WeatherGraphType> =
        mutableStateOf(WeatherGraphType.valueOf(value))

    override fun SaverScope.save(value: MutableState<WeatherGraphType>): String =
        value.value.name


}