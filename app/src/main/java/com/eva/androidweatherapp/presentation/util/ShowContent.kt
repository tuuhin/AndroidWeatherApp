package com.eva.androidweatherapp.presentation.util

data class ShowContent<T>(
    val isLoading: Boolean = true,
    val content: T? = null
)
