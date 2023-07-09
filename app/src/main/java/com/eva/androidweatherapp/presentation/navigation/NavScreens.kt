package com.eva.androidweatherapp.presentation.navigation

sealed class NavScreens(val route: String) {
    object WeatherRouteScreen : NavScreens(route = "weather")
    object CurrentWeatherScreen : NavScreens(route = "current")
    object WeatherForecastScreen : NavScreens(route = "forecast")
    object WeatherAlertScreen : NavScreens(route = "alerts")

    object SearchCityScreen : NavScreens(route = "saved_location")

    object AboutScreen : NavScreens(route = "about")
}