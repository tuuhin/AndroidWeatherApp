package com.eva.androidweatherapp.domain.models


data class WeatherAlertModel(
    val areas: String,
    val headline: String,
    val instruction: String,
    val note: String,
)
