package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list

import kotlinx.coroutines.launch
import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.coremvi.BaseMviViewModel
import ru.mirea.bookingconferencerooms.coreutils.coroutins.collectIn
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState.Auth
import ru.mirea.bookingconferencerooms.featureauth.api.models.AvatarSize
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo.BookingFeatureRepository
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models.BookingFeatureAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models.BookingFeatureEffect
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.list.models.BookingFeatureState
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class BookingViewModel @Inject constructor(
    private val repo: BookingFeatureRepository,
    private val authFlow: AuthFlow,
) : BaseMviViewModel<BookingFeatureState, BookingFeatureAction, BookingFeatureEffect>(
    initState = BookingFeatureState.Loading,
) {

    init {
        loadRooms()
        authFlow.collectIn(scope, ::onAuthStateChange)
    }

    override suspend fun handleAction(action: BookingFeatureAction) = Unit

    override fun onError(coroutineContext: CoroutineContext, throwable: Throwable) {
        setErrorState()
    }

    private fun setErrorState() {
        updateState { BookingFeatureState.Error }
    }

    private fun loadRooms() {
        backgroundScope.launch {
            val response = repo.getRooms()
            val userImageUrl = (authFlow.value as? Auth)?.user?.avatarUrl(AvatarSize.MIDDLE)
            if (userImageUrl == null) {
                emitEffect(BookingFeatureEffect.ToAuth)
                return@launch
            }
            updateStateByApiResponse(response) { data ->
                updateState { BookingFeatureState.Loaded(data, userImageUrl) }
            }
        }
    }

    private fun <T> updateStateByApiResponse(apiResponse: ApiResponse<T>, onSuccess: (T) -> Unit) {
        when (apiResponse) {
            is ApiResponse.Error -> setErrorState()
            is ApiResponse.Success -> onSuccess(apiResponse.body)
        }
    }

    private suspend fun onAuthStateChange(authState: AuthState) {
        val userImageUrl = (authState as? Auth)?.user?.avatarUrl(AvatarSize.MIDDLE)
        if (userImageUrl == null) {
            emitEffect(BookingFeatureEffect.ToAuth)
            return
        }
        updateState {
            when (this) {
                is BookingFeatureState.Loaded -> copy(userImageUrl = userImageUrl)
                else -> this
            }
        }
    }
}