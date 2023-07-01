package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherConditionDto(
    @SerialName("air_quality") val airQuality: AirQualityDto,
    @SerialName("cloud") val cloudCover: Int,
    @SerialName("condition") val condition: WeatherConditionDto,
    @SerialName("feelslike_c") val feelsLikeInCelsius: Double,
    @SerialName("feelslike_f") val feelsLikeFahrenheit: Double,
    @SerialName("gust_kph") val gustSpeedInKph: Double,
    @SerialName("gust_mph") val gustSpeedInMph: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("is_day") val isDay: Int,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Int,
    @SerialName("precip_in") val precipitationInch: Int,
    @SerialName("precip_mm") val precipitationMM: Int,
    @SerialName("pressure_in") val pressure_in: Double,
    @SerialName("pressure_mb") val pressureMilliBar: Int,
    @SerialName("temp_c") val tempInCelsius: Int,
    @SerialName("temp_f") val tempInFahrenheit: Double,
    @SerialName("uv") val ultraviolet: Int,
    @SerialName("vis_km") val visKm: Double,
    @SerialName("vis_miles") val visMiles: Int,
    @SerialName("wind_degree") val windDegrees: Int,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("wind_kph") val windSpeedInKmh: Int,
    @SerialName("wind_mph") val windSpeedInMh: Double
)