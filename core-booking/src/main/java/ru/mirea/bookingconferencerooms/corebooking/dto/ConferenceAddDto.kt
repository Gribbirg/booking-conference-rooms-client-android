package ru.mirea.bookingconferencerooms.corebooking.dto

import kotlinx.serialization.Serializable

@Serializable
class ConferenceAddDto(
    val addConference: Conference,
)