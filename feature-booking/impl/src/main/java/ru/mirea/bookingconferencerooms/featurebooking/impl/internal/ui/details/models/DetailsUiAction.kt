package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewAction
import java.time.LocalTime

internal sealed interface DetailsUiAction : BaseMviViewAction {
    data object BookRoom : DetailsUiAction
    data object DismissBookingDialog : DetailsUiAction
    data class ConfirmBooking(
        val name: String,
        val from: LocalTime,
        val to: LocalTime,
    ) : DetailsUiAction

    data object RetryBooking : DetailsUiAction
}