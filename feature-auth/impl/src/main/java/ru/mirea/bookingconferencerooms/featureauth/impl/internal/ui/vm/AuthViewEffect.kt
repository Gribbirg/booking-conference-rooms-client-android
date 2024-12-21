package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm

import com.yandex.authsdk.YandexAuthLoginOptions
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewEffect

internal sealed interface AuthViewEffect : BaseMviViewEffect {
    data class OpenAuth(val loginOptions: YandexAuthLoginOptions) : AuthViewEffect
    data object Close : AuthViewEffect
}