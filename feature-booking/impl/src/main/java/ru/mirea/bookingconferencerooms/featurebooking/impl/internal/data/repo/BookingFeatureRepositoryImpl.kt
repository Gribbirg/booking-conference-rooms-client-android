package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo

import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomDetails
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferencesOfDay
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api.BookingFeatureApi
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureScope
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

@BookingFeatureScope
internal class BookingFeatureRepositoryImpl @Inject constructor(
    private val api: BookingFeatureApi,
) : BookingFeatureRepository {
    override suspend fun getRooms(): ApiResponse<List<ConferenceRoom>> {
        return api.getConferenceRooms()
    }

    override suspend fun getRoomDetails(id: UUID): ApiResponse<ConferenceRoomDetails> {
        return api.getConferenceRoomDetails(id)
    }

    override suspend fun getConferencesForRoomInDay(
        roomId: UUID,
        day: LocalDate
    ): ApiResponse<ConferencesOfDay> {
        return api.getConferencesByRoomAndDay(roomId, day)
    }

    override suspend fun addConference(
        roomId: UUID,
        day: LocalDate,
        conference: Conference
    ): ApiResponse<Boolean> {
        return api.addConference(roomId, day, conference)
    }
}