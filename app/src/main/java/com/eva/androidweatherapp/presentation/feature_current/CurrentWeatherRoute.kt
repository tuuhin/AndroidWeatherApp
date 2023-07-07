package com.eva.androidweatherapp.presentation.feature_current

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_current.composables.CurrentWeatherData
import com.eva.androidweatherapp.presentation.composables.CurrentWeatherProperties
import com.eva.androidweatherapp.presentation.feature_current.composables.WeatherAstronomicalData
import com.eva.androidweatherapp.presentation.feature_current.composables.WeatherHourlyData
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherRoute(
    model: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    onAbout: (() -> Unit)? = null,
) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val formattedTime by remember {
        derivedStateOf {
            val formatter = DateTimeFormatter.ofPattern("EEE, MMMM d")
            model.searchedLocationModel.time.format(formatter)
        }
    }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { /*TODO*/ },
        gesturesEnabled = true,

        ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = formattedTime) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Outlined.Menu,
                                contentDescription = "Drawer Menu"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
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
                    model = model,
                    modifier = Modifier
                        .weight(.35f)
                        .padding(horizontal = 10.dp)
                )
                WeatherHourlyData(
                    hourlyWeather = model.forecast.first().hourCycle,
                    modifier = Modifier
                        .weight(.3f)
                        .padding(horizontal = 4.dp),
                    onDetails = {}
                )
                Card(
                    modifier = Modifier
                        .weight(.45f)
                        .fillMaxSize(),
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
                            .padding(16.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        CurrentWeatherProperties(
                            forecast = model.forecast.first().dayCycle,
                            current = model.currentWeatherModel,
                            modifier = Modifier.fillMaxWidth()
                        )
                        WeatherAstronomicalData(
                            dayModel = model.forecast.first()
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CurrentWeatherRoutePreview() {
    CurrentWeatherRoute(model = PreviewFakeData.fakeForeCastModel)
}

