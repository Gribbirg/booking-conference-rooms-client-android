package ru.mirea.bookingconferencerooms.featureauth.impl.internal.di

import dagger.Component
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeatureDependencies
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.AuthFeatureImpl

@Component(
    dependencies = [
        AuthFeatureDependencies::class,
    ],
    modules = [
        ViewModelModule::class,
        AuthSdkModule::class,
    ],
)
@AuthFeatureScope
internal interface AuthFeatureComponent {
    @Component.Factory
    interface Factory {
        fun create(deps: AuthFeatureDependencies): AuthFeatureComponent
    }

    val impl: AuthFeatureImpl

    companion object {
        fun create(deps: AuthFeatureDependencies): AuthFeatureComponent {
            return DaggerAuthFeatureComponent.factory().create(deps)
        }
    }
}