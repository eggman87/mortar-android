
plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":token-model"))

    implementation(libs.kotlinpoet)
    implementation(libs.auto.service.annotations)
    implementation(libs.kotlinx.serialization.json)

    kapt(libs.auto.service)

    testImplementation(libs.junit)
}