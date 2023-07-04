package com.eva.androidweatherapp.presentation.util

import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.SearchedLocationModel
import com.eva.androidweatherapp.domain.models.WeatherAlertModel
import com.eva.androidweatherapp.domain.utils.AirQualityOption

object PreviewFakeData {

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
        lastUpdated = "2022-07-22 16:45",
        pressureMilliBar = 1011f,
        poundPerSquareInch = 29.85f, precipitationInch = 0f,
        precipitationMM = 0f,
        tempInFahrenheit = 93.9f,
        tempInCelsius = 34.4f,
        windDirection = "S"
    )

    val fakeSearchedLocationModel = SearchedLocationModel(
        country = "United States of America",
        name = "New York", region = "New York",
        time = "2022-07-22 16:49"
    )

    val fakeAlertModel = WeatherAlertModel(
        headline = "NWS New York City - Upton (Long Island and New York City)",
        instruction = "",
        areas = "",
        note = null,
        desc = "...HEAT ADVISORY REMAINS IN EFFECT UNTIL 8 PM EDT SUNDAY... * WHAT...Heat index values up to 105. * WHERE...Eastern Passaic Hudson Western Bergen Western Essex Eastern Bergen and Eastern Essex Counties. * WHEN...Until 8 PM EDT Sunday. * IMPACTS...High temperatures and high humidity may cause heat illnesses to occur",
        category = "Extreme temperature value"

    )


}