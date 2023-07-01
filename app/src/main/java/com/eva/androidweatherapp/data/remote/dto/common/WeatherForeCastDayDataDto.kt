package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForeCastDayDataDto(
    @SerialName("air_quality") val quality: AirQualityDto,
    @SerialName("avghumidity") val avgHumidity: Int,
    @SerialName("avgtemp_c") val avgTempInCelsius: Double,
    @SerialName("avgtemp_f") val avgTempInFahrenheit: Double,
    @SerialName("avgvis_km") val avgVisKm: Double,
    @SerialName("avgvis_miles") val avgVisM: Int,
    @SerialName("condition") val condition: WeatherConditionDto,
    @SerialName("daily_chance_of_rain") val rainPercentage: Int,
    @SerialName("daily_chance_of_snow") val snowPercentage: Int,
    @SerialName("daily_will_it_rain") val chanceOfRainRegular: Int,
    @SerialName("daily_will_it_snow") val chanceOfSnowRegular: Int,
    @SerialName("maxtemp_c") val maxTempInCelsius: Double,
    @SerialName("maxtemp_f") val maxTempInFahrenheit: Int,
    @SerialName("maxwind_kph") val maxWindSpeedInKmpH: Double,
    @SerialName("maxwind_mph") val maxWindSpeedInMph: Double,
    @SerialName("mintemp_c") val minTempInCelsius: Double,
    @SerialName("mintemp_f") val minTempInFahrenheit: Int,
    @SerialName("toalprecip_in") val totalPrecipitationInInch: Double,
    @SerialName("totalprecip_mm") val totalPrecipitationInMm: Int,
    @SerialName("totalsnow_cm") val totalSnowInCm: Int,
    @SerialName("uv") val ultralight: Int
)