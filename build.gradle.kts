// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.dokka") version "1.7.10"
}

buildscript {
    dependencies {
        classpath ("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }
}