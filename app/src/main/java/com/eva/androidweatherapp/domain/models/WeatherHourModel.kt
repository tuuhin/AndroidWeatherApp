package com.eva.androidweatherapp.domain.models

import java.time.LocalDateTime

data class WeatherHourModel(
    val date: LocalDateTime,
    val code: Int,
    val image: Int,
    val tempC: Float,
    val tempF: Float,
    val windSpeedKmpH: Float,
    val windSpeedMH: Float
)
