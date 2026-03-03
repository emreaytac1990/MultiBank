plugins {
    alias(libs.plugins.multibank.android.library)
    alias(libs.plugins.multibank.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.emreaytac.data"

    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"wss://ws.postman-echo.com/raw\"")
        }
        debug {
            buildConfigField("String", "BASE_URL", "\"wss://ws.postman-echo.com/raw\"")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }
}

dependencies {
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    api(project(":core:websocket"))
    implementation(project(":core:di"))
    api(project(":core:domain"))
    api(project(":core:datastore"))

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization)

    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}