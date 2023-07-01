package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForeCastHourDto(
    @SerialName("air_quality") val airQuality: AirQualityDto,
    @SerialName("chance_of_rain") val precipitationPercentage: Int,
    @SerialName("chance_of_snow") val snowFallPercentage: Int,
    @SerialName("cloud") val cloudCover: Int,
    @SerialName("condition") val condition: WeatherConditionDto,
    @SerialName("dewpoint_c") val dewPointInCelsius: Double,
    @SerialName("dewpoint_f") val dewPointInFahrenheit: Double,
    @SerialName("feelslike_c") val feelsLikeInCelsius: Double,
    @SerialName("feelslike_f") val feelsLikeInFahrenheit: Double,
    @SerialName("gust_kph") val gustSpeedInKmpH: Double,
    @SerialName("gust_mph") val gustSpeedInMh: Double,
    @SerialName("heatindex_c") val heatIndexInCelsius: Double,
    @SerialName("heatindex_f") val heatIndexInFahrenheit: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("is_day") val isDay: Int,
    @SerialName("precip_in") val precipitationInInch: Double,
    @SerialName("precip_mm") val precipitationInMm: Double,
    @SerialName("pressure_in") val pressureInInch: Double,
    @SerialName("pressure_mb") val pressureInMBar: Int,
    @SerialName("temp_c") val temperatureInCelsius: Double,
    @SerialName("temp_f") val temperatureInFahrenheit: Double,
    @SerialName("time") val time: String,
    @SerialName("time_epoch") val epoch: Int,
    @SerialName("uv") val ultralight: Int,
    @SerialName("vis_km") val visKm: Int,
    @SerialName("vis_miles") val visMiles: Int,
    @SerialName("will_it_rain") val chanceOfRainRegular: Int,
    @SerialName("will_it_snow") val chanceOfSnowRegular: Int,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("wind_kph") val windSpeedInKph: Double,
    @SerialName("wind_mph") val windSpeedInMpH: Double,
    @SerialName("windchill_c") val windchillInCelsius: Double,
    @SerialName("windchill_f") val windchillInFahrenheit: Double
)