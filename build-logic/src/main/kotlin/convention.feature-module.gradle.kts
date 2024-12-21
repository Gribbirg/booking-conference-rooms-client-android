plugins {
    id("convention.core-module")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(libs.dagger)
    ksp(libs.dagger.complier)
    implementation(libs.bundles.coil)

    implementation(project(":core-utils"))

}