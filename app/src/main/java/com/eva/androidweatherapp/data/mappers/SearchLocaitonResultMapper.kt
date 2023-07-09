package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.results.SearchResultsDto
import com.eva.androidweatherapp.domain.models.SearchLocationResult

fun SearchResultsDto.toModel(): SearchLocationResult =
    SearchLocationResult(id = id, country = country, name = name, region = region)

fun List<SearchResultsDto>.toModels(): List<SearchLocationResult> = map { it.toModel() }