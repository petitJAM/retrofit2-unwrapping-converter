buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}

allprojects {
    group = "dev.petitjam"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}
