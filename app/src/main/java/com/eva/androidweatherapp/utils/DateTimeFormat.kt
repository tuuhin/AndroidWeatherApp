package com.eva.androidweatherapp.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toDateTimeFormat(): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

fun LocalDateTime.toDateTimeFormat(): String = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    .format(this)

fun String.toIsoDateFormat(): LocalDate = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)

