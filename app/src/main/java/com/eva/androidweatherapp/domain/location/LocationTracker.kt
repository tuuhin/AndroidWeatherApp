package com.eva.androidweatherapp.domain.location

import com.eva.androidweatherapp.domain.models.BaseLocationModel
import kotlinx.coroutines.flow.Flow

interface LocationTracker {

    suspend fun getCurrentLocation(): BaseLocationModel?

    suspend fun getLastLocation(): BaseLocationModel?

    suspend fun getLocationAsFlow(): Flow<BaseLocationModel?>

}