package ru.mirea.bookingconferencerooms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import ru.mirea.bookingconferencerooms.coreui.theme.BookingConferenceRoomsTheme
import ru.mirea.bookingconferencerooms.di.DaggerAppComponent

class MainActivity : ComponentActivity() {

    private val appComponent by lazy {
        DaggerAppComponent.factory().create(
            context = applicationContext
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookingConferenceRoomsTheme {
                val navController = rememberNavController()
                AppNav(
                    navController = navController,
                    appComponent = appComponent,
                )
            }
        }
    }
}