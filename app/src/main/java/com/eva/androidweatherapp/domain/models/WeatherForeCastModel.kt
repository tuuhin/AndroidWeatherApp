package com.eva.androidweatherapp.domain.models

data class WeatherForeCastModel(
    val current: CurrentWeatherModel,
    val alerts: List<WeatherAlertModel>? = null,
    val forecast: List<WeatherDayForecastModel>
)
