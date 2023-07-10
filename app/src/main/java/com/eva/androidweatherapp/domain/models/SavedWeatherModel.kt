package com.eva.androidweatherapp.domain.models

import androidx.annotation.DrawableRes

data class SavedWeatherModel(
    val id: Int? = null,
    val condition: String,
    val code: Int,
    @DrawableRes val image: Int,
    val feelsLikeInCelsius: Float,
    val feelsLikeFahrenheit: Float,
    val humidity: Float,
    val precipitationInch: Float,
    val precipitationMM: Float,
    val pressureMilliBar: Float,
    val tempInCelsius: Float,
    val tempInFahrenheit: Float,
    val windSpeedInKmh: Float,
    val windSpeedInMh: Float,
    val country: String,
    val name: String,
    val region: String,
)
