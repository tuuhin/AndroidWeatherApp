package com.eva.androidweatherapp.data.mappers

import androidx.annotation.DrawableRes
import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.utils.toDateTimeFormat

@get:DrawableRes
val CurrentWeatherModel.image: Int
    get() = code.toDrawableRes()

fun WeatherCurrentDataDto.toModel(): CurrentWeatherModel = CurrentWeatherModel(
    cloudCover = weather.cloudCover,
    code = weather.condition.weatherCode,
    condition = weather.condition.text,
    feelsLikeInCelsius = weather.feelsLikeInCelsius,
    feelsLikeFahrenheit = weather.feelsLikeFahrenheit,
    humidity = weather.humidity,
    precipitationInch = weather.precipitationInch,
    precipitationMM = weather.precipitationMM,
    poundPerSquareInch = weather.pressureInch,
    pressureMilliBar = weather.pressureMilliBar,
    lastUpdated = weather.lastUpdated.toDateTimeFormat(),
    tempInCelsius = weather.tempInCelsius,
    tempInFahrenheit = weather.tempInFahrenheit,
    ultraviolet = weather.ultraviolet,
    windSpeedInKmh = weather.windSpeedInKmh,
    windSpeedInMh = weather.windSpeedInMh,
    name = location.name, region = location.region,
    country = location.country
)

fun WeatherCurrentDataDto.toDbModel(entityId: Int? = null): SavedWeatherModel = SavedWeatherModel(
    id = entityId ?: -1,
    code = weather.condition.weatherCode,
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
    lastUpdate = weather.lastUpdated.toDateTimeFormat()
)