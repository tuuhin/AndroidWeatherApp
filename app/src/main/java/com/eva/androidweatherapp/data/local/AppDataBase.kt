package com.eva.androidweatherapp.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    version = 5,
    entities = [SavedWeatherEntity::class],
    autoMigrations = [
        AutoMigration(from = 2, to = 3, AppMigrations.DeleteUnwantedColumns::class),
        AutoMigration(from = 3, to = 4, AppMigrations.RenameAllColumnsToCaps::class),
        AutoMigration(from = 4, to = 5, AppMigrations.RenameTableName::class),
    ],
    exportSchema = true,
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): SavedWeatherDao

    companion object {

        private const val DATABASE_NAME = "APP_DATABASE"

        fun createDataBase(context: Context): AppDataBase {
            return Room
                .databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .addMigrations(AppMigrations.MIGRATION_1_2)
                .build()
        }
    }
}