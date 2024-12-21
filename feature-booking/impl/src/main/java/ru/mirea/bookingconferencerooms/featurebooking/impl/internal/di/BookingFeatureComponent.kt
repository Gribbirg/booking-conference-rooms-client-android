package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di

import dagger.Component
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureDependencies
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.BookingFeatureImpl
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.modules.DataModule
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.modules.ViewModelModule

@Component(
    dependencies = [BookingFeatureDependencies::class],
    modules = [
        ViewModelModule::class,
        DataModule::class,
    ],
)
@BookingFeatureScope
internal interface BookingFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(deps: BookingFeatureDependencies): BookingFeatureComponent
    }

    val impl: BookingFeatureImpl

    companion object {
        fun create(deps: BookingFeatureDependencies): BookingFeatureComponent =
            DaggerBookingFeatureComponent.factory().create(deps)
    }
}