buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
    }
}

allprojects {
    group = project.property("GROUP")!!
    version = project.property("VERSION_NAME")!!

    repositories {
        mavenCentral()
    }
}
