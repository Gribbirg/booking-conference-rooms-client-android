package ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.mirea.bookingconferencerooms.coreui.components.ErrorScreen
import ru.mirea.bookingconferencerooms.coreui.components.LoadingScreen
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.ImmutableSharedFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFeatureNavAction
import ru.mirea.bookingconferencerooms.featureauth.api.models.AvatarSize
import ru.mirea.bookingconferencerooms.featureauth.impl.R
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.components.AuthScreenScaffold
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm.ProfileViewAction
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm.ProfileViewEffect
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm.ProfileViewState

@Composable
internal fun ProfileScreen(
    state: ProfileViewState,
    onAction: (ProfileViewAction) -> Unit,
    effectFlow: ImmutableSharedFlow<ProfileViewEffect>,
    onNavAction: (AuthFeatureNavAction) -> Unit,
) {
    LaunchedEffect(Unit) {
        effectFlow.collect {
            when (it) {
                ProfileViewEffect.Close -> onNavAction(AuthFeatureNavAction.Back)
                ProfileViewEffect.OpenAuth -> onNavAction(AuthFeatureNavAction.OpenAuth)
            }
        }
    }

    AuthScreenScaffold(
        title = stringResource(R.string.profile),
        onBack = { onNavAction(AuthFeatureNavAction.Back) },
    ) { innerPadding ->
        when (state) {
            ProfileViewState.Loading -> LoadingScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            ProfileViewState.Error -> ErrorScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            )

            is ProfileViewState.Loaded -> ProfileScreenContent(
                state = state,
                onAction = onAction,
            )
        }
    }
}

@Composable
private fun ProfileScreenContent(
    state: ProfileViewState.Loaded,
    onAction: (ProfileViewAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = state.user.avatarUrl(AvatarSize.BIG),
            contentDescription = "User Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.user.name,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )

        state.user.login.let { login ->
            Text(
                text = "Login: $login",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

        state.user.email.let { email ->
            Text(
                text = "Email: $email",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }

        TextButton(
            onClick = { onAction(ProfileViewAction.Logout) },
        ) {
            Text(
                text = stringResource(R.string.logout)
            )
        }
    }
}