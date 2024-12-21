package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.ImmutableSharedFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.models.ImmutableAuthSdk
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewAction
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewEffect

@Composable
internal fun EffectsHandler(
    effectFlow: ImmutableSharedFlow<AuthViewEffect>,
    onAction: (AuthViewAction) -> Unit,
    onNavAction: (AuthFeatureNavAction) -> Unit,
    sdk: ImmutableAuthSdk,
) {
    val launcher = rememberLauncherForActivityResult(sdk.contract) { result ->
        onAction(
            AuthViewAction.HandleAuthResult(result),
        )
    }

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when (effect) {
                is AuthViewEffect.OpenAuth -> launcher.launch(effect.loginOptions)
                AuthViewEffect.Close -> onNavAction(AuthFeatureNavAction.OpenMain)
            }
        }
    }
}