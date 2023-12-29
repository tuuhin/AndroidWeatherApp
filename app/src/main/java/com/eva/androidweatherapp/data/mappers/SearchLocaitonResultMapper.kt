package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.results.SearchResultsDto
import com.eva.androidweatherapp.domain.models.LocationSearchResult

fun SearchResultsDto.toModel(): LocationSearchResult =
    LocationSearchResult(id = id, country = country, name = name, region = region)

fun List<SearchResultsDto>.toModels(): List<LocationSearchResult> = map { it.toModel() }