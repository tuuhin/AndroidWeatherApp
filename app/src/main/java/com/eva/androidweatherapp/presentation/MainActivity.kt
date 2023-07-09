package com.eva.androidweatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.eva.androidweatherapp.presentation.composables.NoLocationPermissions
import com.eva.androidweatherapp.presentation.navigation.NavigationGraph
import com.eva.androidweatherapp.presentation.util.checkLocationPermissions
import com.eva.androidweatherapp.ui.theme.AndroidWeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.inverseOnSurface
                ) {
                    val locationPermissions = checkLocationPermissions()

                    if (locationPermissions)
                        NavigationGraph()
                    else
                        NoLocationPermissions()
                }
            }
        }
    }
}

