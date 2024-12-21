package ru.mirea.bookingconferencerooms.featurebooking.api

import androidx.compose.runtime.Composable
import java.util.UUID

interface BookingFeature {
    @Composable
    fun ListScreen(onNavAction: (BookingFeatureNavAction) -> Unit)

    @Composable
    fun DetailsScreen(id: UUID, onNavAction: (BookingFeatureNavAction) -> Unit)
}