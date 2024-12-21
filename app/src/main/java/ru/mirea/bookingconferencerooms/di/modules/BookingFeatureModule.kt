package ru.mirea.bookingconferencerooms.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.mirea.bookingconferencerooms.di.AppComponent
import ru.mirea.bookingconferencerooms.di.AppComponentScope
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeature
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureDependencies
import ru.mirea.bookingconferencerooms.featurebooking.impl.api.BookingFeatureFactory

@Module
internal interface BookingFeatureModule {
    @Binds
    fun bindBookingFeatureDependencies(deps: AppComponent): BookingFeatureDependencies

    companion object {
        @AppComponentScope
        @Provides
        fun provideBookingFeature(deps: BookingFeatureDependencies): BookingFeature =
            BookingFeatureFactory.create(deps)
    }
}