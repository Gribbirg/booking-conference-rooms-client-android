package ru.mirea.bookingconferencerooms.coreapi

import io.ktor.client.HttpClient
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.http.HttpHeaders
import io.ktor.serialization.ContentConvertException
import io.ktor.serialization.JsonConvertException
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import java.io.IOException


abstract class BaseApi {

    open suspend fun getAuthKey(): String? = null

    protected suspend inline fun <reified T> HttpClient.safeRequest(
        crossinline block: HttpRequestBuilder.() -> Unit,
    ): ApiResponse<T> = withContext(coroutineContext) {
        val authKey = getAuthKey()

        return@withContext try {
            val response = request {
                block()
                authKey?.let {
                    headers {
                        append(
                            HttpHeaders.Authorization,
                            "OAuth $it"
                        )
                    }
                }
            }
            ApiResponse.Success(response.body<T>())
        } catch (serverResponseException: ServerResponseException) {
            ApiResponse.Error.HttpError(
                serverResponseException.response.status.value,
                serverResponseException.response.body()
            )
        } catch (ioException: IOException) {
            ApiResponse.Error.NetworkError
        } catch (serializationException: SerializationException) {
            ApiResponse.Error.SerializationError
        } catch (noTransformationFoundException: NoTransformationFoundException) {
            ApiResponse.Error.SerializationError
        } catch (jsonConvertException: JsonConvertException) {
            ApiResponse.Error.SerializationError
        } catch (contentConvertException: ContentConvertException) {
            ApiResponse.Error.SerializationError
        } catch (e: Exception) {
            println(e)
            ApiResponse.Error.UnknownError
        }
    }
}