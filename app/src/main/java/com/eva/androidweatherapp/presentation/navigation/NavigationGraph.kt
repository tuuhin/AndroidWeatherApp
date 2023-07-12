package com.eva.androidweatherapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.eva.androidweatherapp.presentation.WeatherForecastViewModel
import com.eva.androidweatherapp.presentation.composables.LoadingPlaceholder
import com.eva.androidweatherapp.presentation.feature_current.CurrentWeatherRoute
import com.eva.androidweatherapp.presentation.feature_daily.WeatherGraphViewModel
import com.eva.androidweatherapp.presentation.feature_daily.WeekForecastRoute
import com.eva.androidweatherapp.presentation.feature_search.SearchLocationsRoute
import com.eva.androidweatherapp.presentation.feature_search.SearchLocationViewModel
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.UiEvents
import com.eva.androidweatherapp.presentation.util.sharedViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    val snackBarState = SnackbarHostState()


    CompositionLocalProvider(LocalSnackBarHostState provides snackBarState) {

        NavHost(
            navController = navController,
            startDestination = NavScreens.WeatherRouteScreen.route,
            modifier = modifier
        ) {
            navigation(
                startDestination = NavScreens.CurrentWeatherScreen.route,
                route = NavScreens.WeatherRouteScreen.route
            ) {
                composable(NavScreens.CurrentWeatherScreen.route) { entry ->

                    val viewModel = entry.sharedViewModel<WeatherForecastViewModel>(navController)

                    val content by viewModel.content.collectAsStateWithLifecycle()

                    LaunchedEffect(Unit) {
                        viewModel.uiEvent.collect { events ->
                            when (events) {
                                is UiEvents.ShowSnackBar -> snackBarState.showSnackbar(events.message)
                            }
                        }
                    }

                    LoadingPlaceholder(
                        showContent = content
                    ) { forecast ->
                        CurrentWeatherRoute(forecast = forecast,
                            onAbout = { navController.navigate(NavScreens.AboutScreen.route) },
                            onForecast = { navController.navigate(NavScreens.WeatherForecastScreen.route) },
                            onSearch = { navController.navigate(NavScreens.SearchCityScreen.route) })
                    }

                }
                composable(NavScreens.WeatherForecastScreen.route) { entry ->

                    val sharedViewModel =
                        entry.sharedViewModel<WeatherForecastViewModel>(navController)
                    val content by sharedViewModel.content.collectAsStateWithLifecycle()

                    val viewModel = viewModel<WeatherGraphViewModel>()
                    val state by viewModel.graphState.collectAsStateWithLifecycle()


                    LoadingPlaceholder(
                        showContent = content
                    ) { forecast ->
                        WeekForecastRoute(
                            forecastModel = forecast,
                            navigation = {
                                IconButton(
                                    onClick = { navController.navigate(NavScreens.CurrentWeatherScreen.route) }
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.ArrowBack,
                                        contentDescription = "Arrow Back"
                                    )
                                }
                            },
                            state = state,
                            onEvents = viewModel::onGraphEvents
                        )
                    }
                }

                composable(NavScreens.SearchCityScreen.route) {
                    val viewModel = koinViewModel<SearchLocationViewModel>()

                    val state by viewModel.searchBarState.collectAsStateWithLifecycle()
                    val results by viewModel.searchResults.collectAsStateWithLifecycle()
                    val savedLocations by viewModel.savedLocation.collectAsStateWithLifecycle()

                    LaunchedEffect(Unit) {
                        viewModel.uiEvents.collect { events ->
                            when (events) {
                                is UiEvents.ShowSnackBar -> snackBarState.showSnackbar(events.message)
                            }
                        }
                    }

                    SearchLocationsRoute(
                        state = state,
                        searchResults = results,
                        savedLocations = savedLocations,
                        onEvents = viewModel::onSearchBarEvents
                    )
                }

            }
            composable(NavScreens.AboutScreen.route) {
                AboutRoute(
                    navigationIcon = {
                        if (navController.previousBackStackEntry != null)
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Outlined.ArrowBack,
                                    contentDescription = "Back button"
                                )
                            }
                    }
                )
            }
        }
    }
}