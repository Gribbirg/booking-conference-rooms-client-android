package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewEffect

internal sealed interface BookingFeatureEffect : BaseMviViewEffect {
    data object ToAuth : BookingFeatureEffect
}