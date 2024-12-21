package ru.mirea.bookingconferencerooms.featureauth.impl.internal.data

import com.yandex.authsdk.YandexAuthSdk
import com.yandex.authsdk.YandexAuthToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.di.AuthFeatureScope
import javax.inject.Inject

@AuthFeatureScope
internal class AuthRepository @Inject constructor(
    private val userStore: UserStore,
    private val sdk: YandexAuthSdk,
    private val userApi: UserDataApi,
) {
    private val _authFlow = MutableStateFlow<AuthState>(AuthState.Unknown)
    val authFlow: AuthFlow get() = AuthFlow(_authFlow.asStateFlow())

    suspend fun init() {
        updateFlow()
    }

    suspend fun saveUserId(authToken: YandexAuthToken) {
        userStore.save(authToken.value, sdk.getJwt(authToken))
        updateFlow()
    }

    suspend fun logout() {
        userStore.remove()
        updateFlow()
    }

    private suspend fun updateFlow() {
        _authFlow.emit(userApi.getUserData()?.let { AuthState.Auth(it) } ?: AuthState.NoAuth)
    }
}