plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dokka)
    `maven-publish`
}

android {
    namespace = "com.eggman.mortar.android"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

val androidJavadocsJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc)
}

publishing {
        publications {
            create<MavenPublication>("mavenAndroid") {
                afterEvaluate {
                    from(components["release"])

                    artifact(androidJavadocsJar.get())

                    pom {
                        name.set("MDS Android Compose Core")
                        description.set("Core compose code that is used by compose MDS libraries")
                        url.set("https://github.com/eggman87")
                    }
                }
        }

        repositories {
            maven {
                url = uri("${rootProject.buildDir}/repos")
            }
        }
    }
}

dependencies {

    implementation(project(":themes"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    api(libs.androidx.ui)
    api(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}