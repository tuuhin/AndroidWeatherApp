package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AirQualityDto(
    @SerialName("co") val carbon: Float,
    @SerialName("no2") val nitrogen: Float,
    @SerialName("o3") val ozone: Float,
    @SerialName("pm10") val pm10: Int,
    @SerialName("pm2_5") val pm2_5: Float,
    @SerialName("so2") val sulphur: Float,
    @SerialName("us-epa-index") val epaIndex:Int,
)