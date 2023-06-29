package com.eva.androidweatherapp.domain.location

import com.eva.androidweatherapp.domain.models.BaseLocationModel

abstract class LocationTracker {

    abstract suspend fun getCurrentLocation(): BaseLocationModel?

    abstract val isLocationEnabled: Boolean

    abstract val isGpsEnabled:Boolean

    abstract val isNetworkProviderEnabled:Boolean
}