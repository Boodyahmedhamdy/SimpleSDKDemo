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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    flavorDimensions += listOf("customer")
    productFlavors {
        create("fawry") {
            dimension = "customer"
        }
        create("paymob") {
            dimension = "customer"
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
}

fun DependencyHandlerScope.setupPrinterService() {
    implementation(libs.printer.api)
    "fawryImplementation"(libs.printer.impl)
//    "paymobImplementation"(libs.printer.impl)
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

