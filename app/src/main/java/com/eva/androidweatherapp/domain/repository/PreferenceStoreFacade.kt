package com.eva.androidweatherapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceStoreFacade {
    suspend fun addNewCity(name: String)

    fun getSavedCities(): Flow<Set<String>>

    suspend fun deleteCity(name: String)
}