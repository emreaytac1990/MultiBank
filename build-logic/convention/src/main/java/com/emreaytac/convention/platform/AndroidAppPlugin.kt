package com.emreaytac.convention.platform

import com.android.build.api.dsl.ApplicationExtension
import com.emreaytac.convention.ext.configureKotlinAndroid
import com.emreaytac.convention.ext.implementation
import com.emreaytac.convention.ext.libs
import com.emreaytac.convention.utils.configureGradleManagedDevices
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidAppPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }


            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = libs.findVersion("compileSdk").get().toString().toInt()
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
            }


            dependencies {
                implementation("androidx-core-ktx", target.libs)
                implementation("androidx-lifecycle-runtime-ktx", target.libs)
                implementation("kotlinx-collections-immutable", target.libs)
                implementation("kotlinx-coroutines-core", target.libs)
            }
        }
    }
}