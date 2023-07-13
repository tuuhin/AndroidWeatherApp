package com.eva.androidweatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    version = 2,
    entities = [SavedWeatherEntity::class],
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract val dao: SavedWeatherDao

    companion object {

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'SavedLocations' ADD COLUMN 'lastUpdated' TEXT NULL")
            }
        }

        private const val DATABASE_NAME = "APP_DATABASE"

        fun createDataBase(context: Context): AppDataBase =
            Room
                .databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .build()
    }
}