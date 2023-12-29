package com.eva.androidweatherapp.data.remote

import com.eva.androidweatherapp.BuildConfig
import com.eva.androidweatherapp.data.remote.dto.results.SearchResultsDto
import com.eva.androidweatherapp.data.remote.dto.results.WeatherCurrentDataDto
import com.eva.androidweatherapp.data.remote.dto.results.WeatherForecastDto
import com.eva.androidweatherapp.data.remote.interceptor.AuthInterceptor
import com.eva.androidweatherapp.utils.BooleanResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.Duration

interface WeatherApi {
    @GET("current.json")
    suspend fun getCurrentData(
        @Query("q") query: String
    ): WeatherCurrentDataDto

    @GET("forecast.json")
    suspend fun getWeatherForecast(
        @Query("q") query: String,
        @Query("days") days: Int,
        @Query("dt") hourCount: Int,
        @Query("hour") currentHour: Int? = null,
        @Query("alerts") alert: BooleanResponse = BooleanResponse.FALSE,
        @Query("aqi") quality: BooleanResponse = BooleanResponse.FALSE,
    ): WeatherForecastDto

    @GET("search.json")
    suspend fun searchCity(
        @Query("q") query: String,
    ): List<SearchResultsDto>

    companion object {
        private val mediaType = "application/json".toMediaType()

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(Duration.ofMinutes(1))
            .addInterceptor(AuthInterceptor)
            .retryOnConnectionFailure(true)
            .readTimeout(Duration.ofMinutes(2))
            .build()

        fun createApiInstance(): WeatherApi = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(mediaType))
            .build()
            .create(WeatherApi::class.java)

    }
}