package com.eva.androidweatherapp.widgets.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.glance.LocalContext

@Composable
fun isCurrentLocalAmericanGlance(
    context: Context = LocalContext.current
): Boolean {
    val locale by remember {
        derivedStateOf {
            val current = context.resources.configuration.locales.get(0)
            current.country == "US" && current.language == "en"
        }
    }
    return locale
}