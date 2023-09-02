package com.eva.androidweatherapp.presentation.navigation

sealed class NavScreens(val route: String) {
    data object WeatherRouteScreen : NavScreens(route = "weather")
    data object CurrentWeatherScreen : NavScreens(route = "current")
    data object WeatherForecastScreen : NavScreens(route = "forecast")
    data object SearchCityScreen : NavScreens(route = "saved_location")
    data object AboutScreen : NavScreens(route = "about")
}