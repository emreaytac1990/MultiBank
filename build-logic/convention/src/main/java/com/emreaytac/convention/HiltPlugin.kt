package com.emreaytac.convention

import com.emreaytac.convention.ext.implementation
import com.emreaytac.convention.ext.ksp
import com.emreaytac.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            apply(plugin = "com.google.devtools.ksp")

            dependencies {
                ksp("hilt.compiler", libs)
                ksp("kotlin.metadata", libs)
            }

            // Add support for Jvm Module, base on org.jetbrains.kotlin.jvm
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                dependencies {
                    implementation("hilt-core", libs)
                }
            }

            /** Add support for Android modules, based on [AndroidBasePlugin] */
            pluginManager.withPlugin("com.android.base") {
                apply(plugin = "dagger.hilt.android.plugin")
                dependencies {
                    implementation("hilt-android", libs)
                }
            }
        }
    }
}
