package com.eva.androidweatherapp.domain.models

import com.eva.androidweatherapp.domain.utils.AirQualityOption

data class CurrentWeatherModel(
    val airQuality: AirQualityOption?,
    val airQualityIndex: Float,
    val cloudCover: Float,
    val condition: String,
    val image: Int,
    val code: Int,
    val feelsLikeInCelsius: Float,
    val feelsLikeFahrenheit: Float,
    val humidity: Float,
    val lastUpdated: String,
    val precipitationInch: Float,
    val precipitationMM: Float,
    val poundPerSquareInch: Float,
    val pressureMilliBar: Float,
    val tempInCelsius: Float,
    val tempInFahrenheit: Float,
    val ultraviolet: Float,
    val windDirection: String,
    val windSpeedInKmh: Float,
    val windSpeedInMh: Float
)
