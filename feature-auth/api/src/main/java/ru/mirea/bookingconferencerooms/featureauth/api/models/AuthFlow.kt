package ru.mirea.bookingconferencerooms.featureauth.api.models

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.StateFlow

@Immutable
class AuthFlow(
    flow: StateFlow<AuthState>
) : StateFlow<AuthState> by flow