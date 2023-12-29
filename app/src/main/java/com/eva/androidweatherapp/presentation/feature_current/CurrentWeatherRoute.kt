package com.eva.androidweatherapp.presentation.feature_current

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_current.composables.CurrentDayWeatherProperties
import com.eva.androidweatherapp.presentation.feature_current.composables.CurrentWeatherData
import com.eva.androidweatherapp.presentation.feature_current.composables.WeatherHourlyData
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import com.eva.androidweatherapp.utils.toReadableDateWithWeekDay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherRoute(
    forecast: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    onAbout: (() -> Unit)? = null,
    onForecast: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
    snackBarHostState: SnackbarHostState = LocalSnackBarHostState.current
) {
    val formattedTime by remember {
        derivedStateOf(forecast.current.lastUpdated::toReadableDateWithWeekDay)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                actions = {
                    IconButton(onClick = onAbout ?: {}) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onSearch ?: {},
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search"
                        )
                    }
                },
            )
        },
        contentWindowInsets = WindowInsets.navigationBars
    ) { scPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            contentPadding = scPadding,
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(id = R.dimen.scaffold_horizontal_padding))
        ) {
            item {
                CurrentWeatherData(model = forecast)
            }
            item {
                WeatherHourlyData(
                    hourlyWeather = forecast.firstForecastHourCycles,
                    modifier = Modifier
                        .height(180.dp),
                    onForecast = onForecast ?: {}
                )
            }
            item {
                CurrentDayWeatherProperties(
                    forecast = forecast.firstForecast,
                    current = forecast.current,
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
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
fun CurrentWeatherRoutePreview() = AndroidWeatherAppTheme {
    CurrentWeatherRoute(forecast = PreviewFakeData.fakeForeCastModel)
}


