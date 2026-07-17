plugins {
    alias(libs.plugins.android.library)
    id("maven-publish") // Applied the publishing plugin
    id("org.jetbrains.dokka")
}

dokka {
    this.moduleName.set("CardReader-Impl")
}

android {
    namespace = "com.aah.sdk.simplesdk.services.cardreader.impl"
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

    buildTypes {
        getByName("release") {
            // Enable code shrinking and obfuscation
            isMinifyEnabled = true

            // (Optional but recommended) Enable resource shrinking
            // isShrinkResources = true

            // Apply default Android optimization rules and custom rules
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro",
                "consumer-rules.keep"
            )
        }
    }



    publishing {
        singleVariant("debug")
        singleVariant("release")
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.cardreader.api)
}

afterEvaluate {
    publishing {
        publications {
            // 1. Register the DEBUG publication
            register<MavenPublication>("debug") {
                groupId = "com.aah.sdk"
                artifactId = "cardreader-impl-debug" // Custom debug name
                version = "1.0.0"

                afterEvaluate {
                    from(components["debug"]) // Pulls the debug AAR and its dependencies
                }
            }

            // 2. Register the RELEASE publication
            register<MavenPublication>("release") {
                groupId = "com.aah.sdk"
                artifactId = "cardreader-impl-release" // Custom release name
                version = "1.0.0"

                afterEvaluate {
                    from(components["release"]) // Pulls the release AAR and its dependencies
                }
            }
        }
    }
}
