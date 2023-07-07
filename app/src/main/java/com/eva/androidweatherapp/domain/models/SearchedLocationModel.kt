package com.eva.androidweatherapp.domain.models

import java.time.LocalDateTime


data class SearchedLocationModel(
    val country: String,
    val time: LocalDateTime,
    val name: String,
    val region: String,
)