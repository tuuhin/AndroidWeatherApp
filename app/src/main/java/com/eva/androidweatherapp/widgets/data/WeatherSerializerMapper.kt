package com.eva.androidweatherapp.widgets.data

import com.eva.androidweatherapp.data.mappers.toDrawableRes
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.widgets.model.WidgetWeatherModel

fun CurrentWeatherModel.toSerializer(): WeatherModelSerializer = WeatherModelSerializer(
    condition = condition,
    code = code,
    feelsLikeInCelsius = feelsLikeInCelsius,
    feelsLikeFahrenheit = feelsLikeFahrenheit,
    precipitationInch = precipitationInch,
    precipitationMM = precipitationMM,
    poundPerSquareInch = poundPerSquareInch,
    pressureMilliBar = pressureMilliBar,
    tempInCelsius = tempInCelsius,
    tempInFahrenheit = tempInFahrenheit,
    windSpeedInKmh = windSpeedInKmh,
    windSpeedInMh = windSpeedInMh,
    name = name,
    region = region,
    humidity = humidity
)

fun WeatherModelSerializer.toModel(): WidgetWeatherModel = WidgetWeatherModel(
    condition = condition,
    image = code.toDrawableRes(),
    feelsLikeInCelsius = feelsLikeInCelsius,
    feelsLikeFahrenheit = feelsLikeFahrenheit,
    precipitationInch = precipitationInch,
    precipitationMM = precipitationMM,
    poundPerSquareInch = poundPerSquareInch,
    pressureMilliBar = pressureMilliBar,
    tempInCelsius = tempInCelsius,
    tempInFahrenheit = tempInFahrenheit,
    windSpeedInKmh = windSpeedInKmh,
    windSpeedInMh = windSpeedInMh,
    name = name,
    region = region,
    humidity = humidity
)
