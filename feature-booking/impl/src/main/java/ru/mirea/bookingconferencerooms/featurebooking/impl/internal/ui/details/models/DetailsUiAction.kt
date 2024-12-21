package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models

import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewAction
import java.time.OffsetDateTime

internal sealed interface DetailsUiAction : BaseMviViewAction {
    data object BookRoom : DetailsUiAction

    data object DismissBookingDialog : DetailsUiAction

    data class ConfirmBooking(
        val name: String,
        val from: OffsetDateTime,
        val to: OffsetDateTime,
    ) : DetailsUiAction

    data object RetryBooking : DetailsUiAction
}