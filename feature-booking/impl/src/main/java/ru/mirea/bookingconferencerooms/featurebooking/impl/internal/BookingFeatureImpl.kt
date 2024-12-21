package ru.mirea.bookingconferencerooms.featurebooking.impl.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.mirea.bookingconferencerooms.coreui.vm.ViewModelProvider
import ru.mirea.bookingconferencerooms.coreui.vm.createViewModel
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.asImmutableFlow
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeature
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureScope
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.DetailsViewModel
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.compose.DetailsScreenComponent
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.BookingViewModel
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.compose.BookingFeatureScreen
import java.util.UUID
import javax.inject.Inject

@BookingFeatureScope
internal class BookingFeatureImpl @Inject constructor(
    private val viewModelProvider: ViewModelProvider<BookingViewModel>,
    private val detailsViewModelFactory: DetailsViewModel.Factory,
) : BookingFeature {
    @Composable
    override fun ListScreen(onNavAction: (BookingFeatureNavAction) -> Unit) {
        val viewModel = createViewModel {
            viewModelProvider.get()
        }

        val state by viewModel.uiState.collectAsState()

        BookingFeatureScreen(
            state = state,
            effectFlow = viewModel.uiEffect.asImmutableFlow(),
            onAction = viewModel::onAction,
            onNavAction = onNavAction
        )
    }

    @Composable
    override fun DetailsScreen(id: UUID, onNavAction: (BookingFeatureNavAction) -> Unit) {
        val viewModel = createViewModel {
            detailsViewModelFactory.create(id)
        }

        val state by viewModel.uiState.collectAsState()

        DetailsScreenComponent(
            state = state,
            effectFlow = viewModel.uiEffect.asImmutableFlow(),
            onAction = viewModel::onAction,
            onNavAction = onNavAction
        )
    }
}