package com.eva.androidweatherapp.data.mappers

import androidx.annotation.DrawableRes
import com.eva.androidweatherapp.data.local.SavedWeatherEntity
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.utils.toDateTimeFormat

@get:DrawableRes
val SavedWeatherModel.image: Int
    get() = code.toDrawableRes()

fun SavedWeatherEntity.toModel(): SavedWeatherModel {

    val feelsLikeFahrenheit = (9 / 5 * feelsLikeInCelsius) + 32
    val precipitationMM = 25.4f * precipitationInch
    val tempInFahrenheit = (9 / 5 * tempInCelsius) + 32
    val windSpeedInMh = 0.27f * windSpeedInKmh

    return SavedWeatherModel(
        id = id ?: -1,
        condition = condition,
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
        code = code,
        lastUpdate = lastUpdated?.toDateTimeFormat()
    )
}

fun SavedWeatherModel.toEntity(entityId: Int? = null): SavedWeatherEntity = SavedWeatherEntity(
    id = id ?: entityId,
    condition = condition,
    code = code,
    feelsLikeInCelsius = feelsLikeInCelsius,
    humidity = humidity,
    precipitationInch = precipitationInch,
    pressureMilliBar = pressureMilliBar,
    tempInCelsius = tempInCelsius,
    windSpeedInKmh = windSpeedInKmh,
    country = country,
    name = name,
    region = region,
    lastUpdated = lastUpdate?.toDateTimeFormat(),
)