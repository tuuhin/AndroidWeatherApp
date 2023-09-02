package com.eva.androidweatherapp.presentation.feature_daily

import android.content.res.Configuration
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_daily.composables.ForeCastGraph
import com.eva.androidweatherapp.presentation.feature_daily.composables.WeatherDayCard
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun WeekForecastRoute(
    forecastModel: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    navigation: (@Composable () -> Unit)? = null,
    onEvents: (GraphInteractionEvents) -> Unit,
    state: GraphInteractionState,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Forecast") },
                navigationIcon = navigation ?: {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                ),
            )
        },
        contentWindowInsets = WindowInsets.navigationBars,
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) { scPadding ->
        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            ForeCastGraph(
                forecast = forecastModel,
                state = state,
                onEvents = onEvents,
                modifier = Modifier.aspectRatio(1.5f)
            )
            Text(
                text = "Forecast",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(forecastModel.forecast) { _, forecast ->
                    WeatherDayCard(
                        forecastModel = forecast,
                        modifier = Modifier
                            .wrapContentSize()
                            .animateItemPlacement(tween(600))
                    )
                }
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE
)
@Preview(
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeekForeCastRoutePreview() {
    AndroidWeatherAppTheme {
        WeekForecastRoute(
            forecastModel = PreviewFakeData.fakeForeCastModel,
            navigation = {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Arrow Symbol"
                )
            },
            state = GraphInteractionState(),
            onEvents = {}
        )
    }
}