package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityDto(
    @SerialName("co") val carbon: Double,
    @SerialName("no2") val nitrogen: Double,
    @SerialName("o3") val ozone: Double,
    @SerialName("pm10") val pm10: Int,
    @SerialName("pm2_5") val pm2_5: Double,
    @SerialName("so2") val sulphur: Double,
)