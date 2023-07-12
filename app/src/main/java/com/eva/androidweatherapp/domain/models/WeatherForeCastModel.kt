package com.eva.androidweatherapp.domain.models

data class WeatherForeCastModel(
    val current: CurrentWeatherModel,
    val forecast: List<WeatherDayForecastModel>
)
