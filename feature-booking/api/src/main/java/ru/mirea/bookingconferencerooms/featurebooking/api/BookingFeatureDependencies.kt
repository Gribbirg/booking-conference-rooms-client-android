package ru.mirea.bookingconferencerooms.featurebooking.api

import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow

interface BookingFeatureDependencies {
    val authFlow: AuthFlow
}