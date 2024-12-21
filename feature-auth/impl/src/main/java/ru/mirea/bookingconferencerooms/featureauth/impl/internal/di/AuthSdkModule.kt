package ru.mirea.bookingconferencerooms.featureauth.impl.internal.di

import android.content.Context
import com.yandex.authsdk.YandexAuthOptions
import com.yandex.authsdk.YandexAuthSdk
import dagger.Module
import dagger.Provides

@Module
internal interface AuthSdkModule {
    companion object {
        @Provides
        @AuthFeatureScope
        fun provideAuthOptions(context: Context): YandexAuthOptions {
            return YandexAuthOptions(context)
        }

        @Provides
        @AuthFeatureScope
        fun provideSdk(authOptions: YandexAuthOptions): YandexAuthSdk {
            return YandexAuthSdk.create(authOptions)
        }
    }
}