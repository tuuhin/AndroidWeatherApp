package com.eva.androidweatherapp.widgets.data

import kotlinx.serialization.Serializable

@Serializable
sealed interface SerializedResource {

    @Serializable
    data object IsLoading : SerializedResource

    @Serializable
    data class Success(val data: WeatherModelSerializer) : SerializedResource

    @Serializable
    data class Error(val message: String) : SerializedResource

    @Serializable
    data class NoLocationFound(val message: String) : SerializedResource

}