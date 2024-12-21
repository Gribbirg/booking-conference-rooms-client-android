package ru.mirea.bookingconferencerooms

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument
import java.util.UUID

internal sealed class Destinations(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {
    data object Launch : Destinations("launch")
    data object Auth : Destinations("auth")
    data object RoomsList : Destinations("rooms")
    data object RoomDetails : Destinations(
        "roomdetails?id={id}",
        listOf(navArgument("id") { }),
    ) {
        fun getRoute(id: UUID) = "roomdetails?id=${id}"
    }
    data object Profile : Destinations("profile")
}