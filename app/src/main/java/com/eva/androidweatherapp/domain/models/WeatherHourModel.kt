package com.eva.androidweatherapp.domain.models

import androidx.annotation.DrawableRes
import java.time.LocalDateTime

data class WeatherHourModel(
    val date: LocalDateTime,
    val code: Int,
    @DrawableRes val image: Int,
    val tempC: Float,
    val tempF: Float,
    val windSpeedKmpH: Float,
    val windSpeedMH: Float
)
