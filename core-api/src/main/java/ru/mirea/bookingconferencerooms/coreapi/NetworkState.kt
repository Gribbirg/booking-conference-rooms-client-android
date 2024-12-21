package ru.mirea.bookingconferencerooms.coreapi

sealed class NetworkState {
    data object Updating : NetworkState()
    data object Success : NetworkState()
    sealed class Error : NetworkState() {
        data object NetworkError : Error()
        data object NotFound : Error()
        data object UnknownError : Error()
    }
}