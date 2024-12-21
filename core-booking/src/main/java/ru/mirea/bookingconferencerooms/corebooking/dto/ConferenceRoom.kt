package ru.mirea.bookingconferencerooms.corebooking.dto

import kotlinx.serialization.Serializable
import ru.gribbirg.bookingconferencerooms.coreserialization.UUIDSerializer
import java.util.UUID

@Serializable
data class ConferenceRoom(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val floor: Int,
    val maxPeople: Int,
)
