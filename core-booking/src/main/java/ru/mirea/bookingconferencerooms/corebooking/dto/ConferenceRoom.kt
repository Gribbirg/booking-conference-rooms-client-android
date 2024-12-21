package ru.mirea.bookingconferencerooms.corebooking.dto

import java.time.LocalDateTime
import java.util.UUID

data class ConferenceRoom(
    val id: UUID,
    val name: String,
    val floor: Int,
    val maxPeople: Int,
    val freeTo: LocalDateTime
)
