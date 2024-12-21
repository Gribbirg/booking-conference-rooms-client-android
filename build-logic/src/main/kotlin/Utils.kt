import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import java.io.File

fun BaseExtension.baseAndroidConfig(project: Project) {
    with(project) {
        setCompileSdkVersion(AndroidConst.COMPILE_SKD)

        defaultConfig {
            minSdk = AndroidConst.MIN_SKD

            vectorDrawables {
                useSupportLibrary = true
            }
        }

        signingConfigs {
            create("release-signed") {
                storeFile = File(propertiesSecretes.getProperty("KEY_STORE_PATH"))
                storePassword = propertiesSecretes.getProperty("KEY_STORE_PASSWORD")
                keyAlias = propertiesSecretes.getProperty("KEY_ALIAS")
                keyPassword = propertiesSecretes.getProperty("KEY_PASSWORD")
            }
        }

        buildFeatures.apply {
            buildConfig = true
        }

        buildTypes {
            getByName("debug") {
                buildConfigField(
                    "String",
                    "HOST_URL",
                    propertiesSecretes.getProperty("HOST_URL_DEBUG"),
                )
            }
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
                buildConfigField(
                    "String",
                    "HOST_URL",
                    propertiesSecretes.getProperty("HOST_URL_RELEASE"),
                )
            }
            create("release-signed") {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
                signingConfig = signingConfigs.getByName("release-signed")
                buildConfigField(
                    "String",
                    "HOST_URL",
                    propertiesSecretes.getProperty("HOST_URL_RELEASE"),
                )
            }
        }

        compileOptions {
            sourceCompatibility = AndroidConst.COMPILE_JDK_VERSION
            targetCompatibility = AndroidConst.COMPILE_JDK_VERSION
        }

        kotlinOptions {
            jvmTarget = AndroidConst.KOTLIN_JVM_TARGET
        }
    }
}