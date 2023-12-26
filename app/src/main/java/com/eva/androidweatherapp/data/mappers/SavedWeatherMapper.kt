package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.local.SavedWeatherEntity
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.utils.toDateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun SavedWeatherEntity.toModel(): SavedWeatherModel {

    val feelsLikeFahrenheit = (9 / 5 * feelsLikeInCelsius) + 32
    val precipitationMM = 25.4f * precipitationInch
    val tempInFahrenheit = (9 / 5 * tempInCelsius) + 32
    val windSpeedInMh = 0.27f * windSpeedInKmh

    return SavedWeatherModel(
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
        code = code,
        lastUpdate = lastUpdated?.let { str ->
            LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        }
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