package com.eva.androidweatherapp.di

import com.eva.androidweatherapp.data.location.AndroidLocationTracker
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.data.repository.WeatherRepositoryImpl
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.presentation.WeatherForecastViewModel
import com.google.android.gms.location.LocationServices
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()
    }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    factory<LocationTracker> { AndroidLocationTracker(get(), get()) }

    single<WeatherApi> {
        val mediaType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
          //  .client(get())
            .addConverterFactory(Json.asConverterFactory(mediaType))
            .build()
            .create(WeatherApi::class.java)
    }

    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }

    viewModel { WeatherForecastViewModel(get(), get()) }

}