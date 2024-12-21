package ru.mirea.bookingconferencerooms.corebooking.dto

import kotlinx.serialization.Serializable

@Serializable
class ConferencesByRoomDto(
    val conferencesByRoom: List<Conference>,
)