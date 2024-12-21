package ru.mirea.bookingconferencerooms.corebooking.dto

import kotlinx.serialization.Serializable

@Serializable
class ConferenceRoomsDto(
    val conferenceRooms: List<ConferenceRoom>,
)