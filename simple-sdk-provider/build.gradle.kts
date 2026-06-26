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
//            versionNameSuffix = "fawry"
        }
        create("paymob") {
            dimension = "customer"
//            versionNameSuffix = "paymob"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }

//    publishing {
//        this.multipleVariants("allCustomerFlavors") {
//            includeBuildTypeValues("release", "debug")
//            includeFlavorDimensionAndValues("customer", "fawry", "paymob")
//        }
//    }

}


//afterEvaluate {
//    publishing {
//        publications {
//            register<MavenPublication>("MavenJava") {
//                // This coordinates group, artifact, and version info
//                groupId = "com.aah.sdk"
//                artifactId = "simplesdk"
//                version = "0.0.1" // Use SNAPSHOT for local iterative testing
//
//                // Link this publication to the variants we grouped inside the android block
//                from(components["allCustomerFlavors"])
//            }
//        }
//    }
//}



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
    implementation("com.aah.sdk.services:printer-api:0.0.1")
    "fawryImplementation"("com.aah.sdk.services:printer-impl:0.0.1")
}

fun DependencyHandlerScope.setupCardReaderService() {
    implementation("com.aah.sdk.services:cardreader-api:0.0.1")
    "fawryImplementation"("com.aah.sdk.services:cardreader-impl:0.0.1")
    "paymobImplementation"("com.aah.sdk.services:cardreader-impl:0.0.1")
}

fun DependencyHandlerScope.setupPaymentService() {
    implementation(project(":services:payment:api"))
    "fawryImplementation"(project(":services:payment:impl"))
    "paymobImplementation"(project(":services:payment:impl"))
}

