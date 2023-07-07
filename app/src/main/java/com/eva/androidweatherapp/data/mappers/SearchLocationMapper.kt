package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.common.SearchedLocationDto
import com.eva.androidweatherapp.domain.models.SearchedLocationModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun SearchedLocationDto.toModel(): SearchedLocationModel =
    SearchedLocationModel(
        country = country,
        time = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(epoch),
            ZoneId.systemDefault()
        ),
        name = name,
        region = region
    )