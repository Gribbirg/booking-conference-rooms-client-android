plugins {
    id("convention.feature-module")
}

android {
    namespace = "ru.mirea.bookingconferencerooms.featurebooking.impl"
}

dependencies {
    api(project(":feature-booking:api"))

    implementation(project(":core-ui"))
    implementation(project(":core-mvi"))
    implementation(project(":core-api"))
    implementation(project(":core-booking"))
}