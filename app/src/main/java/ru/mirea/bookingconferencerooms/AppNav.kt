package ru.mirea.bookingconferencerooms

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.mirea.bookingconferencerooms.di.AppComponent
import java.util.UUID

@Composable
internal fun AppNav(
    navController: NavHostController,
    appComponent: AppComponent,
) {
    val navManager = NavManager(navController)

    NavHost(
        navController = navController,
        startDestination = Destinations.Auth.route,
        enterTransition = TransitionAnimations.secondScreenEnter(),
        exitTransition = TransitionAnimations.secondScreenExit(),
    ) {
        composable(
            Destinations.RoomsList.route,
            enterTransition = TransitionAnimations.mainScreenEnter(),
            exitTransition = TransitionAnimations.mainScreenExit(),
        ) {
            appComponent.bookingFeature.ListScreen(
                onNavAction = navManager::handleBookingFeatureNavigation,
            )
        }
        composable(
            Destinations.RoomDetails.route,
            arguments = Destinations.RoomDetails.arguments,
        ) { backStackEntry ->
            val id = backStackEntry.arguments
                ?.getString(Destinations.RoomDetails.arguments.first().name)
                ?.let { UUID.fromString(it) }
            if (id == null) {
                navController.navigate(Destinations.RoomsList.route)
            } else {
                appComponent.bookingFeature.DetailsScreen(
                    id = id,
                    onNavAction = navManager::handleBookingFeatureNavigation,
                )
            }
        }
        composable(
            Destinations.Auth.route,
        ) {
            appComponent.authFeature.AuthScreen(
                onNavAction = navManager::handleAuthFeatureNavigation,
            )
        }
        composable(
            Destinations.Profile.route,
        ) {
            appComponent.authFeature.ProfileScreen(
                onNavAction = navManager::handleAuthFeatureNavigation,
            )
        }
    }
}