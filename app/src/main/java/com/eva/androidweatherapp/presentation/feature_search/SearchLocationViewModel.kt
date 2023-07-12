package com.eva.androidweatherapp.presentation.feature_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.domain.repository.SaveLocationRepository
import com.eva.androidweatherapp.domain.repository.SearchLocationRepository
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
    private val search: SearchLocationRepository, private val database: SaveLocationRepository
) : ViewModel() {

    private val _searchBarState = MutableStateFlow(CitySearchBarState())
    val searchBarState = _searchBarState.asStateFlow()

    private val _searchResults =
        MutableStateFlow(ShowContent<List<SearchLocationResult>>(isLoading = true))
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
            SearchBarEvents.SearchBarToggled -> _searchBarState.update {
                it.copy(isActive = !_searchBarState.value.isActive)
            }

            is SearchBarEvents.OnRemoveCity -> removeCity(events.model)
            is SearchBarEvents.OnLocationSelect -> onLocationSelect(events.location)
        }
    }

    private fun onQueryChange(query: String) {
        _searchBarState.update { it.copy(query = query) }
        _searchJob?.cancel()

        _searchJob = viewModelScope.launch {
            val trimmedLocation = _searchBarState.value.query.trim()

            if (trimmedLocation.isNotEmpty() && isActive) {
                when (val results = search.searchLocations(trimmedLocation)) {
                    is Resource.Loading -> _searchResults.update { showContent ->
                        showContent.copy(isLoading = true, content = null)
                    }

                    is Resource.Success -> _searchResults.update { showContent ->
                        showContent.copy(isLoading = false, content = results.data)
                    }

                    is Resource.Error -> _searchResults.update { showContent ->
                        showContent.copy(isLoading = false, content = null)
                    }
                }
            }
        }
    }

    private fun getSavedLocations() {
        viewModelScope.launch {
            database.getCityWeatherFromLocations()
                .onEach { locations -> _savedLocations.update { locations } }.launchIn(this)
        }
    }



    private fun removeCity(model: SavedWeatherModel) {
        viewModelScope.launch {
            when (val result = database.removeCity(model)) {
                is Resource.Error -> _uiEvents
                    .emit(UiEvents.ShowSnackBar(result.message ?: "Failed to add City")
                )

                is Resource.Loading -> {}
                is Resource.Success -> UiEvents.ShowSnackBar("${model.name} removed")
            }
        }
    }

    private fun onLocationSelect(location: String) {
        viewModelScope.launch {
            when (val result = database.addCity(location)) {
                is Resource.Error -> _uiEvents
                    .emit(UiEvents.ShowSnackBar(result.message ?: "Failed to add City")
                )

                is Resource.Loading -> {}
                is Resource.Success -> UiEvents.ShowSnackBar("$location added")
            }
        }
        _searchBarState.update { CitySearchBarState() }
    }


}