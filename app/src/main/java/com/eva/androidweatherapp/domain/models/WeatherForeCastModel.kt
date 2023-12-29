package com.eva.androidweatherapp.domain.models

data class WeatherForeCastModel(
    val current: CurrentWeatherModel,
    val forecast: List<WeatherDayForecastModel>
) {
    val firstForecast: WeatherDayForecastModel
        get() = forecast.first()

    val firstForecastHourCycles: List<WeatherHourModel>
        get() = forecast.first().hourCycle
}
