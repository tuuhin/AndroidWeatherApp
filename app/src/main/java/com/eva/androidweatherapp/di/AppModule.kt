package com.eva.androidweatherapp.di

import com.eva.androidweatherapp.data.location.AndroidLocationTracker
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.dsl.module

val appModule = module {

    single<FusedLocationProviderClient> { LocationServices.getFusedLocationProviderClient(get()) }

    factory<LocationTracker> { AndroidLocationTracker(get(), get()) }

}