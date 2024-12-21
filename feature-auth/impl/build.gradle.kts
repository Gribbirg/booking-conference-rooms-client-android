plugins {
    id("convention.feature-module")
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "ru.mirea.bookingconferencerooms.featureauth.impl"
}

dependencies {
    api(project(":feature-auth:api"))
    implementation(project(":core-ui"))
    implementation(project(":core-mvi"))
    implementation(project(":core-api"))
    implementation(project(":core-utils"))

    implementation(libs.yandex.auth)
    implementation(libs.androidx.datastore)
}