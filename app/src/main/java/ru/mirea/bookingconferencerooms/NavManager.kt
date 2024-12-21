package ru.mirea.bookingconferencerooms

import androidx.navigation.NavController
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureNavAction

internal class NavManager(
    private val navController: NavController,
) {
    fun handleBookingFeatureNavigation(navAction: BookingFeatureNavAction) {
        when (navAction) {
            is BookingFeatureNavAction.Back -> navController.popBackStack()
            is BookingFeatureNavAction.Details -> navController.navigate(
                Destinations.RoomDetails.getRoute(
                    navAction.id
                )
            )

            BookingFeatureNavAction.Auth -> navController.navigate(Destinations.Auth.route)
            BookingFeatureNavAction.Profile -> navController.navigate(Destinations.Profile.route)
        }
    }

    fun handleAuthFeatureNavigation(navAction: AuthFeatureNavAction) {
        when (navAction) {
            is AuthFeatureNavAction.Back -> navController.popBackStack()
            AuthFeatureNavAction.OpenAuth -> {
                navController.popBackStack()
                navController.navigate(Destinations.Auth.route)
            }

            AuthFeatureNavAction.OpenMain -> navController.navigate(Destinations.RoomsList.route)
        }
    }
}