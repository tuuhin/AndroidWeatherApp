package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherConditionDto(
    @SerialName("code") val weatherCode: Int,
    @SerialName("icon") val icon: String,
    @SerialName("text") val text: String
)