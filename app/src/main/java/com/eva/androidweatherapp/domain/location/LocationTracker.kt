package com.eva.androidweatherapp.domain.location

import com.eva.androidweatherapp.domain.models.BaseLocationModel
import kotlinx.coroutines.flow.Flow

abstract class LocationTracker {

    abstract suspend fun getCurrentLocation(): BaseLocationModel?

    abstract suspend fun getLastLocation(): BaseLocationModel?

    abstract suspend fun getLocationAsFlow(): Flow<BaseLocationModel?>

}