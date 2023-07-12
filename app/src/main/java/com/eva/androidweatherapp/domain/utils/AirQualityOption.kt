package com.eva.androidweatherapp.domain.utils


enum class AirQualityOption(val option: String) {
    GOOD("Good"),
    MODERATE("Moderate"),
    UNHEALTHY_SENSITIVE("Unhealthy For Sensitive"),
    UNHEALTHY("Unhealthy"),
    VERY_UNHEALTHY("Very Unhealthy"),
    HAZARDOUS("Hazardous");

    companion object {
        fun fromNumber(number: Int): AirQualityOption = when (number) {
            1 -> GOOD
            2 -> MODERATE
            3 -> UNHEALTHY
            4 -> UNHEALTHY_SENSITIVE
            5 -> VERY_UNHEALTHY
            else -> HAZARDOUS
        }
    }
}