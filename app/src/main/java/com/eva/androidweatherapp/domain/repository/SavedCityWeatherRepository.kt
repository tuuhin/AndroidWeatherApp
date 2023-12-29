package com.eva.androidweatherapp.domain.repository

import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SavedCityWeatherRepository {
    suspend fun getCityWeatherFromLocations(): Flow<List<SavedWeatherModel>>

    suspend fun removeCity(model: SavedWeatherModel): Resource<Boolean>

    suspend fun addCity(name: String): Resource<SavedWeatherModel>
}