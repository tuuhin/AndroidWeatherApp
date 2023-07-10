package com.eva.androidweatherapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.androidweatherapp.domain.location.LocationTracker
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.presentation.util.ShowContent
import com.eva.androidweatherapp.presentation.util.UiEvents
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    private val locationTracker: LocationTracker,
    private val weatherRepository: WeatherRepository,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _content =
        MutableStateFlow<ShowContent<WeatherForeCastModel>>(ShowContent(isLoading = true))
    val content = _content.asStateFlow()

    init {
        getCurrentWeather()
    }


    private fun getCurrentWeather() {
        viewModelScope.launch {
            locationTracker.getLastLocation()?.let { location ->
                weatherRepository.getWeatherForecastOneDayFromLatAndLong(location)
                    .onEach { res ->
                        when (res) {
                            is Resource.Error -> {
                                _content.update {
                                    ShowContent(isLoading = false, content = null)
                                }
                                _uiEvent.emit(
                                    UiEvents.ShowSnackBar(res.message ?: "Error Occurred")
                                )
                            }

                            is Resource.Success -> _content.update {
                                ShowContent(isLoading = false, content = res.data)
                            }

                            else -> {}
                        }

                    }.launchIn(this)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d("VIEWMODEL", "CLEARED")
    }
}