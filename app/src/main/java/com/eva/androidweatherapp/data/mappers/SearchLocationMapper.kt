package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.common.SearchedLocationDto
import com.eva.androidweatherapp.domain.models.SearchedLocationModel

fun SearchedLocationDto.toModel(): SearchedLocationModel =
    SearchedLocationModel(
        country = country,
        time = time,
        name = name,
        region = region
    )