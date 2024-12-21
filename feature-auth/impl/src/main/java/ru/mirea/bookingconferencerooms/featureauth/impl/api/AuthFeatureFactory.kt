package ru.mirea.bookingconferencerooms.featureauth.impl.api

import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeature
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeatureDependencies
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.di.AuthFeatureComponent

object AuthFeatureFactory {
    fun create(deps: AuthFeatureDependencies): AuthFeature {
        return AuthFeatureComponent.create(deps).impl
    }
}