package ru.mirea.bookingconferencerooms.featureauth.impl.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.mirea.bookingconferencerooms.coreui.vm.ViewModelProvider
import ru.mirea.bookingconferencerooms.coreui.vm.createViewModel
import ru.mirea.bookingconferencerooms.coreui.vm.extensions.asImmutableFlow
import ru.mirea.bookingconferencerooms.featureauth.api.AuthFeature
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthFlow
import ru.mirea.bookingconferencerooms.featureauth.api.models.AuthNavActionHandler
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.data.AuthRepository
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.models.ImmutableAuthSdk
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.AuthScreen
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.compose.ProfileScreen
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.profile.vm.ProfileViewModel
import ru.mirea.bookingconferencerooms.featureauth.impl.internal.ui.vm.AuthViewModel
import javax.inject.Inject

internal class AuthFeatureImpl @Inject constructor(
    private val viewModelProvider: ViewModelProvider<AuthViewModel>,
    private val profileViewModelProvider: ViewModelProvider<ProfileViewModel>,
    private val authRepository: AuthRepository,
    private val sdk: ImmutableAuthSdk,
) : AuthFeature {
    @Composable
    override fun AuthScreen(
        onNavAction: AuthNavActionHandler,
    ) {
        val viewModel = createViewModel {
            viewModelProvider.get()
        }

        val state by viewModel.uiState.collectAsState()

        AuthScreen(
            state = state,
            effectFlow = viewModel.uiEffect.asImmutableFlow(),
            onAction = viewModel::onAction,
            onNavAction = onNavAction::handle,
            sdk = sdk,
        )
    }

    @Composable
    override fun ProfileScreen(onNavAction: AuthNavActionHandler) {
        val viewModel = createViewModel {
            profileViewModelProvider.get()
        }

        val state by viewModel.uiState.collectAsState()

        ProfileScreen(
            state = state,
            effectFlow = viewModel.uiEffect.asImmutableFlow(),
            onAction = viewModel::onAction,
            onNavAction = onNavAction::handle,
        )
    }

    override val authFlow: AuthFlow
        get() = authRepository.authFlow
}
