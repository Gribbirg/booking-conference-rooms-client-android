package ru.mirea.bookingconferencerooms

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

object TransitionAnimations {
    fun mainScreenEnter(animationDuration: Int = DEFAULT_ANIMATION_DURATION):
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            fadeIn(
                animationSpec = tween(durationMillis = animationDuration),
                initialAlpha = 0.999f
            )
        }

    fun mainScreenExit(animationDuration: Int = DEFAULT_ANIMATION_DURATION):
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            fadeOut(
                animationSpec = tween(durationMillis = animationDuration),
                targetAlpha = 0.999f
            )
        }

    fun secondScreenEnter(animationDuration: Int = DEFAULT_ANIMATION_DURATION):
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(
                    animationDuration,
                    easing = FastOutSlowInEasing
                )
            )
        }


    fun secondScreenExit(animationDuration: Int = DEFAULT_ANIMATION_DURATION):
            AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(
                    animationDuration,
                    easing = FastOutSlowInEasing
                )
            )
        }

    private const val DEFAULT_ANIMATION_DURATION = 400
}