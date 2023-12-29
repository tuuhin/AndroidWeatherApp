package com.eva.androidweatherapp.presentation.feature_search

import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.LocationSearchResult
import com.eva.androidweatherapp.domain.models.SavedWeatherModel
import com.eva.androidweatherapp.presentation.feature_search.composables.CitySearchBar
import com.eva.androidweatherapp.presentation.feature_search.composables.NoSearchResults
import com.eva.androidweatherapp.presentation.feature_search.composables.WeatherForecastCard
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.ShowContent
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@Composable
fun SearchLocationsRoute(
    state: LocationsSearchBarState,
    onEvents: (SearchBarEvents) -> Unit,
    searchResults: ShowContent<List<LocationSearchResult>>,
    savedLocations: List<SavedWeatherModel>,
    modifier: Modifier = Modifier,
    snackBarState: SnackbarHostState = LocalSnackBarHostState.current
) {
    val searchBarPadding by animateDpAsState(
        targetValue = if (state.isActive) 0.dp else dimensionResource(id = R.dimen.scaffold_horizontal_padding),
        label = "SearchBarAnimation", animationSpec = tween(easing = FastOutSlowInEasing)
    )

    Scaffold(
        topBar = {
            CitySearchBar(
                query = state.query,
                onQuery = { query -> onEvents(SearchBarEvents.OnQueryChanged(query)) },
                isActive = state.isActive,
                onActiveChange = { onEvents(SearchBarEvents.SearchBarToggled) },
                onLocationSelect = { location ->
                    onEvents(SearchBarEvents.OnLocationSelect(location))
                },
                results = searchResults,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(searchBarPadding),
            )

        },
        snackbarHost = { SnackbarHost(hostState = snackBarState) },
        modifier = modifier,
    ) { scPadding ->
        when {
            savedLocations.isNotEmpty() -> LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = scPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensionResource(id = R.dimen.scaffold_horizontal_padding)),
            ) {
                item {
                    Text(
                        text = stringResource(id = R.string.saved_location_helper_text),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                itemsIndexed(savedLocations) { _, current ->
                    WeatherForecastCard(
                        model = current,
                        onRemove = { onEvents(SearchBarEvents.OnRemoveCity(current)) },
                    )
                }
            }

            else -> NoSearchResults(
                text = stringResource(id = R.string.no_saved_locations),
                color = MaterialTheme.colorScheme.inverseSurface,
                tint = MaterialTheme.colorScheme.surfaceTint,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxSize()
            )
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


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun SavedLocationRoutePreview(
    @PreviewParameter(SavedLocationsPreviewParams::class)
    savedLocation: List<SavedWeatherModel>
) = AndroidWeatherAppTheme {
    SearchLocationsRoute(
        savedLocations = savedLocation,
        state = LocationsSearchBarState(),
        searchResults = ShowContent(),
        onEvents = {},
    )
}
