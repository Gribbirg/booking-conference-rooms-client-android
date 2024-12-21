package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.host
import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.coreapi.graphql.GraphqlApi
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceAddDto
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceInput
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomDto
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoomsDto
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferencesByRoomDto
import ru.mirea.bookingconferencerooms.coreutils.formatToString
import ru.mirea.bookingconferencerooms.featurebooking.impl.BuildConfig
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureScope
import java.util.UUID
import javax.inject.Inject

@BookingFeatureScope
internal class BookingFeatureApiImpl @Inject constructor() : GraphqlApi(), BookingFeatureApi {
    override suspend fun baseRequest(builder: HttpRequestBuilder) {
        builder.apply {
            host = BuildConfig.HOST_URL
        }
    }

    override suspend fun getConferenceRooms(): ApiResponse<List<ConferenceRoom>> {
        return makeGraphqlRequest<ConferenceRoomsDto>(
            """
                {
                  conferenceRooms {
                    id
                    name
                    floor
                    maxPeople
                  }
                }
            """.trimIndent()
        ).mapTo { conferenceRooms }
    }

    override suspend fun getConferenceRoom(roomId: UUID): ApiResponse<ConferenceRoom> {
        return makeGraphqlRequest<ConferenceRoomDto>(
            """
                {
                  conferenceRoom(roomId: "$roomId") {
                    id
                    name
                    floor
                    maxPeople
                  }
                }
            """.trimIndent()
        ).mapTo { conferenceRoom }
    }

    override suspend fun getConferencesByRoom(
        roomId: UUID,
    ): ApiResponse<List<Conference>> {
        return makeGraphqlRequest<ConferencesByRoomDto>(
            """
                {
                  conferencesByRoom(roomId: "$roomId") {
                  	id
                  	name
                    authorId
                  	startTime
                    endTime
                    room {
                      id
                      name
                      floor
                      maxPeople
                    }
                	}
                }
            """.trimIndent()
        ).mapTo { conferencesByRoom }
    }

    override suspend fun addConference(
        conference: ConferenceInput,
    ): ApiResponse<Conference> {
        return makeGraphqlRequest<ConferenceAddDto>(
            """
                mutation {
                	addConference(
                  	conference: {
                      name: "${conference.name}",
                      roomId: "${conference.roomId}",
                      startTime: "${conference.startTime.formatToString()}",
                      endTime: "${conference.endTime.formatToString()}",
                      authorId: "${conference.authorId}",
                    }
                  ) {
                    id
                    name
                    startTime
                    endTime
                    authorId
                    room {
                      id
                      name
                      floor
                      maxPeople
                    }
                  }
                }
            """.trimIndent()
        ).mapTo { addConference }
    }
}