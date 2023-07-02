package com.eva.androidweatherapp.domain.models

data class WeatherForecastDayModel(
    val quality: String? = null,
    val avgHumidity: Float,
    val avgTempInCelsius: Float,
    val avgTempInFahrenheit: Float,
    val code: Int,
    val image: Int,
    val weather: String,
    val rainPercentage: Float,
    val snowPercentage: Float,
    val maxTempInCelsius: Float,
    val maxTempInFahrenheit: Float,
    val maxWindSpeedInKmpH: Float,
    val maxWindSpeedInMph: Float,
    val minTempInCelsius: Float,
    val minTempInFahrenheit: Float,
    val totalPrecipitationInInch: Float,
    val totalPrecipitationInMm: Float,
    val totalSnowInCm: Float,
    val ultralight: Float
)
