package com.eva.androidweatherapp.widgets.model

import androidx.annotation.DrawableRes

data class WidgetWeatherModel(
    val condition: String,
    @DrawableRes val image: Int,
    val feelsLikeInCelsius: Float,
    val feelsLikeFahrenheit: Float,
    val precipitationInch: Float,
    val precipitationMM: Float,
    val poundPerSquareInch: Float,
    val pressureMilliBar: Float,
    val tempInCelsius: Float,
    val tempInFahrenheit: Float,
    val windSpeedInKmh: Float,
    val windSpeedInMh: Float,
    val name: String,
    val region: String,
    val humidity: Float
)
