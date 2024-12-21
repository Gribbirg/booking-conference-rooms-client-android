package ru.mirea.bookingconferencerooms.coreapi.graphql

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String,
    val operationName: String? = null,
)
