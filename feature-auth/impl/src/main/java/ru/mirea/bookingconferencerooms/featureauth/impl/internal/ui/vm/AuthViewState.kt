package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewState

internal sealed interface AuthViewState : BaseMviViewState {
    data object Loading : AuthViewState
    data object Loaded : AuthViewState
    data object Error : AuthViewState
}