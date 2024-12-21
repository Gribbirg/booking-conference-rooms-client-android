package ru.mirea.bookingconferencerooms.featureauth.api.models

sealed interface AuthState {
    data object Unknown : AuthState
    data object NoAuth : AuthState
    data class Auth(val user: User) : AuthState
}