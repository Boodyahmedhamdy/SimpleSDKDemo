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
        publishing {
            singleVariant("debug")
            singleVariant("release")
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
            // 1. Register the DEBUG publication
            register<MavenPublication>("debug") {
                groupId = "com.aah.sdk"
                artifactId = "cardreader-api-debug" // Custom debug name
                version = "1.0.0"

                afterEvaluate {
                    from(components["debug"]) // Pulls the debug AAR and its dependencies
                }
            }

            // 2. Register the RELEASE publication
            register<MavenPublication>("release") {
                groupId = "com.aah.sdk"
                artifactId = "cardreader-api-release" // Custom release name
                version = "1.0.0"

                afterEvaluate {
                    from(components["release"]) // Pulls the release AAR and its dependencies
                }
            }
        }
    }
}

