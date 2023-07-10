package com.eva.androidweatherapp.presentation.feature_search

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.presentation.feature_search.composables.CitySearchBar
import com.eva.androidweatherapp.presentation.feature_search.composables.WeatherForecastCard
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.ShowContent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchLocationsRoute(
    query: String,
    onQuery: (String) -> Unit,
    isActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onLocationSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    searchResults: ShowContent<List<SearchLocationResult>>,
    overallocationResults: ShowContent<List<SavedWeatherModel>>,
) {
    val searchBarPadding by animateDpAsState(if (isActive) 0.dp else 8.dp)

    Scaffold(
        topBar = {
            CitySearchBar(
                query = query,
                onQuery = onQuery,
                isActive = isActive,
                onActiveChange = onActiveChange,
                onLocationSelect = onLocationSelect,
                results = searchResults,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = searchBarPadding)
            )
        },
        containerColor = MaterialTheme.colorScheme.inverseOnSurface
    ) { scPadding ->
        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            if (overallocationResults.isLoading)
                Text(
                    text = "Fetching...",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 4.dp)
                )
            else overallocationResults.content?.let { results ->
                if (results.isEmpty())
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No results")
                    }
                else LazyColumn(
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    itemsIndexed(results) { _, current ->
                        WeatherForecastCard(
                            model = current,
                            modifier = Modifier.animateItemPlacement(tween(400))
                        )
                    }
                }
            }
        }
    }
}


class SavedLocationsPreviewParams :
    CollectionPreviewParameterProvider<ShowContent<List<SavedWeatherModel>>>(
        listOf(
            ShowContent(isLoading = true, content = null),
            ShowContent(
                isLoading = false,
                content = List(3) { PreviewFakeData.fakeSavedWeatherModel }),
            ShowContent(isLoading = false, content = emptyList())
        )
    )


@Preview
@Composable
fun SavedLocationRoutePreview(
    @PreviewParameter(SavedLocationsPreviewParams::class)
    savedLocation: ShowContent<List<SavedWeatherModel>>
) {
    SearchLocationsRoute(
        overallocationResults = savedLocation,
        query = "New Yo",
        onQuery = {},
        onActiveChange = {},
        isActive = false,
        searchResults = ShowContent(),
        onLocationSelect = {}
    )
}