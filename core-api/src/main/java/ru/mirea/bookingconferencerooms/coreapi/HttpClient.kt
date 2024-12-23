package ru.mirea.bookingconferencerooms.coreapi

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun getMainHttpClient(engine: HttpClientEngine = Android.create()) = HttpClient(engine) {
    expectSuccess = true
    followRedirects = false

    install(ContentNegotiation) {
        json(json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
            useAlternativeNames = true
        })
    }

    install(HttpRequestRetry) {
        exponentialDelay(
            base = 1.1,
            maxDelayMs = 1000,
        )
        retryIf(3) { _, response ->
            response.status.let { !it.isSuccess() && it.value !in 400..499 }
        }
    }

    install(Logging) {
        level = LogLevel.ALL
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("internet", "Ktor: $message")
            }
        }
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("internet", "HTTP status: ${response.status.value}")
        }
    }

    install(DefaultRequest) {
        contentType(ContentType.Application.Json)
    }
}