package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewState

internal class AuthStatesPreviewParameterProvider : PreviewParameterProvider<AuthViewState> {
    override val values: Sequence<AuthViewState>
        get() = sequenceOf(
            AuthViewState.Loading,
            AuthViewState.Error,
            AuthViewState.Loaded,
        )
}