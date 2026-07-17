plugins {
    alias(libs.plugins.android.library)
    id("org.jetbrains.dokka")
}
dokka {
    this.moduleName.set("Payment-Api")
}

android {
    namespace = "com.aah.sdk.simplesdk.services.payment.impl"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    implementation(project(":services:payment:api"))
}