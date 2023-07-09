package com.eva.androidweatherapp.data.remote.dto.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.eva.androidweatherapp.data.remote.dto.common.CurrentWeatherConditionDto
import com.eva.androidweatherapp.data.remote.dto.common.SearchedLocationDto

@Serializable
data class WeatherCurrentDataDto(
    @SerialName("location") val location: SearchedLocationDto,
    @SerialName("current") val weather: CurrentWeatherConditionDto
)
