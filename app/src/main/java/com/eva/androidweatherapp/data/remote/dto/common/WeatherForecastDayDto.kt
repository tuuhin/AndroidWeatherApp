package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForeCastDayDto(
    @SerialName("forecastday") val forecast: List<WeatherForeCastDayBlockDto>,
)