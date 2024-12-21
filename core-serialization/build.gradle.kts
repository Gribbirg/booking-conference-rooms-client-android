plugins {
    id("convention.core-module")
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "ru.mirea.bookingconferencerooms.coreserialization"
}

dependencies {
    implementation(project(":core-utils"))
}