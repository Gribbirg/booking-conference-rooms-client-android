package ru.mirea.bookingconferencerooms.coreapi

sealed class ApiResponse<out T> {
    data class Success<T>(val body: T) : ApiResponse<T>()
    sealed class Error : ApiResponse<Nothing>() {
        data class HttpError(val code: Int, val errorBody: String?) : Error()
        data object NetworkError : Error()
        data object SerializationError : Error()
        data object Unauthorized : Error()
        data object NotFound : Error()
        data object UnknownError : Error()
    }

    fun getOrNull(): T? {
        return when (this) {
            is Success -> body
            else -> null
        }
    }

    fun <F> mapTo(map: T.() -> F): ApiResponse<F> {
        return when (this) {
            is Error -> this
            is Success -> Success(this.body.map())
        }
    }
}