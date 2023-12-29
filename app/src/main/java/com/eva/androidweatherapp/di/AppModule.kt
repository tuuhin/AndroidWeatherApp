package com.eva.androidweatherapp.di

import com.eva.androidweatherapp.data.local.AppDataBase
import com.eva.androidweatherapp.data.location.AndroidLocationTracker
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.data.repository.RemoteSearchedLocationRepoImpl
import com.eva.androidweatherapp.data.repository.SavedCityWeatherRepoImpl
import com.eva.androidweatherapp.data.repository.WeatherRepositoryImpl
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.repository.RemoteSearchLocationRepository
import com.eva.androidweatherapp.domain.repository.SavedCityWeatherRepository
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.presentation.WeatherForecastViewModel
import com.eva.androidweatherapp.presentation.feature_search.SearchLocationViewModel
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single { WeatherApi.createApiInstance() }

    single { AppDataBase.createDataBase(get()) }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    factoryOf(::AndroidLocationTracker) bind LocationTracker::class

    factoryOf(::WeatherRepositoryImpl) bind WeatherRepository::class

    factory {
        val dataBase: AppDataBase = get<AppDataBase>()
        dataBase.getDao()
    }

    factoryOf(::RemoteSearchedLocationRepoImpl) bind RemoteSearchLocationRepository::class

    factoryOf(::SavedCityWeatherRepoImpl) bind SavedCityWeatherRepository::class

    viewModelOf(::WeatherForecastViewModel)

    viewModelOf(::SearchLocationViewModel)

}