package ru.mirea.bookingconferencerooms.corebooking.dto

import java.time.LocalDateTime
import java.util.UUID

data class Conference(
    val id: UUID,
    val name: String,
    val authorId: String,
    val from: LocalDateTime,
    val to: LocalDateTime,
)