package com.eva.androidweatherapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseLocationModel(
    val latitude: Double,
    val longitude: Double
) : Parcelable
