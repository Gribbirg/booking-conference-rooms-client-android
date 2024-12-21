package ru.mirea.bookingconferencerooms.featureauth.impl.internal.di

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import ru.mirea.bookingconferencerooms.coreapi.getMainHttpClient

@Module
internal interface ApiModule {
    companion object {
        @Provides
        @AuthFeatureScope
        fun provideHttpEngine(): HttpClientEngine {
            return Android.create()
        }

        @Provides
        @AuthFeatureScope
        fun provideHttpClient(engine: HttpClientEngine): HttpClient {
            return getMainHttpClient(engine)
        }
    }
}