package com.eva.androidweatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [SavedWeatherEntity::class],
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract val dao: SavedWeatherDao

    companion object {

        private const val DATABASE_NAME = "APP_DATABASE"

        fun createDataBase(context: Context): AppDataBase =
            Room
                .databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME).build()
    }
}