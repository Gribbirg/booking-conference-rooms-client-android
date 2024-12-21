package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.mirea.bookingconferencerooms.featureauth.impl.R
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewAction
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewState

@Composable
internal fun AuthScreenContent(
    state: AuthViewState.Loaded,
    onAction: (AuthViewAction) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.auth_to_use_app),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onAction(AuthViewAction.OpenAuth) },
        ) {
            Text(text = stringResource(R.string.auth_button_text))
        }
    }
}