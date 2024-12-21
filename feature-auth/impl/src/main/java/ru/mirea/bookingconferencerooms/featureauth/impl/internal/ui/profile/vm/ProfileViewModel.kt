package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm

import android.util.Log
import ru.mirea.bookingconferencerooms.coremvi.BaseMviViewModel
import ru.mirea.bookingconferencerooms.coreutils.coroutins.collectIn
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.data.AuthRepository
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewState
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseMviViewModel<ProfileViewState, ProfileViewAction, ProfileViewEffect>(
    initState = ProfileViewState.Loading,
) {

    init {
        authRepository.authFlow.collectIn(scope, ::onAuthStateChange)
    }

    override suspend fun handleAction(action: ProfileViewAction) {
        when (action) {
            ProfileViewAction.Logout -> {
                authRepository.logout()
                emitEffect(ProfileViewEffect.OpenAuth)
            }
        }
    }

    override fun onError(coroutineContext: CoroutineContext, throwable: Throwable) {
        Log.e(
            ProfileViewModel::class.java.simpleName,
            "onError: ${throwable::class.java.simpleName}\n${throwable.message}\n${
                throwable.stackTrace.joinToString("\n")
            }"
        )
        updateState { ProfileViewState.Error }
    }

    private suspend fun onAuthStateChange(authState: AuthState) {
        when (authState) {
            is AuthState.Auth -> updateState { ProfileViewState.Loaded(user = authState.user) }
            AuthState.NoAuth -> emitEffect(ProfileViewEffect.OpenAuth)
            else -> Unit
        }
    }
}