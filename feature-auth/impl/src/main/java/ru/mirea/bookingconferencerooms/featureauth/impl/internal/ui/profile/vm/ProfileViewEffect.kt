package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewEffect

internal sealed interface ProfileViewEffect : BaseMviViewEffect {
    data object OpenAuth : ProfileViewEffect
    data object Close : ProfileViewEffect
}