package com.eva.androidweatherapp.widgets.theme

import android.content.Context
import android.os.Build
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.glance.GlanceComposable
import androidx.glance.GlanceTheme
import androidx.glance.LocalContext
import androidx.glance.material3.ColorProviders
import com.eva.androidweatherapp.ui.theme.DarkColorScheme
import com.eva.androidweatherapp.ui.theme.LightColorScheme

@Composable
@GlanceComposable
fun WeatherAppWidgetTheme(
    context: Context = LocalContext.current,
    content: (@Composable () -> Unit) = {}
) {
    GlanceTheme(
        colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            ColorProviders(
                light = dynamicLightColorScheme(context),
                dark = dynamicDarkColorScheme(context)
            ) else
            ColorProviders(
                light = LightColorScheme,
                dark = DarkColorScheme
            ),
        content = content
    )
}