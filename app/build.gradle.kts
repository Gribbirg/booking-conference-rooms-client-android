plugins {
    id("convention.android-app")
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("telegram-reporter")
}

tgReporter {
    token = propertiesSecretes.getProperty("TELEGRAM_BOT_API")
    chatId = propertiesSecretes.getProperty("TELEGRAM_CHAT_ID")
    enableDetails = true
}

android {
    namespace = "ru.mirea.bookingconferencerooms"
    defaultConfig {
        applicationId = "ru.mirea.bookingconferencerooms"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(project(":feature-booking:impl"))
    implementation(project(":feature-auth:impl"))
    implementation(project(":core-ui"))
}