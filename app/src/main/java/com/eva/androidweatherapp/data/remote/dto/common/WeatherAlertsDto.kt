package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherAlertsDto(
    @SerialName("areas") val areas: String? = null,
    @SerialName("category") val category: String? = null,
    @SerialName("certainty") val certainty: String? = null,
    @SerialName("desc") val desc: String,
    @SerialName("effective") val effective: String? = null,
    @SerialName("event") val event: String? = null,
    @SerialName("expires") val expires: String,
    @SerialName("headline") val headline: String,
    @SerialName("instruction") val instruction: String,
    @SerialName("msgtype") val type: String? = null,
    @SerialName("note") val note: String? = null,
    @SerialName("severity") val severity: String? = null,
    @SerialName("urgency") val urgency: String? = null
)