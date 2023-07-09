package com.eva.androidweatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.eva.androidweatherapp.data.location.AndroidLocationTracker
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.data.repository.PreferenceStoreImpl
import com.eva.androidweatherapp.data.repository.SearchLocationRepoImpl
import com.eva.androidweatherapp.data.repository.WeatherRepositoryImpl
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.repository.PreferenceStoreFacade
import com.eva.androidweatherapp.domain.repository.SearchLocationRepository
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.presentation.WeatherForecastViewModel
import com.eva.androidweatherapp.presentation.feature_search.SearchLocationViewModel
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

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "APP_CONFIGURATION")

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .build()
    }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    factory<LocationTracker> { AndroidLocationTracker(get(), get()) }

    factory<PreferenceStoreFacade> { PreferenceStoreImpl(get()) }

    single<WeatherApi> {
        val mediaType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(WeatherApi.BASE_URL)
            //  .client(get())
            .addConverterFactory(Json.asConverterFactory(mediaType))
            .build()
            .create(WeatherApi::class.java)
    }

    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }

    factory<SearchLocationRepository> { SearchLocationRepoImpl(get()) }

    viewModel { WeatherForecastViewModel(get(), get()) }

    viewModel { SearchLocationViewModel(get()) }

}