package com.eva.androidweatherapp.presentation.feature_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.domain.repository.SaveLocationRepository
import com.eva.androidweatherapp.domain.repository.SearchLocationRepository
import com.eva.androidweatherapp.presentation.util.ShowContent
import com.eva.androidweatherapp.utils.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchLocationViewModel(
    private val search: SearchLocationRepository,
    private val database: SaveLocationRepository
) : ViewModel() {

    private val _isSearchActive = MutableStateFlow(false)
    val isSearchActive = _isSearchActive.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _searchResults =
        MutableStateFlow(ShowContent<List<SearchLocationResult>>(isLoading = true))
    val searchResults = _searchResults.asStateFlow()

    private val _savedLocations =
        MutableStateFlow(ShowContent<List<SavedWeatherModel>>(isLoading = true))
    val savedLocation = _savedLocations.asStateFlow()


    private var _searchJob: Job? = null


    init {
        getSavedLocations()
    }

    fun onQueryChange(query: String) {
        _query.update { query }
        _searchJob?.cancel()

        _searchJob = viewModelScope.launch {
            val trimmedData = _query.value.trim()

            if (trimmedData.isNotEmpty()) {
                when (val results = search.searchLocations(trimmedData)) {
                    is Resource.Loading -> _searchResults.update {
                        ShowContent(isLoading = true, content = null)
                    }

                    is Resource.Success -> _searchResults.update {
                        ShowContent(isLoading = false, content = results.data)
                    }

                    is Resource.Error -> _searchResults.update {
                        ShowContent(isLoading = false, content = null)
                    }
                }
            }
        }
    }

    fun getSavedLocations() {
        viewModelScope.launch {
            database.getCityWeatherFromLocations()
                .onEach { locations ->
                    _savedLocations.update { ShowContent(isLoading = false, content = locations) }
                }.launchIn(this)
        }
    }


    fun onToggleSearch(isActive: Boolean) {
        _isSearchActive.update { isActive }
    }

    fun onLocationSelect(location: String) {
        _isSearchActive.update { false }
        _query.update { "" }
        viewModelScope.launch { database.addCity(location) }
    }


}