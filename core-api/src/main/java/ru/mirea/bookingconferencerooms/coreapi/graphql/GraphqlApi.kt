package ru.mirea.bookingconferencerooms.coreapi.graphql

import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.http.HttpMethod
import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.coreapi.BaseApi
import ru.mirea.bookingconferencerooms.coreapi.getMainHttpClient

abstract class GraphqlApi(
    httpClient: HttpClient = getMainHttpClient(),
) : BaseApi(httpClient) {
    protected suspend inline fun <reified T> makeGraphqlRequest(
        query: String,
        operationName: String? = null
    ): ApiResponse<T> {
        return makeGraphqlRequest(GraphQLRequest(query, operationName))
    }

    protected suspend inline fun <reified T> makeGraphqlRequest(
        request: GraphQLRequest,
    ): ApiResponse<T> {
        val result = makeRequest<GraphQLResponse<T>> {
            method = HttpMethod.Post
            setBody(request)
        }

        return when {
            result is ApiResponse.Error -> result
            result is ApiResponse.Success
                    && (result.body.errors?.isEmpty() ?: true)
                    && result.body.data != null -> ApiResponse.Success(result.body.data)

            else -> ApiResponse.Error.UnknownError
        }
    }
}