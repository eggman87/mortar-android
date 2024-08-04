plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.binary.compatibility.validator)
}

val libraryVersion: String by project

allprojects {
    version = libraryVersion
    group = "com.eggman.mortar"
}

apiValidation {
    ignoredProjects.addAll(listOf("token-compiler", "demo-mortar"))
}
