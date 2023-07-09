package com.eva.androidweatherapp.domain.repository

import com.eva.androidweatherapp.domain.models.BaseLocationModel
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun getWeatherForecastOneDayFromLatAndLong(location: BaseLocationModel):
            Flow<Resource<WeatherForeCastModel>>

    suspend fun getWeatherForecastOneDayFromName(name: String):
            Flow<Resource<WeatherForeCastModel>>

    suspend fun getBasicWeatherInfoFromLatAndLong(location: BaseLocationModel)
            : Flow<Resource<CurrentWeatherModel>>

    suspend fun getBasicWeatherInfoFromName(name: String)
            : Flow<Resource<CurrentWeatherModel>>

    suspend fun getCurrentLocationWeather(): Flow<Resource<CurrentWeatherModel>>
}