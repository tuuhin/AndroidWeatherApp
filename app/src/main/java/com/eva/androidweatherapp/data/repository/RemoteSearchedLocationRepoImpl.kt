package com.eva.androidweatherapp.data.repository

import com.eva.androidweatherapp.data.mappers.toModels
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.domain.models.LocationSearchResult
import com.eva.androidweatherapp.domain.repository.RemoteSearchLocationRepository
import com.eva.androidweatherapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class RemoteSearchedLocationRepoImpl(
    private val api: WeatherApi,
) : RemoteSearchLocationRepository {

    override suspend fun searchLocations(name: String): Resource<List<LocationSearchResult>> {
        return try {
            val data = api.searchCity(query = name)
            return Resource.Success(data.toModels())
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

}