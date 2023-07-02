package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForeCastHourDto(
    @SerialName("air_quality") val airQuality: AirQualityDto? = null,
    @SerialName("chance_of_rain") val precipitationPercentage: Float,
    @SerialName("chance_of_snow") val snowFallPercentage: Float,
    @SerialName("cloud") val cloudCover: Float,
    @SerialName("condition") val condition: WeatherConditionDto,
    @SerialName("dewpoint_c") val dewPointInCelsius: Float,
    @SerialName("dewpoint_f") val dewPointInFahrenheit: Float,
    @SerialName("feelslike_c") val feelsLikeInCelsius: Float,
    @SerialName("feelslike_f") val feelsLikeInFahrenheit: Float,
    @SerialName("gust_kph") val gustSpeedInKmpH: Float,
    @SerialName("gust_mph") val gustSpeedInMh: Float,
    @SerialName("heatindex_c") val heatIndexInCelsius: Float,
    @SerialName("heatindex_f") val heatIndexInFahrenheit: Float,
    @SerialName("humidity") val humidity: Float,
    @SerialName("is_day") val isDay: Float,
    @SerialName("precip_in") val precipitationInInch: Float,
    @SerialName("precip_mm") val precipitationInMm: Float,
    @SerialName("pressure_in") val pressureInInch: Float,
    @SerialName("pressure_mb") val pressureInMBar: Float,
    @SerialName("temp_c") val temperatureInCelsius: Float,
    @SerialName("temp_f") val temperatureInFahrenheit: Float,
    @SerialName("time") val time: String,
    @SerialName("time_epoch") val epoch: Float,
    @SerialName("uv") val ultralight: Float,
    @SerialName("vis_km") val visKm: Float,
    @SerialName("vis_miles") val visMiles: Float,
    @SerialName("will_it_rain") val chanceOfRainRegular: Float,
    @SerialName("will_it_snow") val chanceOfSnowRegular: Float,
    @SerialName("wind_degree") val windDegree: Float,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("wind_kph") val windSpeedInKph: Float,
    @SerialName("wind_mph") val windSpeedInMpH: Float,
    @SerialName("windchill_c") val windchillInCelsius: Float,
    @SerialName("windchill_f") val windchillInFahrenheit: Float
)