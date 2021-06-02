plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
}

apply(from = "../gradle/gradle-mvn-push.gradle")

repositories {
    mavenCentral()
}

dependencies {
    // TODO: move versions to an object or something
    // TODO: Check these for updates?
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-moshi:2.9.0")
    api("com.squareup.okhttp3:okhttp:4.9.0")
    api("com.squareup.moshi:moshi:1.11.0")
    // api("com.squareup.okio:okio:2.8.0")
}