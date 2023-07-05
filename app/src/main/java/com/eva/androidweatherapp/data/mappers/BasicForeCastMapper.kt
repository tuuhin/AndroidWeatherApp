package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel

fun WeatherCurrentDataDto.toModel(): CurrentWeatherBasicModel =
    CurrentWeatherBasicModel(
        currentWeather = currentWeatherCondition.toModel(),
        location = searchedLocation.toModel()
    )