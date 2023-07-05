package com.eva.androidweatherapp.widgets.utils

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

object AvailableSizes {
    val WIDGET_SIZE_SMALL = DpSize(100.dp, 60.dp)
    val WIDGET_SIZE_MEDIUM = DpSize(100.dp, 100.dp)
    val WIDGET_SIZE_LARGE = DpSize(200.dp, 100.dp)

    val SIZES = setOf(WIDGET_SIZE_SMALL, WIDGET_SIZE_MEDIUM, WIDGET_SIZE_LARGE)
}