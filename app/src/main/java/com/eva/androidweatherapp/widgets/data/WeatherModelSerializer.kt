package com.eva.androidweatherapp.widgets.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherModelSerializer(
    @SerialName("weather_condition") val condition: String,
    @SerialName("code") val code: Int,
    @SerialName("fTempC") val feelsLikeInCelsius: Float,
    @SerialName("fTempF") val feelsLikeFahrenheit: Float,
    @SerialName("rainInch") val precipitationInch: Float,
    @SerialName("rainMM") val precipitationMM: Float,
    @SerialName("pressurePpsI") val poundPerSquareInch: Float,
    @SerialName("pressureMB") val pressureMilliBar: Float,
    @SerialName("tempC") val tempInCelsius: Float,
    @SerialName("tempF") val tempInFahrenheit: Float,
    @SerialName("wSpeedKm") val windSpeedInKmh: Float,
    @SerialName("wSpeedM") val windSpeedInMh: Float,
    val name: String,
    val region: String,
    val humidity: Float
)