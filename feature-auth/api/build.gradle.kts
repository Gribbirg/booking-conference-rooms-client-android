plugins {
    id("convention.feature-module")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "ru.mirea.bookingconferencerooms.featureauth.api"
}
