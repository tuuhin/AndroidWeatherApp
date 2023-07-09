package com.eva.androidweatherapp.presentation.util

import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.domain.models.WeatherAlertModel
import com.eva.androidweatherapp.domain.models.WeatherDayForecastModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.models.WeatherHourModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object PreviewFakeData {

    val locationResult = SearchLocationResult(
        id = 1000,
        name = "New York",
        region = "New York",
        country = "United States of America"
    )

    val fakeCurrentWeatherModel = CurrentWeatherModel(
        airQuality = AirQualityOption.GOOD,
        airQualityIndex = 10f,
        cloudCover = 75f,
        condition = "Partly Cloudy",
        code = 1003,
        image = R.drawable.ic_few_clouds,
        windSpeedInMh = 16.3f,
        windSpeedInKmh = 25.9f,
        feelsLikeFahrenheit = 98.6f,
        feelsLikeInCelsius = 37f,
        humidity = 31f, ultraviolet = 8f,
        lastUpdated = LocalDateTime.now(),
        pressureMilliBar = 1011f,
        poundPerSquareInch = 29.85f, precipitationInch = 0f,
        precipitationMM = 0f,
        tempInFahrenheit = 93.9f,
        tempInCelsius = 34.4f,
        windDirection = "S", country = "United States of America",
        name = "New York", region = "New York",
    )

    val fakeAlertModel = WeatherAlertModel(
        headline = "NWS New York City - Upton (Long Island and New York City)",
        instruction = "",
        areas = "",
        note = null,
        desc = "...HEAT ADVISORY REMAINS IN EFFECT UNTIL 8 PM EDT SUNDAY... * WHAT...Heat index values up to 105. * WHERE...Eastern Passaic Hudson Western Bergen Western Essex Eastern Bergen and Eastern Essex Counties. * WHEN...Until 8 PM EDT Sunday. * IMPACTS...High temperatures and high humidity may cause heat illnesses to occur",
        category = "Extreme temperature value"

    )

    val fakeWeatherHourModel = WeatherHourModel(
        code = 1000,
        image = R.drawable.ic_weather_clear,
        date = LocalDateTime.parse(
            "2022-07-22 00:00",
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        ),
        tempF = 83.7f,
        tempC = 28.7f,
        windSpeedMH = 9.4f,
        windSpeedKmpH = 15.1f
    )

    val fakeWeatherDayDataModel = WeatherDayForecastModel(
        date = LocalDate.parse(
            "2022-07-22T00:00:00.000Z",
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        ),
        hourCycle = List(10) { fakeWeatherHourModel },
        moonSet = "03:35 PM",
        moonRise = "12:58 AM",
        sunset = "08:20 PM",
        sunrise = "05:44 AM",
        avgHumidity = 53f,
        code = 1000,
        image = R.drawable.ic_weather_clear,
        avgTempInCelsius = 30.7f,
        avgTempInFahrenheit = 87.3f,
        quality = AirQualityOption.MODERATE,
        maxTempInCelsius = 35.9f,
        maxTempInFahrenheit = 96.3f,
        maxWindSpeedInMph = 12.8f,
        maxWindSpeedInKmpH = 20.5f,
        minTempInCelsius = 26.3f,
        minTempInFahrenheit = 79.3f,
        rainPercentage = 0f,
        snowPercentage = 0f,
        weather = "Partly Cloudy",
        totalPrecipitationInMm = 0f,
        totalPrecipitationInInch = 0f,
        ultralight = 8f,
        totalSnowInCm = 0f,
    )

    val fakeForeCastModel = WeatherForeCastModel(
        current = fakeCurrentWeatherModel,
        forecast = List(5) { fakeWeatherDayDataModel },
        alerts = listOf(fakeAlertModel)
    )

}