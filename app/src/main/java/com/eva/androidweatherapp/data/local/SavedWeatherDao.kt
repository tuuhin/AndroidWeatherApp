package com.eva.androidweatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedWeatherDao {

    @Upsert
    suspend fun upsertLocation(entity: SavedWeatherEntity)

    @Query("SELECT * FROM SavedLocations")
    fun getSavedLocations(): Flow<List<SavedWeatherEntity>>

    @Delete
    suspend fun deleteLocation(entity: SavedWeatherEntity)
}