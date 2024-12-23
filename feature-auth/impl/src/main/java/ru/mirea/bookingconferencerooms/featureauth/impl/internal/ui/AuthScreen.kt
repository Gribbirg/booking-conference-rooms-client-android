package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.mirea.bookingconferencerooms.coreui.components.ErrorScreen
import ru.mirea.bookingconferencerooms.coreui.components.LoadingScreen
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.ImmutableSharedFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction
import ru.mirea.bookingconferencerooms.featureauth.impl.R
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.models.ImmutableAuthSdk
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components.AuthScreenContent
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components.AuthScreenScaffold
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components.EffectsHandler
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.preview.AuthStatesPreviewParameterProvider
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewAction
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewEffect
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewState

@Composable
internal fun AuthScreen(
    state: AuthViewState,
    onAction: (AuthViewAction) -> Unit,
    effectFlow: ImmutableSharedFlow<AuthViewEffect>,
    onNavAction: (AuthFeatureNavAction) -> Unit,
    sdk: ImmutableAuthSdk,
) {
    EffectsHandler(
        effectFlow = effectFlow,
        onAction = onAction,
        onNavAction = onNavAction,
        sdk = sdk,
    )

    AuthScreenStates(
        state = state,
        onAction = onAction,
    )
}

@Composable
private fun AuthScreenStates(
    state: AuthViewState,
    onAction: (AuthViewAction) -> Unit,
) {
    AuthScreenScaffold(
        title = stringResource(R.string.auth),
    ) { innerPadding ->
        when (state) {
            AuthViewState.Loading -> LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            AuthViewState.Error -> ErrorScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is AuthViewState.Loaded -> AuthScreenContent(
                state = state,
                onAction = onAction,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )
        }
    }
}

@Preview
@Composable
private fun AuthScreenPreview(
    @PreviewParameter(AuthStatesPreviewParameterProvider::class)
    state: AuthViewState,
) {
    AuthScreenStates(
        state = state,
        onAction = {},
    )
}