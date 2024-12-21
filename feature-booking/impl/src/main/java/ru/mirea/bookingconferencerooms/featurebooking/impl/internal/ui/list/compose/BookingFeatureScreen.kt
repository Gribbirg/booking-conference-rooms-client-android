package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.mirea.bookingconferencerooms.coreui.components.ErrorScreen
import ru.mirea.bookingconferencerooms.coreui.components.LoadingScreen
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.ImmutableSharedFlow
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.R
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.components.BookingScaffold
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models.BookingFeatureAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models.BookingFeatureEffect
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models.BookingFeatureState

@Composable
internal fun BookingFeatureScreen(
    state: BookingFeatureState,
    onAction: (BookingFeatureAction) -> Unit,
    onNavAction: (BookingFeatureNavAction) -> Unit,
    effectFlow: ImmutableSharedFlow<BookingFeatureEffect>,
) {
    LaunchedEffect(Unit) {
        effectFlow.collect {
            when (it) {
                BookingFeatureEffect.ToAuth -> onNavAction(BookingFeatureNavAction.Auth)
            }
        }
    }

    when (state) {
        is BookingFeatureState.Loaded -> {
            BookingScaffold(
                title = stringResource(R.string.list_top_app_bar_content),
                imageUrl = state.userImageUrl,
                toProfile = { onNavAction(BookingFeatureNavAction.Profile) },
            ) { innerPadding ->
                RoomsList(
                    rooms = state.rooms,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    onItemClicked = { id -> onNavAction(BookingFeatureNavAction.Details(id)) },
                )
            }
        }

        is BookingFeatureState.Loading -> {
            BookingScaffold { innerPadding ->
                LoadingScreen(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                )
            }
        }

        is BookingFeatureState.Error -> {
            BookingScaffold(
                title = stringResource(R.string.error)
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