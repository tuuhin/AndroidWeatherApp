package com.eva.androidweatherapp.presentation.feature_current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_current.composables.CurrentWeatherData
import com.eva.androidweatherapp.presentation.composables.CurrentWeatherProperties
import com.eva.androidweatherapp.presentation.feature_current.composables.WeatherAstronomicalData
import com.eva.androidweatherapp.presentation.feature_current.composables.WeatherHourlyData
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherRoute(
    forecast: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    onAbout: (() -> Unit)? = null,
    onForecast: (() -> Unit)? = null,
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
            TopAppBar(
                title = {
                    Text(text = formattedTime, style = MaterialTheme.typography.titleMedium)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                ),
                actions = {
                    IconButton(onClick = onAbout ?: {}) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information"
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) { scPadding ->
        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            CurrentWeatherData(
                model = forecast,
                modifier = Modifier
                    .weight(.45f)
                    .padding(horizontal = 10.dp)
            )
            WeatherHourlyData(
                hourlyWeather = forecast.forecast.first().hourCycle,
                modifier = Modifier
                    .weight(.3f)
                    .padding(horizontal = 4.dp),
                onForecast = onForecast ?: {}
            )
            Card(
                modifier = Modifier
                    .weight(.35f)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                shape = MaterialTheme.shapes.extraLarge.copy(
                    bottomEnd = CornerSize(0.dp),
                    bottomStart = CornerSize(0.dp)
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    CurrentWeatherProperties(
                        forecastDay = forecast.forecast.first(),
                        current = forecast.current,
                        modifier = Modifier.fillMaxWidth()
                    )
                    WeatherAstronomicalData(
                        dayModel = forecast.forecast.first()
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun CurrentWeatherRoutePreview() {
    CurrentWeatherRoute(forecast = PreviewFakeData.fakeForeCastModel)
}

