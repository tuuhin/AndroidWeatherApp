package com.eva.androidweatherapp.presentation.feature_daily

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.domain.models.WeatherForeCastModel
import com.eva.androidweatherapp.presentation.feature_daily.composables.ForeCastGraph
import com.eva.androidweatherapp.presentation.feature_daily.composables.WeatherDayCard
import com.eva.androidweatherapp.presentation.util.PreviewFakeData
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekForecastRoute(
    forecastModel: WeatherForeCastModel,
    modifier: Modifier = Modifier,
    navigation: (@Composable () -> Unit)? = null,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(id = R.string.forecast_route_title)) },
                navigationIcon = navigation ?: {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                ),
            )
        },
        contentWindowInsets = WindowInsets.navigationBars,
    ) { scPadding ->
        Column(
            modifier = modifier
                .padding(scPadding)
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            ForeCastGraph(
                forecast = forecastModel,
                modifier = Modifier.aspectRatio(1.5f)
            )
            Text(
                text = stringResource(id = R.string.forecast_route_title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(forecastModel.forecast) { _, forecast ->
                    WeatherDayCard(
                        forecastModel = forecast,
                        modifier = Modifier.wrapContentHeight()
                    )
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun WeekForeCastRoutePreview() = AndroidWeatherAppTheme {
    WeekForecastRoute(
        forecastModel = PreviewFakeData.fakeForeCastModel,
        navigation = {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "Arrow Symbol"
            )
        },
    )
}
