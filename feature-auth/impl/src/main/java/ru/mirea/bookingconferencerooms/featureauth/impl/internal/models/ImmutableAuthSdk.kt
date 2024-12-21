package ru.mirea.bookingconferencerooms.featureauth.impl.internal.models

import androidx.compose.runtime.Immutable
import com.yandex.authsdk.YandexAuthSdk
import javax.inject.Inject

@Immutable
class ImmutableAuthSdk @Inject constructor(
    authSdk: YandexAuthSdk,
) : YandexAuthSdk by authSdk