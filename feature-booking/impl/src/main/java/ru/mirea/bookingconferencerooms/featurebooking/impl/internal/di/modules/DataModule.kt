package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.di.modules

import dagger.Binds
import dagger.Module
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api.BookingFeatureApi
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.api.BookingFeatureApiMock
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo.BookingFeatureRepository
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo.BookingFeatureRepositoryImpl

@Module
internal interface DataModule {
    @Binds
    fun bindRepository(impl: BookingFeatureRepositoryImpl): BookingFeatureRepository

    @Binds
    fun bindApi(impl: BookingFeatureApiMock): BookingFeatureApi
}