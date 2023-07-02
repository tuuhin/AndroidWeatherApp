package com.eva.androidweatherapp.domain.models

data class CurrentWeatherForecastBasicModel(
    val currentWeatherModel: CurrentWeatherModel,
    val searchedLocationModel: SearchedLocationModel
)