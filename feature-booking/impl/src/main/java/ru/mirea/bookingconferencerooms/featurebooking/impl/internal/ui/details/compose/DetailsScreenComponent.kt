package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.mirea.bookingconferencerooms.coreui.components.ErrorScreen
import ru.mirea.bookingconferencerooms.coreui.components.LoadingScreen
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.ImmutableSharedFlow
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.R
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.components.BookingScaffold
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiEffect
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiState

@Composable
internal fun DetailsScreenComponent(
    state: DetailsUiState,
    effectFlow: ImmutableSharedFlow<DetailsUiEffect>,
    onAction: (DetailsUiAction) -> Unit,
    onNavAction: (BookingFeatureNavAction) -> Unit,
) {
    EffectHandler(
        effectFlow = effectFlow,
        onNavAction = onNavAction,
    )

    when (state) {
        is DetailsUiState.Loaded -> {
            BookingScaffold(
                title = state.room.name,
                onBack = { onNavAction(BookingFeatureNavAction.Back) },
                toProfile = { onNavAction(BookingFeatureNavAction.Profile) },
                imageUrl = state.userImageUrl,
            ) { innerPadding ->
                RoomDetailsComponent(
                    roomDetails = state.room,
                    conferencesOfDayState = state.conferencesOfDayState,
                    bookRoom = { onAction(DetailsUiAction.BookRoom) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                )
                BookingDialog(
                    state = state.bookingDialogState,
                    onDismiss = { onAction(DetailsUiAction.DismissBookingDialog) },
                    onConfirm = { name, from, to ->
                        onAction(
                            DetailsUiAction.ConfirmBooking(
                                name = name,
                                from = from,
                                to = to,
                            )
                        )
                    },
                    onRetry = { onAction(DetailsUiAction.RetryBooking) },
                )
            }
        }

        is DetailsUiState.Loading -> {
            BookingScaffold(
                title = "",
                onBack = { onNavAction(BookingFeatureNavAction.Back) },
            ) { innerPadding ->
                LoadingScreen(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
        }

        is DetailsUiState.Error -> {
            BookingScaffold(
                title = stringResource(R.string.error),
                onBack = { onNavAction(BookingFeatureNavAction.Back) },
            ) { innerPadding ->
                ErrorScreen(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
        }
    }
}