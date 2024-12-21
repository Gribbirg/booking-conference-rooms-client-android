package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api

import kotlinx.coroutines.delay
import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomDetails
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferencesOfDay
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureScope
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@BookingFeatureScope
internal class BookingFeatureApiMock @Inject constructor() : BookingFeatureApi {
    override suspend fun getConferenceRooms(): ApiResponse<List<ConferenceRoom>> {
        delay(2000)
        return ApiResponse.Success(mockRooms)
    }

    override suspend fun getConferenceRoomDetails(id: UUID): ApiResponse<ConferenceRoomDetails> {
        delay(2000)
        return mockDetails.firstOrNull { it.id == id }
            ?.let { ApiResponse.Success(it) }
            ?: ApiResponse.Error.NotFound
    }

    override suspend fun getConferencesByRoomAndDay(
        roomId: UUID,
        day: LocalDate
    ): ApiResponse<ConferencesOfDay> {
        delay(2000)
        return mockConferences[roomId]
            ?.firstOrNull { it.day == day }
            ?.let { ApiResponse.Success(it) } ?: ApiResponse.Error.NotFound
    }

    override suspend fun addConference(
        roomId: UUID,
        day: LocalDate,
        conference: Conference
    ): ApiResponse<Boolean> {
        delay(2000)
        return ApiResponse.Success(true)
    }
}

private val mockRooms: List<ConferenceRoom> = listOf(
    ConferenceRoom(
        id = UUID.fromString("c3de5059-b0e6-4d3d-af41-a408f0e57077"),
        name = "Room 1",
        floor = 1,
        maxPeople = 2,
        freeTo = LocalDateTime.now(),
    ),
)

private val mockDetails: List<ConferenceRoomDetails> = listOf(
    ConferenceRoomDetails(
        id = UUID.fromString("c3de5059-b0e6-4d3d-af41-a408f0e57077"),
        name = "Room 1",
        floor = 1,
        maxPeople = 2,
        author = "Alex"
    ),
)

private val mockConferences: Map<UUID, List<ConferencesOfDay>> = mapOf(
    UUID.fromString("c3de5059-b0e6-4d3d-af41-a408f0e57077") to listOf(
        ConferencesOfDay(
            day = LocalDate.now(),
            conferences = listOf(
                Conference(
                    id = UUID.fromString("c3de5059-b0e6-4d3d-af41-a408f0e57077"),
                    name = "Conf 1",
                    authorId = "0",
                    from = LocalDateTime.now(),
                    to = LocalDateTime.now(),
                ),
                Conference(
                    id = UUID.fromString("c3de5059-b0e6-4d3d-af41-a408f0e57078"),
                    name = "Conf 2",
                    authorId = "0",
                    from = LocalDateTime.now(),
                    to = LocalDateTime.now(),
                )
            ),
        )
    ),
)