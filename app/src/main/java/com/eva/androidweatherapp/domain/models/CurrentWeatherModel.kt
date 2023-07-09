package com.eva.androidweatherapp.domain.models

import androidx.annotation.DrawableRes
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDateTime

data class CurrentWeatherModel(
    val airQuality: AirQualityOption?,
    val airQualityIndex: Float,
    val cloudCover: Float,
    val condition: String,
    @DrawableRes val image: Int,
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
    val windDirection: String,
    val windSpeedInKmh: Float,
    val windSpeedInMh: Float,
    val country: String,
    val name: String,
    val region: String,
)