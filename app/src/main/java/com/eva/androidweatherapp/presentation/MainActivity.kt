package com.eva.androidweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.eva.androidweatherapp.presentation.composables.NoLocationPermissions
import com.eva.androidweatherapp.presentation.navigation.NavigationGraph
import com.eva.androidweatherapp.presentation.util.LocalSnackBarHostState
import com.eva.androidweatherapp.presentation.util.checkLocationPermissions
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // splash
        installSplashScreen()
        super.onCreate(savedInstanceState)
        // fill the whole window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AndroidWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.inverseOnSurface
                ) {
                    val snackBarState = remember { SnackbarHostState() }
                    val locationPermissions = checkLocationPermissions()
                    when {
                        locationPermissions -> CompositionLocalProvider(
                            LocalSnackBarHostState provides snackBarState
                        ) {
                            NavigationGraph()
                        }
                        else -> NoLocationPermissions()
                    }
                }
            }
        }
    }
}

