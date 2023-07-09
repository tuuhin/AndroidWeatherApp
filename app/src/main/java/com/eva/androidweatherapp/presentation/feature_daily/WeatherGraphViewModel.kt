package com.eva.androidweatherapp.presentation.feature_daily

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherGraphViewModel : ViewModel() {

    private val _isDropDownOpen = MutableStateFlow(false)
    val isDropDownOpen = _isDropDownOpen.asStateFlow()

    private val _graphType = MutableStateFlow(WeatherGraphType.AVG_TEMPERATURE)
    val graphType = _graphType.asStateFlow()

    fun onDropDownToggle() {
        _isDropDownOpen.update { !_isDropDownOpen.value }
    }

    fun onGraphTypeSelect(type: WeatherGraphType) {
        _graphType.update { type }
    }
}