package com.eva.androidweatherapp.widgets

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.CircularProgressIndicator
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.text.Text
import com.eva.androidweatherapp.domain.models.CurrentWeatherBasicModel
import com.eva.androidweatherapp.domain.repository.WeatherRepository
import com.eva.androidweatherapp.utils.Resource
import com.eva.androidweatherapp.widgets.composables.WeatherErrorLayout
import com.eva.androidweatherapp.widgets.composables.WeatherWidgetLayouts
import com.eva.androidweatherapp.widgets.theme.WeatherAppWidgetTheme
import com.eva.androidweatherapp.widgets.utils.AvailableSizes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

typealias CurrentWeatherResource = Resource<CurrentWeatherBasicModel>

object WeatherAppWidget : GlanceAppWidget(), KoinComponent {

    private val weatherRepo: WeatherRepository by inject()


    private val weatherData =
        MutableStateFlow<CurrentWeatherResource>(Resource.Loading())

    override val sizeMode: SizeMode
        get() = SizeMode.Responsive(AvailableSizes.SIZES)


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // suspend call can be made here
        withContext(Dispatchers.IO) {
            weatherRepo
                .getBasicWeatherInfoFromName("Howrah")
                .cancellable()
                .onEach { res -> weatherData.update { res } }
                .launchIn(this)
        }

        provideContent {
            WeatherAppWidgetTheme {
                Box(
                    modifier = GlanceModifier
                        .fillMaxSize()
                        .background(GlanceTheme.colors.surfaceVariant)
                        .cornerRadius(10.dp)
                        .appWidgetBackground(),
                    contentAlignment = Alignment.Center
                ) {
                    val state by weatherData.collectAsState()
                    when (state) {
                        is Resource.Error -> WeatherErrorLayout(
                            errorMessage = state.message ?: "",
                            action = actionRunCallback<RefreshAction>()
                        )

                        is Resource.Loading -> Column(
                            modifier = GlanceModifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(
                                color = GlanceTheme.colors.onPrimaryContainer
                            )
                            Spacer(modifier = GlanceModifier.height(2.dp))
                            Text(text = "Loading..")
                        }

                        is Resource.Success -> state.data
                            ?.let { model -> WeatherWidgetLayouts(model = model) }
                            ?: Text(text = "No results..")
                    }
                }
            }
        }
    }
}

class RefreshAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        WeatherAppWidget.update(context, glanceId)
    }
}