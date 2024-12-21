package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm

import android.util.Log
import com.yandex.authsdk.YandexAuthLoginOptions
import com.yandex.authsdk.YandexAuthResult
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import ru.mirea.bookingconferencerooms.coremvi.BaseMviViewModel
import ru.mirea.bookingconferencerooms.coreutils.coroutins.collectIn
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.data.AuthRepository
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseMviViewModel<AuthViewState, AuthViewAction, AuthViewEffect>(
    initState = AuthViewState.Loading,
) {
    init {
        authRepository.authFlow.collectIn(scope, ::handleAuthStateChange)
        backgroundScope.launch {
            authRepository.init()
        }
    }

    override suspend fun handleAction(action: AuthViewAction) {
        when (action) {
            is AuthViewAction.HandleAuthResult -> handeAuthResult(action.result)
            AuthViewAction.OpenAuth -> openAuth()
        }
    }

    override fun onError(coroutineContext: CoroutineContext, throwable: Throwable) {
        Log.e(
            AuthViewModel::class.java.simpleName,
            "onError: ${throwable::class.java.simpleName}\n${throwable.message}\n${
                throwable.stackTrace.joinToString("\n")
            }"
        )
        updateState { AuthViewState.Error }
    }

    private suspend fun handeAuthResult(result: YandexAuthResult) {
        when (result) {
            is YandexAuthResult.Success -> {
                updateState { AuthViewState.Loading }
                backgroundScope.launch {
                    authRepository.saveUserId(result.token)
                }
            }

            YandexAuthResult.Cancelled -> Unit

            is YandexAuthResult.Failure -> {
                updateState { AuthViewState.Error }
            }
        }
    }

    private suspend fun openAuth() {
        val options = YandexAuthLoginOptions()
        emitEffect(AuthViewEffect.OpenAuth(options))
    }

    private suspend fun handleAuthStateChange(authState: AuthState) {
        when (authState) {
            is AuthState.Auth -> emitEffect(AuthViewEffect.Close)
            AuthState.NoAuth -> updateState { AuthViewState.Loaded }
            else -> Unit
        }
    }
}