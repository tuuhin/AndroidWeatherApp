package com.eva.androidweatherapp.data.local

import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object AppMigrations {

    private const val tableNameOld = "SavedLocations"

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE `SavedLocations` ADD COLUMN `lastUpdated` TEXT NULL")
        }
    }

    @DeleteColumn.Entries(
        DeleteColumn(tableNameOld, "image"),
        DeleteColumn(tableNameOld, "windSpeedInMh"),
        DeleteColumn(tableNameOld, "tempInFahrenheit"),
        DeleteColumn(tableNameOld, "precipitationMM"),
        DeleteColumn(tableNameOld, "feelsLikeFahrenheit")
    )
    class DeleteUnwantedColumns : AutoMigrationSpec

    @RenameColumn.Entries(
        RenameColumn(tableName = tableNameOld, "id", "ID"),
        RenameColumn(tableName = tableNameOld, "condition", "CONDITION"),
        RenameColumn(tableName = tableNameOld, "code", "WEATHER_CODE"),
        RenameColumn(tableName = tableNameOld, "feelsLikeInCelsius", "FEELS_LIKE_TEMP_C"),
        RenameColumn(tableName = tableNameOld, "humidity", "HUMIDITY"),
        RenameColumn(tableName = tableNameOld, "precipitationInch", "PRECIPITATION_INCH"),
        RenameColumn(tableName = tableNameOld, "tempInCelsius", "TEMP_C"),
        RenameColumn(tableName = tableNameOld, "pressureMilliBar", "PRESSURE_MBAR"),
        RenameColumn(tableName = tableNameOld, "windSpeedInKmh", "WIND_SPEED_KMPH"),
        RenameColumn(tableName = tableNameOld, "country", "COUNTRY"),
        RenameColumn(tableName = tableNameOld, "name", "NAME"),
        RenameColumn(tableName = tableNameOld, "region", "REGION"),
        RenameColumn(tableName = tableNameOld, "lastUpdated", "LAST_UPDATED")
    )
    class RenameAllColumnsToCaps : AutoMigrationSpec

    @RenameTable(fromTableName = tableNameOld, toTableName = "SAVED_LOCATIONS")
    class RenameTableName : AutoMigrationSpec

}
