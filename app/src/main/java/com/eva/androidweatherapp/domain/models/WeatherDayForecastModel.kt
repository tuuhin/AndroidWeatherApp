package com.eva.androidweatherapp.domain.models

import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDate

data class WeatherDayForecastModel(
    val date: LocalDate,
    val moonRise: String,
    val moonSet: String,
    val sunrise: String,
    val sunset: String,
    val hourCycle: List<WeatherHourModel>,
    val quality: AirQualityOption? = null,
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
    val ultralight: Float
)