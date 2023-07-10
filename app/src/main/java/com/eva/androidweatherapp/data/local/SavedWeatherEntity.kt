package com.eva.androidweatherapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SavedLocations")
data class SavedWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val condition: String,
    val code: Int,
    val image: Int,
    val feelsLikeInCelsius: Float,
    val feelsLikeFahrenheit: Float,
    val humidity: Float,
    val precipitationInch: Float,
    val precipitationMM: Float,
    val pressureMilliBar: Float,
    val tempInCelsius: Float,
    val tempInFahrenheit: Float,
    val windSpeedInKmh: Float,
    val windSpeedInMh: Float,
    val country: String,
    val name: String,
    val region: String,
)
