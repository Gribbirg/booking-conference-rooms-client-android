package ru.mirea.bookingconferencerooms.featurebooking.impl.api

import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeature
import ru.mirea.bookingconferencerooms.featurebooking.api.BookingFeatureDependencies
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.BookingFeatureComponent

object BookingFeatureFactory {
    fun create(deps: BookingFeatureDependencies): BookingFeature =
        BookingFeatureComponent.create(deps).impl
}