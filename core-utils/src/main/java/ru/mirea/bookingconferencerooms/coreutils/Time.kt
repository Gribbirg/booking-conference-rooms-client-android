package ru.mirea.bookingconferencerooms.coreutils

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun OffsetDateTime.formatToString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx")
    return format(formatter)
}