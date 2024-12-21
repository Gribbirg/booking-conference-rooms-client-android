package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo

import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceInput
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api.BookingFeatureApi
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureScope
import java.util.UUID
import javax.inject.Inject

@BookingFeatureScope
internal class BookingFeatureRepositoryImpl @Inject constructor(
    private val api: BookingFeatureApi,
) : BookingFeatureRepository {

    override suspend fun getRooms(): ApiResponse<List<ConferenceRoom>> {
        return api.getConferenceRooms()
    }

    override suspend fun getRoomDetails(id: UUID): ApiResponse<ConferenceRoom> {
        return api.getConferenceRoom(id)
    }

    override suspend fun getConferencesForRoom(roomId: UUID): ApiResponse<List<Conference>> {
        return api.getConferencesByRoom(roomId)
    }

    override suspend fun addConference(conference: ConferenceInput): ApiResponse<Conference> {
        return api.addConference(conference)
    }
}