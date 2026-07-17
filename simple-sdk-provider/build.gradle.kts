plugins {
    alias(libs.plugins.android.library)
//    id("maven-publish")
}

android {
    namespace = "com.aah.sdk.simplesdk.provider"
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

    flavorDimensions += listOf("customer")
    productFlavors {
        create("fawry") {
            dimension = "customer"
            // this.matchingFallbacks.add("release")
        }
        create("paymob") {
            dimension = "customer"
            // this.matchingFallbacks.add("release")
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

    implementation(project(":simple-sdk"))

    setupPaymentService()
    setupCardReaderService()
    setupPrinterService()
    setupBiometricsService()
}

fun DependencyHandlerScope.setupPrinterService() {
    implementation(libs.printer.api)

//    "fawryImplementation"(libs.printer.impl)
    // add("fawryDebugImplementation", libs.printer.impl)
//     "fawryDebugImplementation"(libs.printer.impl)
//    "paymobImplementation"(libs.printer.impl)

    configurations.configureEach {
        when (name) {
            "fawryDebugImplementation" -> this@setupPrinterService.dependencies.add(this.name, libs.cardreader.impl)
            "fawryReleaseImplementation" -> this@setupPrinterService.dependencies.add(this.name, libs.cardreader.impl)

            "paymobDebugImplementation" -> this@setupPrinterService.dependencies.add(this.name, libs.cardreader.impl)
            "paymobReleaseImplementation" -> this@setupPrinterService.dependencies.add(this.name, libs.cardreader.impl)
        }
    }
}

fun DependencyHandlerScope.setupCardReaderService() {
    implementation(libs.cardreader.api)
    "fawryImplementation"(libs.cardreader.impl)
    "paymobImplementation"(libs.cardreader.impl)
}

fun DependencyHandlerScope.setupPaymentService() {
    implementation(project(":services:payment:api"))
    "fawryImplementation"(project(":services:payment:impl"))
//    "paymobImplementation"(project(":services:payment:impl"))
}

fun DependencyHandlerScope.setupBiometricsService() {
    implementation(libs.biometrics.api)
    "fawryImplementation"(libs.biometrics.impl)
    "paymobImplementation"(libs.biometrics.impl)
}

