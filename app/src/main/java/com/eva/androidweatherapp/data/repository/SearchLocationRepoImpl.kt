package com.eva.androidweatherapp.data.repository

import com.eva.androidweatherapp.data.mappers.toModels
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.domain.repository.SearchLocationRepository
import com.eva.androidweatherapp.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class SearchLocationRepoImpl(
    private val api: WeatherApi,
) : SearchLocationRepository {

    override suspend fun searchLocations(name: String): Resource<List<SearchLocationResult>> {
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