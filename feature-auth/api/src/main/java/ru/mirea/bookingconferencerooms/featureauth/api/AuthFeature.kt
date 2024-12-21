package ru.mirea.bookingconferencerooms.featureauth.api

import androidx.compose.runtime.Composable
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthNavActionHandler

interface AuthFeature {
    val authFlow: AuthFlow

    @Composable
    fun AuthScreen(
        onNavAction: AuthNavActionHandler,
    )

    @Composable
    fun ProfileScreen(
        onNavAction: AuthNavActionHandler,
    )
}