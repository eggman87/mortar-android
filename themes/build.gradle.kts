plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("kapt")
    alias(libs.plugins.dokka)
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

val javadocsJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            artifact(sourcesJar.get())
            artifact(javadocsJar.get())

            pom {
                name.set("MDS Tokens")
                description.set("Mortar Design System Tokens")
                url.set("https://github.com/eggman87")
            }
        }
    }

    repositories {
        maven {
            // this is local to build folder to demo the publishing working.
            url = uri("${rootProject.buildDir}/repos")
        }
    }
}

dependencies {
    kapt(project(":token-compiler"))
    api(project(":token-model"))
}