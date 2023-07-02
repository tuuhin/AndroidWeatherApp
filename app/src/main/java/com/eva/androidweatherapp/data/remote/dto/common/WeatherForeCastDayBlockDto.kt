package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForeCastDayBlockDto(
    @SerialName("astro") val astronomical: WeatherAstronomyDto,
    @SerialName("date") val date: String,
    @SerialName("date_epoch") val epoch: Int,
    @SerialName("day") val day: WeatherForeCastDayDataDto,
    @SerialName("hour") val hour: List<WeatherForeCastHourDto>
)