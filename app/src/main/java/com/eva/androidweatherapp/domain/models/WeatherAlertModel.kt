package com.eva.androidweatherapp.domain.models


data class WeatherAlertModel(
    val areas: String? = null,
    val headline: String,
    val instruction: String? = null,
    val note: String? = null,
    val desc: String,
    val category: String? = null
)
