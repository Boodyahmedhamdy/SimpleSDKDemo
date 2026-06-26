// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    id("org.jetbrains.dokka") version "2.2.0"
}

dokka {
    this.moduleName.set("Simple SDK")
    this.dokkaPublications.html {
        this.outputDirectory.set(rootDir.resolve("docs/"))
    }
}

dependencies {
    dokka(project(":services:cardreader:api"))
    dokka(project(":services:cardreader:impl"))

    dokka(project(":services:payment:api"))
    dokka(project(":services:payment:impl"))

    dokka(project(":services:printer:api"))
    dokka(project(":services:printer:impl"))
}