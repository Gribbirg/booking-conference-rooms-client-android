package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models

import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewState

internal sealed interface BookingFeatureState : BaseMviViewState {
    data object Loading : BookingFeatureState
    data object Error : BookingFeatureState
    data class Loaded(
        val rooms: List<ConferenceRoom>,
        val userImageUrl: String,
    ) : BookingFeatureState
}