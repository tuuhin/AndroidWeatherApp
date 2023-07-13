package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherCurrentDataDto.toModel(): CurrentWeatherModel = CurrentWeatherModel(
    airQuality = weather.airQuality?.epaIndex?.let { AirQualityOption.fromNumber(it) },
    cloudCover = weather.cloudCover,
    code = weather.condition.weatherCode,
    image = weather.condition.weatherCode.toDrawableRes(),
    condition = weather.condition.text,
    feelsLikeInCelsius = weather.feelsLikeInCelsius,
    feelsLikeFahrenheit = weather.feelsLikeFahrenheit,
    humidity = weather.humidity,
    precipitationInch = weather.precipitationInch,
    precipitationMM = weather.precipitationMM,
    poundPerSquareInch = weather.pressureInch,
    pressureMilliBar = weather.pressureMilliBar,
    lastUpdated = LocalDateTime.parse(
        weather.lastUpdated,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    ),
    tempInCelsius = weather.tempInCelsius,
    tempInFahrenheit = weather.tempInFahrenheit,
    ultraviolet = weather.ultraviolet,
    windDirection = weather.windDirection,
    windSpeedInKmh = weather.windSpeedInKmh,
    windSpeedInMh = weather.windSpeedInMh,
    name = location.name, region = location.region,
    country = location.country
)

fun WeatherCurrentDataDto.toDbModel(entityId: Int? = null): SavedWeatherModel = SavedWeatherModel(
    id = entityId,
    code = weather.condition.weatherCode,
    image = weather.condition.weatherCode.toDrawableRes(),
    condition = weather.condition.text,
    feelsLikeInCelsius = weather.feelsLikeInCelsius,
    feelsLikeFahrenheit = weather.feelsLikeFahrenheit,
    humidity = weather.humidity,
    precipitationInch = weather.precipitationInch,
    precipitationMM = weather.precipitationMM,
    pressureMilliBar = weather.pressureMilliBar,
    tempInCelsius = weather.tempInCelsius,
    tempInFahrenheit = weather.tempInFahrenheit,
    windSpeedInKmh = weather.windSpeedInKmh,
    windSpeedInMh = weather.windSpeedInMh,
    name = location.name,
    region = location.region,
    country = location.country,
    lastUpdate = LocalDateTime.parse(
        weather.lastUpdated,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    )

)