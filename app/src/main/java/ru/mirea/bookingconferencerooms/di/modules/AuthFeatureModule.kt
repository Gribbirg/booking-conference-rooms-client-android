package ru.mirea.bookingconferencerooms.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.mirea.bookingconferencerooms.di.AppComponent
import ru.mirea.bookingconferencerooms.di.AppComponentScope
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeature
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeatureDependencies
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
import ru.mirea.bookingconferencerooms.featureauth.impl.api.AuthFeatureFactory

@Module
internal interface AuthFeatureModule {
    @Binds
    fun bindAuthFeatureDependencies(deps: AppComponent): AuthFeatureDependencies

    companion object {
        @AppComponentScope
        @Provides
        fun provideAuthFeature(deps: AuthFeatureDependencies): AuthFeature {
            return AuthFeatureFactory.create(deps)
        }

        @AppComponentScope
        @Provides
        fun provideAuthFlow(feature: AuthFeature): AuthFlow {
            return feature.authFlow
        }
    }
}