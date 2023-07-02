package com.eva.androidweatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val locationTracker: LocationTracker,
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            locationTracker.getCurrentLocation()?.let { location ->
                weatherRepository
                    .getWeatherForecastOneDayFromLatAndLong(location)
                    .onEach { resource ->
                        when (resource) {
                            is Resource.Error -> {
                                Log.d("ERROR", resource.message ?: "")
                            }

                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                Log.d("SUCCESS", resource.data.toString())
                            }
                        }
                    }.launchIn(this)
            }
        }
    }
}