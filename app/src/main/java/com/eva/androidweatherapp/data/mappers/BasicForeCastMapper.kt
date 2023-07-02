package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherForecastBasicModel

fun WeatherCurrentDataDto.toModel(): CurrentWeatherForecastBasicModel =
    CurrentWeatherForecastBasicModel(
        currentWeatherModel = currentWeatherCondition.toModel(),
        searchedLocationModel = searchedLocation.toModel()
    )