package com.eva.androidweatherapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedWeatherDao {

    @Upsert
    suspend fun upsertWeatherEntity(entity: SavedWeatherEntity)

    @Upsert
    suspend fun upsertWeatherEntities(entity: List<SavedWeatherEntity>)

    @Query("SELECT * FROM SAVED_LOCATIONS")
    fun getSavedWeatherEntitiesAsFlow(): Flow<List<SavedWeatherEntity>>

    @Query("SELECT * FROM SAVED_LOCATIONS")
    fun getSavedWeatherEntitiesAsList(): List<SavedWeatherEntity>

    @Delete
    suspend fun deleteWeatherEntity(entity: SavedWeatherEntity)

    @Delete
    suspend fun deleteWeatherEntities(entities: List<SavedWeatherEntity>)
}