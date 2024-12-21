package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo

import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceInput
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import java.util.UUID

internal interface BookingFeatureRepository {
    suspend fun getRooms(): ApiResponse<List<ConferenceRoom>>
    suspend fun getRoomDetails(id: UUID): ApiResponse<ConferenceRoom>
    suspend fun getConferencesForRoom(roomId: UUID): ApiResponse<List<Conference>>
    suspend fun addConference(conference: ConferenceInput): ApiResponse<Conference>
}