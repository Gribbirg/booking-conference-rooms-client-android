package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models

import androidx.compose.runtime.Immutable
import ru.mirea.bookingconferencerooms.corebooking.dto.Conference
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceRoom
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewState
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset

internal sealed interface DetailsUiState : BaseMviViewState {
    data object Loading : DetailsUiState
    data object Error : DetailsUiState
    data class Loaded(
        val room: ConferenceRoom,
        val conferencesOfDayState: ConferencesOfDayState = ConferencesOfDayState(),
        val userImageUrl: String,
        val bookingDialogState: BookingDialogState,
    ) : DetailsUiState
}

@Immutable
internal data class ConferencesOfDayState(
    val day: LocalDate = LocalDate.now(),
    val listState: ConferencesListState = ConferencesListState.Loading,
)

@Immutable
internal sealed interface ConferencesListState {
    data object Loading : ConferencesListState
    data object Error : ConferencesListState
    data class Loaded(
        val conferences: List<Conference>,
    ) : ConferencesListState
}

@Immutable
internal sealed interface BookingDialogState {
    data object Closed : BookingDialogState
    data object Loading : BookingDialogState
    data class Opened(
        val name: String,
        val from: OffsetDateTime,
        val to: OffsetDateTime,
    ) : BookingDialogState {
        companion object {
            val default: Opened
                get() = Opened(
                    name = "",
                    from = OffsetDateTime.now(ZoneOffset.systemDefault()),
                    to = OffsetDateTime.now(ZoneOffset.systemDefault()).plusHours(1),
                )
        }
    }

    data object BusyTimeSelected : BookingDialogState
}