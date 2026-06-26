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

    /*buildTypes {
        release {
            isMinifyEnabled = true
        }
    }*/


    // CHANGED: Group both release and debug variants into a single component
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

    implementation(libs.cardreader.api)
}

// CHANGED: Create one single publication coordinating both variants under version 0.0.1
afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("allVariants") {
                groupId = "com.aah.sdk.services"
                artifactId = "cardreader-impl"
                version = "0.0.1" // Both debug and release share this exact coordinate

                // Points to the multipleVariants component we defined inside the android block
                from(components["allBuildTypes"])
            }
        }
    }
}
