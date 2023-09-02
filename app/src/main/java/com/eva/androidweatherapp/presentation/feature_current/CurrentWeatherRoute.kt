package com.eva.androidweatherapp.presentation.feature_current

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_current.composables.CurrentWeatherData
import com.eva.androidweatherapp.presentation.feature_current.composables.CurrentDayWeatherProperties
import com.eva.androidweatherapp.presentation.feature_current.composables.WeatherHourlyData
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.requestBackgroundLocationDialog
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme
import java.time.format.DateTimeFormatter

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
        derivedStateOf {
            val formatter = DateTimeFormatter.ofPattern("EEE, MMMM d")
            forecast.current.lastUpdated.format(formatter)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = formattedTime, style = MaterialTheme.typography.titleMedium)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
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
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        contentWindowInsets = WindowInsets.navigationBars
    ) { scPadding ->

        requestBackgroundLocationDialog()

        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize(),
        ) {
            CurrentWeatherData(
                model = forecast,
                modifier = Modifier
                    .weight(.35f)
                    .padding(horizontal = 10.dp)
            )
            WeatherHourlyData(
                hourlyWeather = forecast.forecast.first().hourCycle,
                modifier = Modifier
                    .height(180.dp)
                    .padding(horizontal = 4.dp),
                onForecast = onForecast ?: {}
            )
            CurrentDayWeatherProperties(
                forecast = forecast.forecast.first(),
                current = forecast.current,
                modifier = Modifier
                    .padding(vertical = 2.dp, horizontal = 4.dp)
                    .weight(.4f)
            )
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
fun CurrentWeatherRoutePreview() {
    AndroidWeatherAppTheme {
        CurrentWeatherRoute(
            forecast = PreviewFakeData.fakeForeCastModel
        )
    }
}

