package com.eva.androidweatherapp.widgets.utils

import androidx.compose.ui.unit.DpSize

fun DpSize.toWidgetSize() = when (this) {
    AvailableSizes.WIDGET_SIZE_SMALL.size  -> AvailableSizes.WIDGET_SIZE_SMALL
    AvailableSizes.WIDGET_SIZE_MEDIUM.size -> AvailableSizes.WIDGET_SIZE_MEDIUM
    else -> AvailableSizes.WIDGET_SIZE_LARGE
}