package com.eva.androidweatherapp.presentation.feature_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.androidweatherapp.domain.models.LocationSearchResult
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.repository.RemoteSearchLocationRepository
import com.eva.androidweatherapp.domain.repository.SavedCityWeatherRepository
import com.eva.androidweatherapp.presentation.util.ShowContent
import com.eva.androidweatherapp.presentation.util.UiEvents
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class SearchLocationViewModel(
    private val search: RemoteSearchLocationRepository,
    private val database: SavedCityWeatherRepository
) : ViewModel() {

    private val _searchBarState = MutableStateFlow(LocationsSearchBarState())
    val searchBarState = _searchBarState.asStateFlow()

    private val _searchResults: MutableStateFlow<ShowContent<List<LocationSearchResult>>> =
        MutableStateFlow(ShowContent(isLoading = true))

    val searchResults = _searchResults.asStateFlow()

    private val _savedLocations = MutableStateFlow<List<SavedWeatherModel>>(emptyList())
    val savedLocation = _savedLocations.asStateFlow()

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents = _uiEvents.asSharedFlow()

    private var _searchJob: Job? = null


    init {
        getSavedLocations()
    }

    fun onSearchBarEvents(events: SearchBarEvents) {
        when (events) {
            is SearchBarEvents.OnQueryChanged -> onQueryChange(events.value)
            SearchBarEvents.SearchBarToggled -> _searchBarState.update { state ->
                val isSearchBarActive = _searchBarState.value.isActive
                state.copy(isActive = !isSearchBarActive)
            }

            is SearchBarEvents.OnRemoveCity -> removeCity(events.model)
            is SearchBarEvents.OnLocationSelect ->  onLocationSelect(events.location)
        }
    }

    private fun onQueryChange(query: String) {
        _searchBarState.update { it.copy(query = query) }
        _searchJob?.cancel()

        val trimmedLocation = _searchBarState.value.query.trim()
        _searchJob = viewModelScope.launch {

            if (trimmedLocation.isNotEmpty() && this.isActive) {
                when (val results = search.searchLocations(trimmedLocation)) {
                    is Resource.Loading -> _searchResults.update { content ->
                        content.copy(isLoading = true, content = null)
                    }

                    is Resource.Success -> _searchResults.update { content ->
                        content.copy(isLoading = false, content = results.data)
                    }

                    is Resource.Error -> _searchResults.update { content ->
                        content.copy(isLoading = false, content = null)
                    }
                }
            }
        }
    }

    private fun getSavedLocations() = viewModelScope.launch {
        database.getCityWeatherFromLocations()
            .onEach { locations -> _savedLocations.update { locations } }
            .launchIn(this)
    }


    private fun removeCity(model: SavedWeatherModel) = viewModelScope.launch {
        when (val result = database.removeCity(model)) {
            is Resource.Error -> _uiEvents
                .emit(UiEvents.ShowSnackBar(result.message ?: "FAILED TO REMOVE A CITY"))

            is Resource.Loading -> {}
            is Resource.Success -> UiEvents.ShowSnackBar("${model.name.uppercase()} REMOVED")
        }
    }


    private fun onLocationSelect(location: String) = viewModelScope.launch {
        when (val result = database.addCity(location)) {
            is Resource.Error -> _uiEvents
                .emit(UiEvents.ShowSnackBar(result.message ?: "FAILED TO ADD A CITY"))

            is Resource.Loading -> {}
            is Resource.Success -> {
                UiEvents.ShowSnackBar("$location added")
                _searchBarState.update { LocationsSearchBarState() }
            }
        }
    }
}


