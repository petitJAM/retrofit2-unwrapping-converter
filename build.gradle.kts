import java.util.*

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}

plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

allprojects {
    group = project.property("GROUP")!!
    version = project.property("VERSION_NAME")!!

    repositories {
        mavenCentral()
    }
}

val propertiesFile = file("local.properties")
val properties = Properties()
if (propertiesFile.exists()) {
    properties.load(propertiesFile.inputStream())
}

val sonatypeUsername = properties["SONATYPE_NEXUS_USERNAME"] as? String
val sonatypePassword = properties["SONATYPE_NEXUS_PASSWORD"] as? String

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

            if (sonatypeUsername != null && sonatypePassword != null) {
                username.set(sonatypeUsername)
                password.set(sonatypePassword)
            }
        }
    }
}