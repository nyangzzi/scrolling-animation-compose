plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
    id("org.jetbrains.dokka") version "1.7.10"
    id("maven-publish") // Add maven-publish plugin
    java
    `java-library`
}

publishing {
    publications {
        create<MavenPublication>("maven-public") {
            groupId = "com.msg"
            artifactId = "library"
            version = "1.0.0"
            from(components["java"])
        }
    }
}