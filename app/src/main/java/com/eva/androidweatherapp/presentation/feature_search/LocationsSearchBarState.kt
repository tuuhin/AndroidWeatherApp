package com.eva.androidweatherapp.presentation.feature_search

import com.eva.androidweatherapp.domain.models.SavedWeatherModel

data class LocationsSearchBarState(
    val isActive: Boolean = false,
    val query: String = ""
)

sealed interface SearchBarEvents {
    data object SearchBarToggled : SearchBarEvents
    data class OnQueryChanged(val value: String) : SearchBarEvents
    data class OnRemoveCity(val model: SavedWeatherModel) : SearchBarEvents
    data class OnLocationSelect(val location: String) : SearchBarEvents

}