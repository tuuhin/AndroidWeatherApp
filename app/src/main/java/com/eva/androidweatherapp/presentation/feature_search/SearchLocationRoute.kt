package com.eva.androidweatherapp.presentation.feature_search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.presentation.feature_search.composables.CitySearchBar
import com.eva.androidweatherapp.presentation.feature_search.composables.NoSearchResults
import com.eva.androidweatherapp.presentation.feature_search.composables.WeatherForecastCard
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.ShowContent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchLocationsRoute(
    state: CitySearchBarState,
    onEvents: (SearchBarEvents) -> Unit,
    modifier: Modifier = Modifier,
    searchResults: ShowContent<List<SearchLocationResult>>,
    savedLocations: List<SavedWeatherModel>,
    snackBarState: SnackbarHostState = LocalSnackBarHostState.current
) {
    val searchBarPadding by animateDpAsState(if (state.isActive) 0.dp else 8.dp)

    Scaffold(
        topBar = {
            CitySearchBar(
                query = state.query,
                onQuery = { onEvents(SearchBarEvents.OnQueryChanged(it)) },
                isActive = state.isActive,
                onActiveChange = { onEvents(SearchBarEvents.SearchBarToggled) },
                onLocationSelect = { onEvents(SearchBarEvents.OnLocationSelect(it)) },
                results = searchResults,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = searchBarPadding)
            )
        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface,
        snackbarHost = { SnackbarHost(hostState = snackBarState) }
    ) { scPadding ->
        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            when {
                savedLocations.isNotEmpty() -> LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Text(
                            text = stringResource(id = R.string.saved_location_helper_text),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.tertiary,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                        )
                    }
                    itemsIndexed(savedLocations) { _, current ->
                        WeatherForecastCard(
                            model = current,
                            onRemove = { onEvents(SearchBarEvents.OnRemoveCity(current)) },
                            modifier = Modifier
                                .animateItemPlacement(tween(400))
                        )
                    }
                }

                else -> NoSearchResults(
                    text = "No saved locations",
                    color = MaterialTheme.colorScheme.inverseSurface,
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


class SavedLocationsPreviewParams :
    CollectionPreviewParameterProvider<List<SavedWeatherModel>>(
        listOf(
            List(3) { PreviewFakeData.fakeSavedWeatherModel },
            emptyList()
        )
    )


@Preview
@Composable
fun SavedLocationRoutePreview(
    @PreviewParameter(SavedLocationsPreviewParams::class)
    savedLocation: List<SavedWeatherModel>
) {
    SearchLocationsRoute(
        savedLocations = savedLocation,
        state = CitySearchBarState(),
        searchResults = ShowContent(),
        onEvents = {}
    )
}