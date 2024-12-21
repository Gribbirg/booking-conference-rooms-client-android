package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewState
import ru.mirea.bookingconferencerooms.featureauth.api.models.User

internal sealed interface ProfileViewState : BaseMviViewState {
    data object Loading : ProfileViewState
    data class Loaded(
        val user: User,
    ) : ProfileViewState
    data object Error : ProfileViewState
}