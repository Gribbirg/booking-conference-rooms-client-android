package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm

import com.yandex.authsdk.YandexAuthResult
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewAction

internal sealed interface AuthViewAction : BaseMviViewAction {
    data object OpenAuth : AuthViewAction
    data class HandleAuthResult(val result: YandexAuthResult) : AuthViewAction
}