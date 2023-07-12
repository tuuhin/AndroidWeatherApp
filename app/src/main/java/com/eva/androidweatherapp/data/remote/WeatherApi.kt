package com.eva.androidweatherapp.data.remote

import com.eva.androidweatherapp.BuildConfig
import com.eva.androidweatherapp.data.remote.dto.results.SearchResultsDto
import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.data.remote.dto.results.WeatherForecastDto
import com.eva.androidweatherapp.utils.BooleanResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("current.json")
    suspend fun getCurrentData(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String
    ): WeatherCurrentDataDto

    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String,
        @Query("days") days: Int,
        @Query("dt") hourCount: Int,
        @Query("hour") currentHour: Int? = null,
        @Query("alerts") alert: BooleanResponse,
        @Query("aqi") quality: BooleanResponse,
    ): WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String,
    ): List<SearchResultsDto>

    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1/"
        private val mediaType = "application/json".toMediaType()

        fun createApiInstance(client: OkHttpClient): WeatherApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(Json.asConverterFactory(mediaType))
                .build()
                .create(WeatherApi::class.java)

    }
}