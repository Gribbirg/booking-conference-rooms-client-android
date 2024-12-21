package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api

import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomDetails
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferencesOfDay
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureScope
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

@BookingFeatureScope
internal class BookingFeatureApiImpl @Inject constructor() : BookingFeatureApi {
    override suspend fun getConferenceRooms(): ApiResponse<List<ConferenceRoom>> {
        TODO("Not yet implemented")
    }

    override suspend fun getConferenceRoomDetails(id: UUID): ApiResponse<ConferenceRoomDetails> {
        TODO("Not yet implemented")
    }

    override suspend fun getConferencesByRoomAndDay(
        roomId: UUID,
        day: LocalDate
    ): ApiResponse<ConferencesOfDay> {
        TODO("Not yet implemented")
    }

    override suspend fun addConference(
        roomId: UUID,
        day: LocalDate,
        conference: Conference
    ): ApiResponse<Boolean> {
        TODO("Not yet implemented")
    }

}