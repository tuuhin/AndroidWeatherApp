package com.eva.androidweatherapp.data.repository

import com.eva.androidweatherapp.data.mappers.toModel
import com.eva.androidweatherapp.data.remote.WeatherApi
import com.eva.androidweatherapp.domain.models.BaseLocationModel
import com.eva.androidweatherapp.domain.models.CurrentWeatherModel
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.utils.BooleanResponse
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherForecastOneDayFromLatAndLong(location: BaseLocationModel)
            : Flow<Resource<WeatherForeCastModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = weatherApi.getWeatherForecast(
                    query = "${location.latitude},${location.longitude}",
                    days = 7,
                    alert = BooleanResponse.FALSE,
                    hourCount = 10,
                    quality = BooleanResponse.FALSE,
                )
                emit(Resource.Success(data = data.toModel()))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(e.message ?: "Http exception occurred"))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(e.message ?: "IO exception occurred"))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message ?: "Exception occurred"))
            }
        }
    }

    override suspend fun getWeatherForecastOneDayFromName(name: String)
            : Flow<Resource<WeatherForeCastModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = weatherApi.getWeatherForecast(
                    query = name,
                    days = 1,
                    alert = BooleanResponse.FALSE,
                    hourCount = 10,
                    quality = BooleanResponse.FALSE,
                )
                emit(Resource.Success(data = data.toModel()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http exception occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "IO exception occurred"))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Exception occurred"))
            }
        }
    }

    override suspend fun getBasicWeatherInfoFromLatAndLong(location: BaseLocationModel)
            : Flow<Resource<CurrentWeatherModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data =
                    weatherApi.getCurrentData(query = "${location.latitude},${location.longitude}")
                emit(Resource.Success(data = data.toModel()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http exception occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "IO exception occurred"))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Exception occurred"))
            }
        }
    }

    override suspend fun getBasicWeatherInfoFromName(name: String)
            : Flow<Resource<CurrentWeatherModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = weatherApi.getCurrentData(query = name)
                emit(Resource.Success(data = data.toModel()))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "Http exception occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(e.message ?: "IO exception occurred"))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Exception occurred"))
            }
        }
    }
}