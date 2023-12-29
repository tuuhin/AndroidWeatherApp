package com.eva.androidweatherapp.domain.models

import java.time.LocalDateTime

data class CurrentWeatherModel(
    val cloudCover: Float,
    val condition: String,
    val code: Int,
    val feelsLikeInCelsius: Float,
    val feelsLikeFahrenheit: Float,
    val humidity: Float,
    val lastUpdated: LocalDateTime,
    val precipitationInch: Float,
    val precipitationMM: Float,
    val poundPerSquareInch: Float,
    val pressureMilliBar: Float,
    val tempInCelsius: Float,
    val tempInFahrenheit: Float,
    val ultraviolet: Float,
    val windSpeedInKmh: Float,
    val windSpeedInMh: Float,
    val country: String,
    val name: String,
    val region: String,
)