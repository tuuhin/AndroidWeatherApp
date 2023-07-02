package com.eva.androidweatherapp.domain.models

data class WeatherForeCastModel(
    val currentWeatherModel: CurrentWeatherModel,
    val searchedLocationModel: SearchedLocationModel,
    val alerts: List<WeatherAlertModel>? = null,
    val forecast: List<WeatherDayDataModel>
)
