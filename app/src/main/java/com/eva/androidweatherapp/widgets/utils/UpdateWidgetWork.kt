package com.eva.androidweatherapp.widgets.utils

import android.content.Context
import android.util.Log
import androidx.glance.appwidget.updateAll
import androidx.work.ListenableWorker.Result
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.utils.Resource
import com.eva.androidweatherapp.widgets.WeatherAppWidget
import com.eva.androidweatherapp.widgets.WeatherDataDefinition
import com.eva.androidweatherapp.widgets.data.SerializedResource
import com.eva.androidweatherapp.widgets.data.toSerializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object UpdateWidgetWork : KoinComponent {

    private val locationTracker: LocationTracker by inject()

    private val repository: WeatherRepository by inject()

    private const val workKey = "WORKER_WIDGET"

    suspend fun doWork(context: Context): Result {

        var result: Result? = null

        withContext(Dispatchers.IO) {
            locationTracker.getLastLocation()
                ?.let { locations ->
                    repository
                        .getBasicWeatherInfoFromLatAndLong(locations)
                        .catch { err -> err.printStackTrace() }
                        .onEach { res -> Log.wtf(workKey, res.toString()) }
                        .collect { res ->
                            when (res) {
                                is Resource.Loading -> {
                                    WeatherDataDefinition
                                        .getDataStore(context, WeatherDataDefinition.fileName)
                                        .updateData { SerializedResource.IsLoading }
                                    WeatherAppWidget.updateAll(context)
                                }

                                is Resource.Success -> {
                                    WeatherDataDefinition
                                        .getDataStore(context, WeatherDataDefinition.fileName)
                                        .updateData {
                                            res.data?.let { model ->
                                                SerializedResource.Success(model.toSerializer())
                                            } ?: SerializedResource.Error("No data found")
                                        }
                                    WeatherAppWidget.updateAll(context)
                                    result = Result.success()
                                }

                                is Resource.Error -> {
                                    WeatherDataDefinition
                                        .getDataStore(context, WeatherDataDefinition.fileName)
                                        .updateData {
                                            SerializedResource.Error(res.message ?: "Error")
                                        }
                                    WeatherAppWidget.updateAll(context)
                                    result = Result.failure()
                                }
                            }

                        }

                } ?: run {
                WeatherDataDefinition
                    .getDataStore(context, WeatherDataDefinition.fileName)
                    .updateData { SerializedResource.NoLocationFound("No background location") }
                WeatherAppWidget.updateAll(context)
            }
        }
        return result ?: Result.failure()
    }
}