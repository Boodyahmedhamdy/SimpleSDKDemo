plugins {
    alias(libs.plugins.android.library)
    id("org.jetbrains.dokka")
    id("maven-publish") // Added: Applied maven-publish plugin
}

dokka {
    this.moduleName.set("Printer-Api")
}

android {
    namespace = "com.aah.sdk.simplesdk.services.printer.api"
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

    // Added: Configured AGP to package both release and debug variants into a single component
    publishing {
        multipleVariants("allBuildTypes") {
            includeBuildTypeValues("release", "debug")
            withSourcesJar() // Automatically attaches the source code jar
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

// Added: Publication configuration linking variant components and Dokka docs
afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("allVariants") {
                groupId = "com.aah.sdk.services"
                artifactId = "printer-api"
                version = "0.0.1"

                // Binds the release and debug multi-variant artifacts to this publication
                from(components["allBuildTypes"])

            }
        }
    }
}