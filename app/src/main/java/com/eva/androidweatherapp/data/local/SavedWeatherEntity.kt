package com.eva.androidweatherapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SAVED_LOCATIONS")
data class SavedWeatherEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("ID")
    val id: Int? = null,

    @ColumnInfo("CONDITION")
    val condition: String,

    @ColumnInfo("WEATHER_CODE")
    val code: Int,

    @ColumnInfo("FEELS_LIKE_TEMP_C")
    val feelsLikeInCelsius: Float,
    @ColumnInfo("HUMIDITY")
    val humidity: Float,

    @ColumnInfo("PRECIPITATION_INCH")
    val precipitationInch: Float,
    @ColumnInfo("PRESSURE_MBAR")
    val pressureMilliBar: Float,

    @ColumnInfo("TEMP_C")
    val tempInCelsius: Float,

    @ColumnInfo("WIND_SPEED_KMPH")
    val windSpeedInKmh: Float,

    @ColumnInfo("COUNTRY")
    val country: String,

    @ColumnInfo("NAME")
    val name: String,

    @ColumnInfo("REGION")
    val region: String,

    @ColumnInfo("LAST_UPDATED")
    val lastUpdated: String? = null
)
