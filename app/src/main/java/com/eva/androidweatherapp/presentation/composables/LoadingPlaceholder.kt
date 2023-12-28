package com.eva.androidweatherapp.presentation.composables

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import com.eva.androidweatherapp.presentation.util.ShowContent

@Composable
fun <T> LoadingPlaceholder(
    modifier: Modifier = Modifier,
    showContent: ShowContent<T> = ShowContent(),
    content: (@Composable (T) -> Unit) = {},
) {
    AnimatedContent(
        targetState = showContent.isLoading,
        label = "IS LOADING DONE",
        transitionSpec = {
            fadeIn() + expandIn(expandFrom = Alignment.Center) { IntSize(100, 100) } togetherWith
                    fadeOut() + shrinkOut()
        },
    ) { isLoading ->
        if (!isLoading) showContent.content
            ?.let { data -> content(data) }
            ?: FailedToLoad(modifier = modifier)
        else LoadingAnimation()
    }
}

