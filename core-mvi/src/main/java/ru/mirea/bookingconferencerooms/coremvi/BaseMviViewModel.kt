package ru.mirea.bookingconferencerooms.coremvi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewAction
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewEffect
import ru.mirea.bookingconferencerooms.coremvi.models.BaseMviViewState
import kotlin.coroutines.CoroutineContext

abstract class BaseMviViewModel<S : BaseMviViewState, A : BaseMviViewAction, E : BaseMviViewEffect>(
    initState: S,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initState)
    val uiState: StateFlow<S> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<E>()
    val uiEffect: SharedFlow<E> = _uiEffect.asSharedFlow()

    private val _uiAction = MutableSharedFlow<A>()

    protected val scope: CoroutineScope =
        viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
            onError(
                coroutineContext,
                throwable,
            )
        }

    protected val backgroundScope: CoroutineScope =
        scope + Dispatchers.IO

    init {
        collectAction()
    }

    fun onAction(action: A) {
        scope.launch { _uiAction.emit(action) }
    }

    protected fun updateState(update: S.() -> S) {
        _uiState.update(update)
    }

    protected suspend fun emitEffect(effect: E) {
        _uiEffect.emit(effect)
    }

    private fun collectAction() {
        scope.launch { _uiAction.collect(::handleAction) }
    }

    protected abstract suspend fun handleAction(action: A)

    protected abstract fun onError(coroutineContext: CoroutineContext, throwable: Throwable)
}