package com.eva.androidweatherapp.data.mappers

import androidx.annotation.DrawableRes
import com.eva.androidweatherapp.data.remote.dto.results.WeatherForecastDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import com.eva.androidweatherapp.utils.toDateTimeFormat
import com.eva.androidweatherapp.utils.toIsoDateFormat

@get:DrawableRes
val WeatherDayForecastModel.image: Int
    get() = code.toDrawableRes()


@get:DrawableRes
val WeatherHourModel.image: Int
    get() = code.toDrawableRes()

fun WeatherForecastDto.toModel(): WeatherForeCastModel = WeatherForeCastModel(
    current = CurrentWeatherModel(
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
    ),
    forecast = forecast.forecast.map { info ->
        WeatherDayForecastModel(
            date = info.date.toIsoDateFormat(),
            sunrise = info.astronomical.sunrise,
            sunset = info.astronomical.sunset,
            moonRise = info.astronomical.moonRise,
            moonSet = info.astronomical.moonSet,
            hourCycle = info.hour.map { hourly ->
                WeatherHourModel(
                    date = hourly.time.toDateTimeFormat(),
                    code = hourly.condition.weatherCode,
                    tempC = hourly.temperatureInCelsius,
                    tempF = hourly.temperatureInFahrenheit,
                    windSpeedKmpH = hourly.windSpeedInKph,
                    windSpeedMH = hourly.windSpeedInMpH
                )
            },

            quality = info.day.quality?.epaIndex?.let(AirQualityOption::fromNumber),
            avgHumidity = info.day.avgHumidity,
            avgTempInCelsius = info.day.avgTempInCelsius,
            avgTempInFahrenheit = info.day.avgTempInFahrenheit,
            code = info.day.condition.weatherCode,
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
            totalPrecipitationInMm = info.day.totalPrecipitationInMm
        )
    }
)