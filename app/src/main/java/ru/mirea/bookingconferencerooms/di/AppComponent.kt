package ru.mirea.bookingconferencerooms.di

import android.content.Context
import androidx.compose.runtime.Immutable
import dagger.BindsInstance
import dagger.Component
import ru.mirea.bookingconferencerooms.di.modules.AuthFeatureModule
import ru.mirea.bookingconferencerooms.di.modules.BookingFeatureModule
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeature
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeatureDependencies
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeature
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureDependencies

@Immutable
@Component(
    modules = [
        BookingFeatureModule::class,
        AuthFeatureModule::class,
    ],
)
@AppComponentScope
internal interface AppComponent :
    BookingFeatureDependencies,
    AuthFeatureDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): AppComponent
    }

    val bookingFeature: BookingFeature
    val authFeature: AuthFeature
}