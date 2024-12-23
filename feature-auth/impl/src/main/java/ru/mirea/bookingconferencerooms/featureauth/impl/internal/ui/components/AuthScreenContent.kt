package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.mirea.bookingconferencerooms.featureauth.impl.R
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewAction
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewState

@Composable
internal fun AuthScreenContent(
    state: AuthViewState.Loaded,
    onAction: (AuthViewAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            text = stringResource(R.string.auth_to_use_app),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = { onAction(AuthViewAction.OpenAuth) },
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = stringResource(R.string.auth_button_text),
            )
        }
    }
}