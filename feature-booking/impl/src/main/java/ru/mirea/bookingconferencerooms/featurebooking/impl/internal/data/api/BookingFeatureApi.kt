package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api

import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceInput
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import java.util.UUID

internal interface BookingFeatureApi {

    suspend fun getConferenceRooms(): ApiResponse<List<ConferenceRoom>>

    suspend fun getConferenceRoom(
        roomId: UUID,
    ): ApiResponse<ConferenceRoom>

    suspend fun getConferencesByRoom(
        roomId: UUID,
    ): ApiResponse<List<Conference>>

    suspend fun addConference(
        conference: ConferenceInput,
    ): ApiResponse<Conference>
}