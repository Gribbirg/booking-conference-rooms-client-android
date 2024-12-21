package ru.mirea.bookingconferencerooms.coreapi.graphql

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse<T>(
    val data: T? = null,
    val errors: List<GraphQLError>? = null
)

@Serializable
data class GraphQLError(
    val message: String,
    val locations: List<GraphQLErrorLocation>?,
    val path: List<String>?,
)

@Serializable
data class GraphQLErrorLocation(
    val line: Int,
    val column: Int,
)
