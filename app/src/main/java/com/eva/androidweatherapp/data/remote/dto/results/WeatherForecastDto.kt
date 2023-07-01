package com.eva.androidweatherapp.data.remote.dto.results

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.eva.androidweatherapp.data.remote.dto.common.CurrentWeatherConditionDto
import com.eva.androidweatherapp.data.remote.dto.common.SearchedLocationDto
import com.eva.androidweatherapp.data.remote.dto.common.WeatherAlertsDto
import com.eva.androidweatherapp.data.remote.dto.common.WeatherForeCastDayDto

@Serializable
data class WeatherForecastDto(
    @SerialName("current") val current: CurrentWeatherConditionDto,
    @SerialName("forecast") val forecast: List<WeatherForeCastDayDto>,
    @SerialName("location") val location: SearchedLocationDto,
    @SerialName("alerts") val alerts: List<WeatherAlertsDto>
)