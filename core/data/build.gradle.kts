plugins {
    alias(libs.plugins.multibank.android.library)
    alias(libs.plugins.multibank.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.emreaytac.data"

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
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":core:websocket"))
    implementation(project(":core:di"))
}