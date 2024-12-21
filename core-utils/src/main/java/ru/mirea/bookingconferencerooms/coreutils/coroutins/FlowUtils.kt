package ru.mirea.bookingconferencerooms.coreutils.coroutins

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.collectIn(scope: CoroutineScope, block: suspend (T) -> Unit) {
    scope.launch {
        collect(block)
    }
}