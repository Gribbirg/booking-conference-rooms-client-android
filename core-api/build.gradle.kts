plugins {
    id("convention.core-module")
}

android {
    namespace = "ru.mirea.bookingconferencerooms.coreapi"
}

dependencies {
    api(libs.bundles.ktor)
}