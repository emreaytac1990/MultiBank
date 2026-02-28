package com.emreaytac.convention.platform

import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.emreaytac.convention.ext.androidTestImplementation
import com.emreaytac.convention.ext.configureKotlinAndroid
import com.emreaytac.convention.ext.disableUnnecessaryAndroidTests
import com.emreaytac.convention.ext.implementation
import com.emreaytac.convention.ext.libs
import com.emreaytac.convention.ext.testImplementation
import com.emreaytac.convention.utils.configureGradleManagedDevices
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                testOptions.targetSdk = 36
                lint.targetSdk = 36
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.animationsDisabled = true
                // configureFlavors(this)
                configureGradleManagedDevices(this)
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }

            dependencies {
                androidTestImplementation("kotlin.test", libs)
                testImplementation("kotlin.test", libs)
                testImplementation("junit", libs)
            }
        }
    }
}