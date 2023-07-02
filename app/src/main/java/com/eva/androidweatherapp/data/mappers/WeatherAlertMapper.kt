package com.eva.androidweatherapp.data.mappers

import com.eva.androidweatherapp.data.remote.dto.common.WeatherAlertsDto
import com.eva.androidweatherapp.domain.models.WeatherAlertModel

fun WeatherAlertsDto.toModel(): WeatherAlertModel = WeatherAlertModel(
    areas = areas,
    headline = headline,
    instruction = instruction,
    note = note
)