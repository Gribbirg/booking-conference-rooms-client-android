package ru.mirea.bookingconferencerooms.coreutils

import android.util.Log

fun logError(throwable: Throwable) {
    Log.e(
        "Error",
        "onError: ${throwable::class.java.simpleName}\n${throwable.message}\n${
            throwable.stackTrace.joinToString("\n")
        }"
    )
}