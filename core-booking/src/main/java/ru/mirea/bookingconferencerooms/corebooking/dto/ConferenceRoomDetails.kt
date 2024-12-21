package ru.mirea.bookingconferencerooms.corebooking.dto

import java.util.UUID

data class ConferenceRoomDetails(
    val id: UUID,
    val name: String,
    val floor: Int,
    val maxPeople: Int,
    val author: String,
)