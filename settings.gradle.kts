pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
}

rootProject.name = "SimpleSDKDemo"
include(":app")
include(":simple-sdk")
include(":services:payment:api")
include(":services:payment:impl")
include(":services:cardreader:api")
include(":services:cardreader:impl")
include(":services:printer:api")
include(":services:printer:impl")
include(":simple-sdk-provider")
