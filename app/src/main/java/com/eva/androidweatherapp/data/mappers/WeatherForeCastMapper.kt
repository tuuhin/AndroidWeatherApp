package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.results.WeatherForecastDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherForecastDto.toModel(): WeatherForeCastModel = WeatherForeCastModel(
    current = CurrentWeatherModel(
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
    ),
    forecast = forecast.forecast.map { info ->
        WeatherDayForecastModel(
            date = LocalDate.parse(
                info.date,
                DateTimeFormatter.ofPattern("yyyy-MM-dd")
            ),
            sunrise = info.astronomical.sunrise,
            sunset = info.astronomical.sunset,
            moonRise = info.astronomical.moonRise,
            moonSet = info.astronomical.moonSet,
            hourCycle = info.hour.map { hourly ->
                WeatherHourModel(
                    date = LocalDateTime.parse(
                        hourly.time,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                    ),
                    code = hourly.condition.weatherCode,
                    image = hourly.condition.weatherCode.toDrawableRes(),
                    tempC = hourly.temperatureInCelsius,
                    tempF = hourly.temperatureInFahrenheit,
                    windSpeedKmpH = hourly.windSpeedInKph,
                    windSpeedMH = hourly.windSpeedInMpH
                )
            },

            quality = info.day.quality?.epaIndex?.let { AirQualityOption.fromNumber(it) },
            avgHumidity = info.day.avgHumidity,
            avgTempInCelsius = info.day.avgTempInCelsius,
            avgTempInFahrenheit = info.day.avgTempInFahrenheit,
            code = info.day.condition.weatherCode,
            image = info.day.condition.weatherCode.toDrawableRes(),
            weather = info.day.condition.text,
            maxTempInCelsius = info.day.maxTempInCelsius,
            minTempInCelsius = info.day.minTempInCelsius,
            maxTempInFahrenheit = info.day.maxTempInFahrenheit,
            minTempInFahrenheit = info.day.minTempInFahrenheit,
            ultralight = info.day.ultralight,
            maxWindSpeedInKmpH = info.day.maxWindSpeedInKmpH,
            maxWindSpeedInMph = info.day.maxWindSpeedInMph,
            rainPercentage = info.day.rainPercentage,
            snowPercentage = info.day.snowPercentage,
            totalPrecipitationInInch = info.day.totalPrecipitationInInch,
            totalSnowInCm = info.day.totalSnowInCm,
            totalPrecipitationInMm = info.day.totalPrecipitationInMm
        )
    }
)