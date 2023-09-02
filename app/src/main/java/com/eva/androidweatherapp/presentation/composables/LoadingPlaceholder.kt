package com.eva.androidweatherapp.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
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
        enter = fadeIn() +
                expandIn(expandFrom = Alignment.Center) { IntSize(100, 100) },
        modifier = Modifier.fillMaxSize()
    ) {
        showContent.content
            ?.let { data -> content(data) }
            ?: FailedToLoad(modifier = modifier)
    }
}

