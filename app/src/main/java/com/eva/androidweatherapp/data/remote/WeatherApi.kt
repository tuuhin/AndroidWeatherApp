package com.eva.androidweatherapp.data.remote

import com.eva.androidweatherapp.BuildConfig
import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.data.remote.dto.results.WeatherForecastDto
import com.eva.androidweatherapp.utils.BooleanResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/current.json")
    suspend fun getCurrentData(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String
    ): WeatherCurrentDataDto

    @GET("/forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("days") days: Int,
        @Query("dt") hourCount: Int,
        @Query("hour") currentHour: Int?,
        @Query("alerts") alert: BooleanResponse,
        @Query("aqi") quality: BooleanResponse = BooleanResponse.TRUE
    ): WeatherForecastDto

    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1"
    }
}