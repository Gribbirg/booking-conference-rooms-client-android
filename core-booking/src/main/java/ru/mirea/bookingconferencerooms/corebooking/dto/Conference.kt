package ru.mirea.bookingconferencerooms.corebooking.dto

import kotlinx.serialization.Serializable
import ru.gribbirg.bookingconferencerooms.coreserialization.OffsetDateTimeSerializer
import ru.gribbirg.bookingconferencerooms.coreserialization.UUIDSerializer
import java.time.OffsetDateTime
import java.util.UUID

@Serializable
data class Conference(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val authorId: String,
    @Serializable(with = OffsetDateTimeSerializer::class)
    val startTime: OffsetDateTime,
    @Serializable(with = OffsetDateTimeSerializer::class)
    val endTime: OffsetDateTime,
    val room: ConferenceRoom,
)