package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.data.remote.dto.common.CurrentWeatherConditionDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Change the Air quality index parameter
fun CurrentWeatherConditionDto.toModel(): CurrentWeatherModel = CurrentWeatherModel(
    airQuality = airQuality?.epaIndex?.let { AirQualityOption.airQualityFromNumber(it) },
    airQualityIndex = 0f,
    cloudCover = cloudCover,
    code = condition.weatherCode,
    image = when (condition.weatherCode) {
        1000 -> R.drawable.ic_weather_clear
        1003 -> R.drawable.ic_few_clouds
        1006 -> R.drawable.ic_clouds
        1009 -> R.drawable.ic_scatterd_clouds
        1030 -> R.drawable.ic_mist
        1063, 1180, 1183, 1240 -> R.drawable.ic_scattered_rain
        1066 -> R.drawable.ic_snowy
        1069, 1204, 1207, 1210, 1213, 1249, 1252, 1255 -> R.drawable.ic_sleet
        1072, 1150, 1153, 1171 -> R.drawable.ic_drizzle
        1087, 1273, 1276, 1279, 1282 -> R.drawable.ic_thunderstorm
        1114, 1216, 1219, 1222, 1225, 1237, 1258, 1261, 1264 -> R.drawable.ic_snowfall
        1117 -> R.drawable.ic_blizzard
        1135 -> R.drawable.ic_fog
        1147 -> R.drawable.ic_freezing_fog
        1189, 1192, 1195, 1243, 1246 -> R.drawable.ic_rain
        1198, 1201 -> R.drawable.ic_freezing_rain
        else -> R.drawable.ic_unknown_weather
    },
    condition = condition.text,
    feelsLikeInCelsius = feelsLikeInCelsius,
    feelsLikeFahrenheit = feelsLikeFahrenheit,
    humidity = humidity,
    precipitationInch = precipitationInch,
    precipitationMM = precipitationMM,
    poundPerSquareInch = pressureInch,
    pressureMilliBar = pressureMilliBar,
    lastUpdated = LocalDateTime.parse(lastUpdated, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
    tempInCelsius = tempInCelsius,
    tempInFahrenheit = tempInFahrenheit, ultraviolet = ultraviolet,
    windDirection = windDirection,
    windSpeedInKmh = windSpeedInKmh,
    windSpeedInMh = windSpeedInMh,
)