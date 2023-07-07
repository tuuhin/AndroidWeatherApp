package com.eva.androidweatherapp.presentation.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun isCurrentLocaleAmerican(
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