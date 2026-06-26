plugins {
    alias(libs.plugins.android.library)
    id("maven-publish")
    id("org.jetbrains.dokka")
}
dokka {
    this.moduleName.set("CardReader-Api")
    this.moduleVersion.set("0.0.1")
}
android {
    namespace = "com.aah.sdk.simplesdk.services.cardreader.api"
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

    // CHANGED: Configured AGP to expose both build types for publication
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
                artifactId = "cardreader-api"
                version = "0.0.1" // Both debug and release share this exact coordinate

                // Points to the multipleVariants component we defined inside the android block
                from(components["allBuildTypes"])
            }
        }
    }
}

