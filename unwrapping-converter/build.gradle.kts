plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.squareup.retrofit2:retrofit:2.9.0")
}