package com.eva.androidweatherapp.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun String.toDateTimeFormat(): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))

fun String.toIsoDateFormat(): LocalDate = LocalDate.parse(this, DateTimeFormatter.ISO_DATE)

fun LocalDateTime.toDateTimeFormat(): String = DateTimeFormatter
    .ofPattern("yyyy-MM-dd HH:mm")
    .format(this)

fun LocalDateTime.toHourAmOrPm(): String = DateTimeFormatter
    .ofPattern("hh a")
    .format(this)


fun LocalDateTime.toReadableDateWithWeekDay(): String = DateTimeFormatter
    .ofPattern("EEE, MMMM d")
    .format(this)

fun LocalDate.toDayMonthFormat(): String = DateTimeFormatter
    .ofPattern("dd MMMM")
    .format(this)

val LocalDate.weekDayShort: String
    get() = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)

