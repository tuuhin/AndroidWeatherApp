package com.eva.androidweatherapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.glance.text.Text
import com.eva.androidweatherapp.presentation.util.ShowContent

@Composable
fun <T> LoadingPlaceholder(
    modifier: Modifier = Modifier,
    showContent: ShowContent<T> = ShowContent(),
    content: (@Composable (T) -> Unit) = {}
) {
    if (showContent.isLoading)
        LoadingAnimation(modifier = modifier)
    else if (showContent.content == null)
        Text(text = "Failed")
    else
        content(showContent.content)
}
