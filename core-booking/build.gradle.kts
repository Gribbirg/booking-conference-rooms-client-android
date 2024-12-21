plugins {
    id("convention.core-module")
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "ru.mirea.bookingconferencerooms.corebooking"
}

dependencies {
    implementation(project(":core-serialization"))
}