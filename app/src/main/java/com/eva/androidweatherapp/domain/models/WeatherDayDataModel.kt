package com.eva.androidweatherapp.domain.models

import java.time.LocalDate

data class WeatherDayDataModel(
    val date: LocalDate,
    val moonRise: String,
    val moonSet: String,
    val sunrise: String,
    val sunset: String,
    val hourCycle: List<WeatherHourModel>,
    val dayCycle: WeatherForecastDayModel
)