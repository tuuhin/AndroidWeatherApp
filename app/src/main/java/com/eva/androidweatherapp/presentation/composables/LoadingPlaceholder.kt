package com.eva.androidweatherapp.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.eva.androidweatherapp.R
import com.eva.androidweatherapp.presentation.util.ShowContent

@Composable
fun <T> LoadingPlaceholder(
    modifier: Modifier = Modifier,
    showContent: ShowContent<T> = ShowContent(),
    density: Density = LocalDensity.current,
    content: (@Composable (T) -> Unit) = {},
) {
    AnimatedVisibility(
        visible = showContent.isLoading,
        enter = fadeIn() + slideInVertically { with(density) { -40.dp.roundToPx() } },
        exit = fadeOut() + shrinkOut(),
        modifier = Modifier.fillMaxSize()
    ) {
        LoadingAnimation(modifier = modifier)
    }
    AnimatedVisibility(
        visible = !showContent.isLoading,
        enter = fadeIn(initialAlpha = 0.5f) + expandIn(expandFrom = Alignment.Center)
    ) {
        showContent.content
            ?.let { data -> content(data) }
            ?: Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherImage(
                    res = R.drawable.ic_error,
                    modifier = Modifier.sizeIn(maxWidth = 100.dp, maxHeight = 100.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Failed to load resources",
                    style = MaterialTheme.typography.titleMedium
                )
            }
    }
}
