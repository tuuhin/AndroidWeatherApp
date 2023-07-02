package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherConditionDto(
    @SerialName("air_quality") val airQuality: AirQualityDto? = null,
    @SerialName("cloud") val cloudCover: Float,
    @SerialName("condition") val condition: WeatherConditionDto,
    @SerialName("feelslike_c") val feelsLikeInCelsius: Float,
    @SerialName("feelslike_f") val feelsLikeFahrenheit: Float,
    @SerialName("gust_kph") val gustSpeedInKph: Float,
    @SerialName("gust_mph") val gustSpeedInMph: Float,
    @SerialName("humidity") val humidity: Float,
    @SerialName("is_day") val isDay: Float,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Float,
    @SerialName("precip_in") val precipitationInch: Float,
    @SerialName("precip_mm") val precipitationMM: Float,
    @SerialName("pressure_in") val pressureInch: Float,
    @SerialName("pressure_mb") val pressureMilliBar: Float,
    @SerialName("temp_c") val tempInCelsius: Float,
    @SerialName("temp_f") val tempInFahrenheit: Float,
    @SerialName("uv") val ultraviolet: Float,
    @SerialName("vis_km") val visKm: Float,
    @SerialName("vis_miles") val visMiles: Float,
    @SerialName("wind_degree") val windDegrees: Float,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("wind_kph") val windSpeedInKmh: Float,
    @SerialName("wind_mph") val windSpeedInMh: Float
)