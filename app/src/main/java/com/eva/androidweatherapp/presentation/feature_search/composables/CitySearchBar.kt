package com.eva.androidweatherapp.presentation.feature_search.composables

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
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
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.LocationSearchResult
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.presentation.util.ShowContent
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun CitySearchBar(
    query: String,
    onQuery: (String) -> Unit,
    isActive: Boolean,
    onActiveChange: (Boolean) -> Unit,
    onLocationSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = SearchBarDefaults.inputFieldShape,
    windowInsets: WindowInsets = SearchBarDefaults.windowInsets,
    results: ShowContent<List<LocationSearchResult>>,
) {
    SearchBar(
        query = query,
        onQueryChange = onQuery,
        onSearch = {},
        active = isActive,
        onActiveChange = onActiveChange,
        trailingIcon = {
            IconButton(onClick = { onActiveChange(false) }) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "Cancel"
                )
            }
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = "Search"
            )
        },
        placeholder = { Text(text = stringResource(id = R.string.search_placeholder)) },
        shape = shape,
        windowInsets = windowInsets,
        modifier = modifier,
    ) {
        if (query.isNotEmpty()) {
            when {
                results.isLoading && results.content == null -> Text(
                    text = stringResource(id = R.string.loading_placeholder),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 4.dp)
                )

                !results.content.isNullOrEmpty() -> LazyColumn(
                    contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.scaffold_horizontal_padding)),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.ime)
                        .fillMaxSize()
                ) {
                    item {
                        Text(
                            text = stringResource(id = R.string.data_from_api_text),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            textAlign = TextAlign.Right,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        )
                    }
                    itemsIndexed(items = results.content, key = { _, item -> item.id }) { _, item ->
                        LocationResultsCard(
                            searchResult = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { onLocationSelect(item.name) }
                        )
                    }
                }

                else -> NoSearchResults(
                    text = stringResource(id = R.string.city_not_found_text),
                    painter = painterResource(id = R.drawable.ic_search),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


class ResultsForSearchPreviewParams :
    CollectionPreviewParameterProvider<ShowContent<List<LocationSearchResult>>>(
        listOf(
            ShowContent(isLoading = true, content = null),
            ShowContent(
                isLoading = false,
                content = List(3) { PreviewFakeData.locationResult.copy(id = it) }),
            ShowContent(isLoading = false, content = emptyList())
        )
    )

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun CitySearchBarPreview(
    @PreviewParameter(ResultsForSearchPreviewParams::class)
    results: ShowContent<List<LocationSearchResult>>,
) = AndroidWeatherAppTheme {
    CitySearchBar(
        query = "New Yo",
        onQuery = {},
        onActiveChange = {},
        isActive = true,
        results = results,
        onLocationSelect = {}
    )
}