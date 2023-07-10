package com.eva.androidweatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.eva.androidweatherapp.data.local.AppDataBase
import com.eva.androidweatherapp.data.location.AndroidLocationTracker
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.data.repository.PreferenceStoreImpl
import com.eva.androidweatherapp.data.repository.SaveLocationRepoImpl
import com.eva.androidweatherapp.data.repository.SearchLocationRepoImpl
import com.eva.androidweatherapp.data.repository.WeatherRepositoryImpl
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.repository.PreferenceStoreFacade
import com.eva.androidweatherapp.domain.repository.SaveLocationRepository
import com.eva.androidweatherapp.domain.repository.SearchLocationRepository
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.presentation.WeatherForecastViewModel
import com.eva.androidweatherapp.presentation.feature_search.SearchLocationViewModel
import com.google.android.gms.location.LocationServices
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.time.Duration
import java.util.concurrent.TimeUnit

val Context.configStore: DataStore<Preferences> by preferencesDataStore(name = "APP_CONFIGURATION")

val appModule = module {

    single {
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor())
            .readTimeout(Duration.ofMinutes(2))
            .build()

    }
    single { WeatherApi.createApiInstance(get(), get()) }

    single { AppDataBase.createDataBase(get()) }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    factory<LocationTracker> { AndroidLocationTracker(get(), get()) }

    factory<PreferenceStoreFacade> { PreferenceStoreImpl(get()) }

    factory<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }

    factory<SearchLocationRepository> { SearchLocationRepoImpl(get()) }

    factory<SaveLocationRepository> { SaveLocationRepoImpl(get(), get()) }

    viewModel { WeatherForecastViewModel(get(), get()) }

    viewModel { SearchLocationViewModel(get(), get()) }

}