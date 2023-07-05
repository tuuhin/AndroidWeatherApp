package com.eva.androidweatherapp.domain.models

data class CurrentWeatherBasicModel(
    val currentWeather: CurrentWeatherModel,
    val location: SearchedLocationModel
)