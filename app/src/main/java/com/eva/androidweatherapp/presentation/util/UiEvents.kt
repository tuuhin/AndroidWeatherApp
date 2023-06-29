package com.eva.androidweatherapp.presentation.util

sealed interface UiEvents {
    data class ShowSnackBar(val message: String) : UiEvents
}