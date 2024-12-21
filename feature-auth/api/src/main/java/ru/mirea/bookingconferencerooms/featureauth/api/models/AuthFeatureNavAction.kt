package ru.mirea.bookingconferencerooms.featureauth.api.models

import androidx.compose.runtime.Immutable

sealed interface AuthFeatureNavAction {
    data object Back : AuthFeatureNavAction
    data object OpenMain : AuthFeatureNavAction
    data object OpenAuth : AuthFeatureNavAction
}

@Immutable
fun interface AuthNavActionHandler {
    fun handle(action: AuthFeatureNavAction)
}