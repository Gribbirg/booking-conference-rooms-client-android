package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewEffect

internal sealed interface DetailsUiEffect : BaseMviViewEffect {
    data object ToAuth : DetailsUiEffect
}