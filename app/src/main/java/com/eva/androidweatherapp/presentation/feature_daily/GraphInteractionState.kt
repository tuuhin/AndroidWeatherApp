package com.eva.androidweatherapp.presentation.feature_daily

data class GraphInteractionState(
    val isDropDownOpen: Boolean = false,
    val graph: WeatherGraphType = WeatherGraphType.AVG_TEMPERATURE
)


sealed interface GraphInteractionEvents {
    object OnToggleDropDown : GraphInteractionEvents
    data class OngraphSelect(val type: WeatherGraphType) : GraphInteractionEvents
}