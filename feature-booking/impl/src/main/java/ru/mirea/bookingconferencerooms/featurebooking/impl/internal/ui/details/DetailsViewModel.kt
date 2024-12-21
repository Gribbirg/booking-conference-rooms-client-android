package ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details

import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.mirea.bookingconferencerooms.coreapi.ApiResponse
import ru.mirea.bookingconferencerooms.corebooking.dto.ConferenceInput
import ru.mirea.bookingconferencerooms.coremvi.BaseMviViewModel
import ru.mirea.bookingconferencerooms.coreutils.coroutins.collectIn
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthState.Auth
import ru.mirea.bookingconferencerooms.featureauth.api.models.AvatarSize
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.data.repo.BookingFeatureRepository
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.BookingDialogState
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.ConferencesListState
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiAction
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiEffect
import ru.mirea.bookingconferencerooms.featurebooking.impl.internal.ui.details.models.DetailsUiState
import java.time.OffsetDateTime
import java.util.UUID
import kotlin.coroutines.CoroutineContext

internal class DetailsViewModel @AssistedInject constructor(
    @Assisted
    private val id: UUID,
    private val repository: BookingFeatureRepository,
    private val authFlow: AuthFlow,
) : BaseMviViewModel<DetailsUiState, DetailsUiAction, DetailsUiEffect>(
    initState = DetailsUiState.Loading
) {
    private var lastBooking: BookingDialogState.Opened? = null

    init {
        loadItem()
        authFlow.collectIn(scope, ::onAuthStateChange)
    }

    override suspend fun handleAction(action: DetailsUiAction) {
        when (action) {
            is DetailsUiAction.BookRoom -> updateStateIfLoaded {
                copy(
                    bookingDialogState = BookingDialogState.Opened.default,
                )
            }

            is DetailsUiAction.ConfirmBooking -> confirmBooking(action.name, action.from, action.to)
            DetailsUiAction.DismissBookingDialog -> {
                lastBooking = null
                updateStateIfLoaded {
                    copy(
                        bookingDialogState = BookingDialogState.Closed,
                    )
                }
            }

            DetailsUiAction.RetryBooking -> updateStateIfLoaded {
                copy(
                    bookingDialogState = lastBooking ?: BookingDialogState.Opened.default
                )
            }
        }
    }

    override fun onError(coroutineContext: CoroutineContext, throwable: Throwable) {
        setErrorState()
    }

    private fun setErrorState() {
        updateState { DetailsUiState.Error }
    }

    private fun loadItem() {
        updateState { DetailsUiState.Loading }
        backgroundScope.launch {
            val roomAsync = async { repository.getRoomDetails(id) }
            val conferencesAsync =
                async { repository.getConferencesForRoom(id) }

            val room = roomAsync.await()

            val userImageUrl = (authFlow.value as? Auth)?.user?.avatarUrl(AvatarSize.MIDDLE)
            if (userImageUrl == null) {
                emitEffect(DetailsUiEffect.ToAuth)
                return@launch
            }

            updateStateByApiResponse(room) { roomData ->
                updateState {
                    DetailsUiState.Loaded(
                        room = roomData,
                        userImageUrl = userImageUrl,
                        bookingDialogState = BookingDialogState.Closed,
                    )
                }
            }

            val conferences = conferencesAsync.await()
            updateStateIfLoaded {
                copy(
                    conferencesOfDayState = conferencesOfDayState.copy(
                        listState = if (conferences is ApiResponse.Success)
                            ConferencesListState.Loaded(
                                conferences = conferences.body,
                            )
                        else
                            ConferencesListState.Error
                    )
                )
            }
        }
    }

    private fun <T> updateStateByApiResponse(apiResponse: ApiResponse<T>, onSuccess: (T) -> Unit) {
        when (apiResponse) {
            is ApiResponse.Error -> setErrorState()
            is ApiResponse.Success -> onSuccess(apiResponse.body)
        }
    }

    private fun updateStateIfLoaded(block: DetailsUiState.Loaded.() -> DetailsUiState) {
        updateState {
            if (this is DetailsUiState.Loaded) {
                block()
            } else {
                this
            }
        }
    }

    private suspend fun onAuthStateChange(authState: AuthState) {
        val userImageUrl = (authState as? Auth)?.user?.avatarUrl(AvatarSize.MIDDLE)
        if (userImageUrl == null) {
            emitEffect(DetailsUiEffect.ToAuth)
            return
        }
        updateState {
            when (this) {
                is DetailsUiState.Loaded -> copy(userImageUrl = userImageUrl)
                else -> this
            }
        }
    }

    private fun confirmBooking(
        name: String,
        from: OffsetDateTime,
        to: OffsetDateTime,
    ) {
        lastBooking = BookingDialogState.Opened(name, from, to)
        updateStateIfLoaded { copy(bookingDialogState = BookingDialogState.Loading) }
        backgroundScope.launch {
            val userId = (authFlow.value as? Auth)?.user?.id
            if (userId == null) {
                updateState { DetailsUiState.Error }
                return@launch
            }

            val isAdded = repository.addConference(
                conference = ConferenceInput(
                    name = name,
                    authorId = userId,
                    startTime = from,
                    endTime = to,
                    roomId = id,
                )
            ).getOrNull()

            if (isAdded != null) {
                updateStateIfLoaded {
                    copy(
                        bookingDialogState = BookingDialogState.Closed,
                    )
                }
                loadItem()
            } else {
                updateStateIfLoaded {
                    copy(
                        bookingDialogState = BookingDialogState.BusyTimeSelected,
                    )
                }
            }
        }
    }


    @AssistedFactory
    internal interface Factory {
        fun create(id: UUID): DetailsViewModel
    }
}