plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {
    // TODO: move versions to an object or something
    // TODO: Check these for updates?
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-moshi:2.9.0")
    api("com.squareup.okhttp3:okhttp:4.9.1")
    api("com.squareup.moshi:moshi:1.12.0")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set(prop("POM_ARTIFACT_ID"))
                description.set(prop("POM_DESCRIPTION"))
                url.set(prop("POM_URL"))

                licenses {
                    license {
                        name.set(prop("POM_LICENSE_NAME"))
                        url.set(prop("POM_LICENSE_URL"))
                        distribution.set(prop("POM_LICENSE_DIST"))
                    }
                }

                developers {
                    developer {
                        id.set(prop("POM_DEVELOPER_ID"))
                        name.set(prop("POM_DEVELOPER_NAME"))
                    }
                }

                scm {
                    url.set(prop("POM_SCM_URL"))
                    connection.set(prop("POM_SCM_CONNECTION"))
                    developerConnection.set(prop("POM_SCM_DEV_CONNECTION"))
                }
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(*publishing.publications.toTypedArray())
}

fun prop(name: String): String {
    return project.property(name) as String
}