package com.eva.androidweatherapp.widgets.utils

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class AvailableSizes(val size: DpSize) {

    WIDGET_SIZE_SMALL(size = DpSize(100.dp, 50.dp)),

    WIDGET_SIZE_MEDIUM(size = DpSize(100.dp, 100.dp)),

    WIDGET_SIZE_LARGE(size = DpSize(200.dp, 100.dp));

    companion object {
        val sizes = AvailableSizes.values().map { it.size }.toSet()
    }
}