package ru.mirea.bookingconferencerooms.featurebooking.api

import java.util.UUID

sealed interface BookingFeatureNavAction {
    data object Back : BookingFeatureNavAction
    data class Details(val id: UUID) : BookingFeatureNavAction
    data object Auth : BookingFeatureNavAction
    data object Profile : BookingFeatureNavAction
}