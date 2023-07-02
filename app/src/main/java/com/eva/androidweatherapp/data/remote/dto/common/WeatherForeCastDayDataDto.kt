package com.eva.androidweatherapp.data.remote.dto.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForeCastDayDataDto(
    @SerialName("air_quality") val quality: AirQualityDto? = null,
    @SerialName("avghumidity") val avgHumidity: Float,
    @SerialName("avgtemp_c") val avgTempInCelsius: Float,
    @SerialName("avgtemp_f") val avgTempInFahrenheit: Float,
    @SerialName("avgvis_km") val avgVisKm: Float,
    @SerialName("avgvis_miles") val avgVisM: Float,
    @SerialName("condition") val condition: WeatherConditionDto,
    @SerialName("daily_chance_of_rain") val rainPercentage: Float,
    @SerialName("daily_chance_of_snow") val snowPercentage: Float,
    @SerialName("daily_will_it_rain") val chanceOfRainRegular: Float,
    @SerialName("daily_will_it_snow") val chanceOfSnowRegular: Float,
    @SerialName("maxtemp_c") val maxTempInCelsius: Float,
    @SerialName("maxtemp_f") val maxTempInFahrenheit: Float,
    @SerialName("maxwind_kph") val maxWindSpeedInKmpH: Float,
    @SerialName("maxwind_mph") val maxWindSpeedInMph: Float,
    @SerialName("mintemp_c") val minTempInCelsius: Float,
    @SerialName("mintemp_f") val minTempInFahrenheit: Float,
    @SerialName("totalprecip_in") val totalPrecipitationInInch: Float,
    @SerialName("totalprecip_mm") val totalPrecipitationInMm: Float,
    @SerialName("totalsnow_cm") val totalSnowInCm: Float,
    @SerialName("uv") val ultralight: Float
)