package com.eva.androidweatherapp.domain.repository

import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.utils.Resource

interface SearchLocationRepository {

    suspend fun searchLocations(name: String): Resource<List<SearchLocationResult>>
}