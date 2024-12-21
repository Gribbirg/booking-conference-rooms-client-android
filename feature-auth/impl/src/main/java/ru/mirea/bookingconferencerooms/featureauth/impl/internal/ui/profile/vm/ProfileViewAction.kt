package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewAction

internal sealed interface ProfileViewAction : BaseMviViewAction {
    data object Logout : ProfileViewAction
}