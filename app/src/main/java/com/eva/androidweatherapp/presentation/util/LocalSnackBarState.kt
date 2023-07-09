package com.eva.androidweatherapp.presentation.util

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackBarHostState = compositionLocalOf { SnackbarHostState() }