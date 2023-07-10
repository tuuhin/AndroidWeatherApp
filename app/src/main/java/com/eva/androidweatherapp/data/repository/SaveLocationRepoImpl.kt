package com.eva.androidweatherapp.data.repository

import com.eva.androidweatherapp.data.local.AppDataBase
import com.eva.androidweatherapp.data.mappers.toEntity
import com.eva.androidweatherapp.data.mappers.toModel
import com.eva.androidweatherapp.data.mappers.toSaveModel
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.repository.SaveLocationRepository
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class SaveLocationRepoImpl(
    private val api: WeatherApi,
    private val database: AppDataBase
) : SaveLocationRepository {

    private val dao by lazy { database.dao }

    private suspend fun getCityWeather(name: String): Resource<SavedWeatherModel> {
        return try {
            val data = api.getCurrentData(query = name).toSaveModel()
            Resource.Success(data)
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Http Exception")
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(e.message ?: "IO error Occurred")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Exception Occurred")
        }
    }

    override suspend fun addCity(name: String): Resource<SavedWeatherModel> {
        return when (val res = getCityWeather(name)) {
            is Resource.Success -> {
                res.data?.let { model -> dao.upsertLocation(model.toEntity()) }
                res
            }

            else -> res
        }
    }

    override suspend fun removeCity(model: SavedWeatherModel) {
        dao.deleteLocation(model.toEntity())
    }


    override suspend fun getCityWeatherFromLocations(): Flow<List<SavedWeatherModel>> {
        return dao.getSavedLocations().map { entities ->
            entities.map { it.toModel() }
        }
    }
}