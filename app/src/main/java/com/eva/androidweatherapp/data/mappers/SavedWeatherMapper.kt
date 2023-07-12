package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.local.SavedWeatherEntity
import com.eva.androidweatherapp.domain.models.SavedWeatherModel

fun SavedWeatherEntity.toModel(): SavedWeatherModel = SavedWeatherModel(
    id = id,
    condition = condition,
    image = code.toDrawableRes(),
    feelsLikeInCelsius = feelsLikeInCelsius,
    feelsLikeFahrenheit = feelsLikeFahrenheit,
    humidity = humidity,
    precipitationInch = precipitationInch,
    precipitationMM = precipitationMM,
    pressureMilliBar = pressureMilliBar,
    tempInCelsius = tempInCelsius,
    tempInFahrenheit = tempInFahrenheit,
    windSpeedInKmh = windSpeedInKmh,
    windSpeedInMh = windSpeedInMh,
    country = country,
    name = name,
    region = region,
    code = code
)

fun SavedWeatherModel.toEntity(entityId: Int? = null): SavedWeatherEntity = SavedWeatherEntity(
    id = id ?: entityId,
    condition = condition,
    image = image,
    code = code,
    feelsLikeInCelsius = feelsLikeInCelsius,
    feelsLikeFahrenheit = feelsLikeFahrenheit,
    humidity = humidity,
    precipitationInch = precipitationInch,
    precipitationMM = precipitationMM,
    pressureMilliBar = pressureMilliBar,
    tempInCelsius = tempInCelsius,
    tempInFahrenheit = tempInFahrenheit,
    windSpeedInKmh = windSpeedInKmh,
    windSpeedInMh = windSpeedInMh,
    country = country,
    name = name,
    region = region
)