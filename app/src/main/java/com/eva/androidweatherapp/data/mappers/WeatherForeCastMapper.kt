package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.data.remote.dto.results.WeatherForecastDto
import com.eva.androidweatherapp.domain.models.WeatherDayDataModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.models.WeatherForecastDayModel
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherForecastDto.toModel(): WeatherForeCastModel = WeatherForeCastModel(
    currentWeatherModel = current.toModel(),
    searchedLocationModel = location.toModel(),
    alerts = alerts?.map { it.toModel() },
    forecast = forecast.forecast.map { info ->
        WeatherDayDataModel(
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
                    image = when (hourly.condition.weatherCode) {
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
                    tempC = hourly.temperatureInCelsius,
                    tempF = hourly.temperatureInFahrenheit,
                    windSpeedKmpH = hourly.windSpeedInKph,
                    windSpeedMH = hourly.windSpeedInMpH
                )
            },
            dayCycle = WeatherForecastDayModel(
                quality = info.day.quality?.epaIndex?.let { AirQualityOption.airQualityFromNumber(it) },
                avgHumidity = info.day.avgHumidity,
                avgTempInCelsius = info.day.avgTempInCelsius,
                avgTempInFahrenheit = info.day.avgTempInFahrenheit,
                code = info.day.condition.weatherCode,
                image = when (info.day.condition.weatherCode) {
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

        )
    }
)