package ru.mirea.bookingconferencerooms.coreui.vm.extensions

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.SharedFlow

@Immutable
class ImmutableSharedFlow<E>(
    private val flow: SharedFlow<E>
) : SharedFlow<E> by flow

fun <E> SharedFlow<E>.asImmutableFlow(): ImmutableSharedFlow<E> =
    ImmutableSharedFlow(this)