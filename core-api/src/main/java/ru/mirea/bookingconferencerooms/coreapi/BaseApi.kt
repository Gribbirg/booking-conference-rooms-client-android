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
import ru.mirea.bookingconferencerooms.coreutils.logError
import java.io.IOException


abstract class BaseApi(
    val httpClient: HttpClient = getMainHttpClient(),
) {
    open suspend fun getAuthKey(): String? = null

    open suspend fun baseRequest(builder: HttpRequestBuilder): Unit = Unit

    protected suspend inline fun <reified T> makeRequest(
        crossinline block: HttpRequestBuilder.() -> Unit,
    ): ApiResponse<T> = withContext(httpClient.coroutineContext) {
        val authKey = getAuthKey()

        return@withContext try {
            val response = httpClient.request {
                authKey?.let {
                    headers {
                        append(
                            HttpHeaders.Authorization,
                            "OAuth $it"
                        )
                    }
                }
                baseRequest(this)
                block()
            }
            ApiResponse.Success(response.body<T>())
        } catch (serverResponseException: ServerResponseException) {
            logError(serverResponseException)
            ApiResponse.Error.HttpError(
                serverResponseException.response.status.value,
                serverResponseException.response.body()
            )
        } catch (ioException: IOException) {
            logError(ioException)
            ApiResponse.Error.NetworkError
        } catch (serializationException: SerializationException) {
            logError(serializationException)
            ApiResponse.Error.SerializationError
        } catch (noTransformationFoundException: NoTransformationFoundException) {
            logError(noTransformationFoundException)
            ApiResponse.Error.SerializationError
        } catch (jsonConvertException: JsonConvertException) {
            logError(jsonConvertException)
            ApiResponse.Error.SerializationError
        } catch (contentConvertException: ContentConvertException) {
            logError(contentConvertException)
            ApiResponse.Error.SerializationError
        } catch (e: Exception) {
            logError(e)
            ApiResponse.Error.UnknownError
        }
    }
}