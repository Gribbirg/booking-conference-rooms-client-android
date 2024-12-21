package ru.mirea.bookingconferencerooms.featureauth.impl.internal.di

import dagger.Module
import dagger.Provides
import ru.mirea.bookingconferencerooms.coreui.vm.ViewModelProvider
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm.ProfileViewModel
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewModel
import javax.inject.Provider

@Module
internal interface ViewModelModule {
    companion object {
        @Provides
        fun viewModelProvider(provider: Provider<AuthViewModel>): ViewModelProvider<AuthViewModel> {
            return ViewModelProvider { provider.get() }
        }

        @Provides
        fun profileViewModelProvider(provider: Provider<ProfileViewModel>): ViewModelProvider<ProfileViewModel> {
            return ViewModelProvider { provider.get() }
        }
    }
}