package ru.mirea.bookingconferencerooms.coreui.vm

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel

@Immutable
fun interface ViewModelProvider<V: ViewModel> {
    fun get(): V
}