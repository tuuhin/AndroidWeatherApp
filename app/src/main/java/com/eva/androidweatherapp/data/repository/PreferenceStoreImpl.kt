package com.eva.androidweatherapp.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.eva.androidweatherapp.di.dataStore
import com.eva.androidweatherapp.domain.repository.PreferenceStoreFacade
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class PreferenceStoreImpl(
    private val context: Context,
) : PreferenceStoreFacade {

    private val cityPresences = stringSetPreferencesKey("CITY_NAME")

    override suspend fun addNewCity(name: String) {
        context.dataStore.edit { prefs ->
            val currentData = prefs[cityPresences] ?: emptySet()
            prefs[cityPresences] = currentData.plus(name)
        }
    }

    override fun getSavedCities(): Flow<Set<String>> =
        context.dataStore.data.map { prefs -> prefs[cityPresences] ?: emptySet() }

    override suspend fun deleteCity(name: String) {
        context.dataStore.edit { prefs ->
            val currentData = prefs[cityPresences] ?: emptySet()
            if (currentData.contains(name)) {
                val updated = currentData.filter { it != name }
                prefs[cityPresences] = updated.toSet()
            }
        }
    }

}