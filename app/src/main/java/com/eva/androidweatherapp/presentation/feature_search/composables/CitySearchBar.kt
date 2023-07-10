package com.eva.androidweatherapp.presentation.feature_search.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.SearchLocationResult
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.ShowContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySearchBar(
    query: String,
    onQuery: (String) -> Unit,
    isActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onLocationSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    results: ShowContent<List<SearchLocationResult>>
) {
    SearchBar(
        query = query,
        onQueryChange = onQuery,
        onSearch = {},
        active = isActive,
        onActiveChange = onActiveChange,
        modifier = modifier,
        trailingIcon = {
            IconButton(onClick = { onActiveChange(false) }) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "Cancel")
            }
        },
        leadingIcon = {
            Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search")
        },
        windowInsets = WindowInsets.systemBars,
        placeholder = { Text(text = "Search..") },
        shape = MaterialTheme.shapes.medium,
    ) {
        when {
            results.isLoading -> Text(
                text = "Requesting...",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 4.dp)
            )

            results.content == null -> {}
            results.content.isNotEmpty() -> LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(results.content) { _, item ->
                    LocationResultsCard(
                        result = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { onLocationSelect(item.name) }
                    )
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Text(
                            text = "Data from API",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }

            else -> NoSearchResults()
        }
    }
}


class ResultsForSearchPreviewParams :
    CollectionPreviewParameterProvider<ShowContent<List<SearchLocationResult>>>(
        listOf(
            ShowContent(isLoading = true, content = null),
            ShowContent(isLoading = false, content = List(3) { PreviewFakeData.locationResult }),
            ShowContent(isLoading = false, content = emptyList())
        )
    )

@Preview
@Composable
fun CitySearchBarPreview(
    @PreviewParameter(ResultsForSearchPreviewParams::class)
    results: ShowContent<List<SearchLocationResult>>,
) {
    CitySearchBar(
        query = "New Yo",
        onQuery = {},
        onActiveChange = {},
        isActive = true,
        results = results,
        onLocationSelect = {}
    )
}