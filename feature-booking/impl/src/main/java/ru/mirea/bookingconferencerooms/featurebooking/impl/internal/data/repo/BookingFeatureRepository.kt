package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo

import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomDetails
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferencesOfDay
import java.time.LocalDate
import java.util.UUID

internal interface BookingFeatureRepository {
    suspend fun getRooms(): ApiResponse<List<ConferenceRoom>>
    suspend fun getRoomDetails(id: UUID): ApiResponse<ConferenceRoomDetails>
    suspend fun getConferencesForRoomInDay(
        roomId: UUID,
        day: LocalDate,
    ): ApiResponse<ConferencesOfDay>
    suspend fun addConference(
        roomId: UUID,
        day: LocalDate,
        conference: Conference,
    ): ApiResponse<Boolean>
}