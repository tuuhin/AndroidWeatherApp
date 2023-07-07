package com.eva.androidweatherapp.presentation.feature_daily

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_daily.composables.WeatherDayCard
import com.eva.androidweatherapp.presentation.util.PreviewFakeData

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun WeekForecastRoute(
    model: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    navigation: (@Composable () -> Unit)? = null,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Weekly data") },
                navigationIcon = navigation ?: {}
            )
        }
    ) { scPadding ->
        LazyColumn(
            contentPadding = scPadding,
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(model.forecast) { _, forecast ->
                WeatherDayCard(dayModel = forecast, modifier = Modifier.padding(horizontal = 8.dp))
            }
        }
    }
}


@Preview
@Composable
fun WeekForeCastRoutePreview() {
    Surface(
        modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        WeekForecastRoute(
            model = PreviewFakeData.fakeForeCastModel,
        )

    }
}