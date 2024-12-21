package ru.mirea.bookingconferencerooms.corebooking.dto

import java.time.LocalDate

data class ConferencesOfDay(
    val day: LocalDate,
    val conferences: List<Conference>,
)
