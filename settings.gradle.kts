pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Booking conference rooms"
includeBuild("build-logic")
include(":app")
include(":core-ui")
include(":core-mvi")
include(":feature-booking:api")
include(":feature-booking:impl")
include(":core-booking")
include(":core-api")
include(":feature-auth")
include(":feature-auth:api")
include(":feature-auth:impl")
include(":feature-launch")
include(":feature-launch:api")
include(":feature-launch:impl")
include(":core-utils")
include(":core-auth")
include(":core-serialization")
