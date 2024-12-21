package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.modules

import dagger.Module
import dagger.Provides
import ru.mirea.bookingconferencerooms.coreui.vm.ViewModelProvider
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.BookingViewModel
import javax.inject.Provider

@Module
internal interface ViewModelModule {
    companion object {
        @Provides
        fun viewModelProvider(provider: Provider<BookingViewModel>): ViewModelProvider<BookingViewModel> {
            return ViewModelProvider { provider.get() }
        }
    }
}