package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherAstronomyDto(
//    @SerialName("is_moon_up") val isNight: Int,
//    @SerialName("is_sun_up") val isDay: Int,
//    @SerialName("moon_illumination") val illumination: String,
//    @SerialName("moon_phase") val moonPhase: String,
    @SerialName("moonrise") val moonRise: String,
    @SerialName("moonset") val moonSet: String,
    @SerialName("sunrise") val sunrise: String,
    @SerialName("sunset") val sunset: String
)