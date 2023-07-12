package com.eva.androidweatherapp.presentation.feature_daily

import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel

enum class WeatherGraphType(val type: String) {
    AVG_TEMPERATURE("Avg. Temp"),
    MAX_TEMPERATURE("Max. Temp"),
    MIN_TEMPERATURE("Min Temp"),
    PRECIPITATION("Precipitation"),
    WIND_SPEED("Wind Speed"),
    HUMIDITY("Humidity");

    fun typeWithUnit(localeAmerican: Boolean): String {
        return when (this) {
            AVG_TEMPERATURE,MAX_TEMPERATURE,MIN_TEMPERATURE -> if (localeAmerican)
                "$type (F)"
            else "$type (C)"

            PRECIPITATION -> if (localeAmerican)
                "$type (Inch)"
            else "$type (Mm)"

            WIND_SPEED -> if (localeAmerican)
                "$type (mph)"
            else "$type (kmph)"

            HUMIDITY -> "$type (%)"
        }
    }

    fun valueFromForeCast(model: WeatherDayForecastModel, localeAmerican: Boolean): Float {
        return when (this) {
            AVG_TEMPERATURE -> if (localeAmerican)
                model.avgTempInFahrenheit
            else model.avgTempInCelsius

            MAX_TEMPERATURE -> if (localeAmerican)
                model.maxTempInFahrenheit else model.maxTempInCelsius

            MIN_TEMPERATURE -> if (localeAmerican)
                model.minTempInFahrenheit
            else model.minTempInCelsius

            PRECIPITATION -> if (localeAmerican)
                model.totalPrecipitationInInch
            else model.totalPrecipitationInMm

            WIND_SPEED -> if (localeAmerican)
                model.maxWindSpeedInMph
            else model.maxWindSpeedInKmpH

            HUMIDITY -> model.avgHumidity
        }
    }

    fun maxOfForecast(forecast: WeatherForeCastModel, localeAmerican: Boolean): Float {
        return when (this) {
            AVG_TEMPERATURE -> {
                if (localeAmerican)
                    forecast.forecast.maxOf { it.avgTempInFahrenheit }
                else forecast.forecast.maxOf { it.avgTempInCelsius }
            }

            MAX_TEMPERATURE -> {
                if (localeAmerican) forecast.forecast.maxOf { it.maxTempInFahrenheit }
                else forecast.forecast.maxOf { it.maxTempInCelsius }
            }

            MIN_TEMPERATURE -> {
                if (localeAmerican) forecast.forecast.minOf { it.minTempInFahrenheit }
                else forecast.forecast.maxOf { it.minTempInCelsius }
            }

            PRECIPITATION -> forecast.forecast.maxOf { it.totalPrecipitationInMm }

            WIND_SPEED -> {
                if (localeAmerican)
                    forecast.forecast.maxOf { it.maxWindSpeedInMph }
                else forecast.forecast.maxOf { it.maxWindSpeedInKmpH }
            }

            HUMIDITY -> forecast.forecast.maxOf { it.avgHumidity }
        }
    }

    fun minOfForecast(forecast: WeatherForeCastModel, localeAmerican: Boolean): Float {
        return when (this) {
            AVG_TEMPERATURE -> {
                if (localeAmerican)
                    forecast.forecast.minOf { it.avgTempInFahrenheit }
                else forecast.forecast.minOf { it.avgTempInCelsius }
            }

            MAX_TEMPERATURE -> {
                if (localeAmerican) forecast.forecast.minOf { it.maxTempInFahrenheit }
                else forecast.forecast.minOf { it.maxTempInCelsius }
            }

            MIN_TEMPERATURE -> {
                if (localeAmerican) forecast.forecast.minOf { it.minTempInFahrenheit }
                else forecast.forecast.minOf { it.minTempInCelsius }
            }

            PRECIPITATION -> forecast.forecast.minOf { it.totalPrecipitationInMm }

            WIND_SPEED -> {
                if (localeAmerican)
                    forecast.forecast.minOf { it.maxWindSpeedInMph }
                else forecast.forecast.minOf { it.maxWindSpeedInKmpH }
            }

            HUMIDITY -> forecast.forecast.minOf { it.avgHumidity }
        }
    }
}

