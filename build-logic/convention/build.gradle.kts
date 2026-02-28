import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}
group = "com.emreaytac.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks{
    // It ensures compliance with Gradle best practices
    validatePlugins{
        enableStricterValidation = true
        failOnWarning = true
    }
}


gradlePlugin {
    plugins {
        register("androidAppPlugin") {
            id = libs.plugins.multibank.android.application.asProvider().get().pluginId
            implementationClass = "com.emreaytac.convention.platform.AndroidAppPlugin"
        }
        register("androidAppComposePlugin") {
            id = libs.plugins.multibank.android.application.compose.get().pluginId
            implementationClass = "com.emreaytac.convention.platform.AndroidAppComposePlugin"
        }
        register("androidLibraryPlugin") {
            id = libs.plugins.multibank.android.library.asProvider().get().pluginId
            implementationClass = "com.emreaytac.convention.platform.AndroidLibraryPlugin"
        }
        register("androidLibraryComposePlugin") {
            id = libs.plugins.multibank.android.library.compose.get().pluginId
            implementationClass = "com.emreaytac.convention.platform.AndroidLibraryComposePlugin"
        }
        register("jvmLibraryPlugin") {
            id = libs.plugins.multibank.jvm.library.get().pluginId
            implementationClass = "com.emreaytac.convention.platform.JvmLibraryPlugin"
        }
        register("hiltPlugin") {
            id = libs.plugins.multibank.hilt.get().pluginId
            implementationClass = "com.emreaytac.convention.HiltPlugin"
        }
    }
}