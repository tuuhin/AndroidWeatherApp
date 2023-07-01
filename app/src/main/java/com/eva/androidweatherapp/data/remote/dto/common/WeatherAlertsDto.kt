package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherAlertsDto(
    @SerialName("areas") val areas: String,
    @SerialName("category") val category: String,
    @SerialName("certainty") val certainty: String,
    @SerialName("desc") val desc: String,
    @SerialName("effective") val effective: String,
    @SerialName("event") val event: String,
    @SerialName("expires") val expires: String,
    @SerialName("headline") val headline: String,
    @SerialName("instruction") val instruction: String,
    @SerialName("msgtype") val type: String,
    @SerialName("note") val note: String,
    @SerialName("severity") val severity: String,
    @SerialName("urgency") val urgency: String
)