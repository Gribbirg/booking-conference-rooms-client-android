plugins {
    id("convention.feature-module")
}

android {
    namespace = "ru.mirea.bookingconferencerooms.featurebooking.api"
}

dependencies {
    api(project(":feature-auth:api"))
}
