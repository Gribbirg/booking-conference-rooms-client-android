package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.ImmutableSharedFlow
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiEffect

@Composable
internal fun EffectHandler(
    effectFlow: ImmutableSharedFlow<DetailsUiEffect>,
    onNavAction: (BookingFeatureNavAction) -> Unit,
) {
    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                DetailsUiEffect.ToAuth -> onNavAction(BookingFeatureNavAction.Auth)
            }
        }
    }
}
