plugins {
    alias(libs.plugins.android.library)
    id("org.jetbrains.dokka")
    id("maven-publish")
}

dokka {
    this.moduleName.set("Biometrics-Api")
    this.moduleVersion.set("0.0.1")
}

android {
    namespace = "com.aah.sdk.simplesdk.services.biometrics.api"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    publishing {
        multipleVariants("allBuildTypes") {
            includeBuildTypeValues("release", "debug")
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("allVariants") {
                groupId = "com.aah.sdk.services"
                artifactId = "biometrics-api"
                version = "0.0.1"

                from(components["allBuildTypes"])
            }
        }
    }
}
