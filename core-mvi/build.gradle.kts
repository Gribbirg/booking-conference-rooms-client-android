plugins {
    id("convention.core-module")
}

android {
    namespace = "ru.mirea.bookingconferencerooms.coremvi"
}

dependencies {
    implementation(project(":core-utils"))
}