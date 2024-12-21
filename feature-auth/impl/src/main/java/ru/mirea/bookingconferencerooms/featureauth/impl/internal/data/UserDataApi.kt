package ru.mirea.bookingconferencerooms.featureauth.impl.internal.data

import io.ktor.client.request.parameter
import io.ktor.client.request.url
import ru.mirea.bookingconferencerooms.coreapi.BaseApi
import ru.mirea.bookingconferencerooms.featureauth.api.models.User
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.di.AuthFeatureScope
import javax.inject.Inject

@AuthFeatureScope
internal class UserDataApi @Inject constructor(
    private val userStore: UserStore,
) : BaseApi() {
    override suspend fun getAuthKey(): String? {
        return userStore.get()
    }

    suspend fun getUserData(): User? {
        val jwt = userStore.getJwt()
        return makeRequest<User> {
            url(URL_AUTH)
            parameter(PARAM_FORMAT, FORMAT_JSON)
            parameter(PARAM_JWT, jwt)
        }.getOrNull()
    }

    private companion object {
        const val URL_AUTH = "https://login.yandex.ru/info"

        const val PARAM_FORMAT = "format"
        const val PARAM_JWT = "jwt_secret"

        const val FORMAT_JSON = "json"
    }
}