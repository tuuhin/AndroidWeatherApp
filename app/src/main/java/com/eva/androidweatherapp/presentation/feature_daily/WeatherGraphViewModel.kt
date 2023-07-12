package com.eva.androidweatherapp.presentation.feature_daily

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WeatherGraphViewModel : ViewModel() {

    private val _graphState = MutableStateFlow(GraphInteractionState())
    val graphState = _graphState.asStateFlow()

    fun onGraphEvents(events: GraphInteractionEvents) {
        when (events) {

            GraphInteractionEvents.OnToggleDropDown -> {
                _graphState.update {
                    it.copy(isDropDownOpen = !_graphState.value.isDropDownOpen)
                }
            }

            is GraphInteractionEvents.OngraphSelect -> {
                _graphState.update {
                    it.copy(graph = events.type)
                }
            }
        }
    }

}