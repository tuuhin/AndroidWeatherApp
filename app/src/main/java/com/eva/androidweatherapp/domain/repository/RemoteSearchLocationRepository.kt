package com.eva.androidweatherapp.domain.repository

import com.eva.androidweatherapp.domain.models.LocationSearchResult
import com.eva.androidweatherapp.utils.Resource

interface RemoteSearchLocationRepository {

    suspend fun searchLocations(name: String): Resource<List<LocationSearchResult>>
}