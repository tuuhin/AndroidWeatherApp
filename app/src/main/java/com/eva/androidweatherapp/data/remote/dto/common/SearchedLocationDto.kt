package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedLocationDto(
    @SerialName("country") val country: String,
    @SerialName("lat") val latitude: Double,
    @SerialName("localtime") val time: String,
    @SerialName("localtime_epoch") val epoch: Int,
    @SerialName("lon") val longitude: Double,
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("tz_id") val timeZone: String
)