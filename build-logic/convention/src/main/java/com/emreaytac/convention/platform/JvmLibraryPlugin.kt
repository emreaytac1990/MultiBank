package com.emreaytac.convention.platform

import com.emreaytac.convention.ext.configureKotlinJvm
import com.emreaytac.convention.ext.libs
import com.emreaytac.convention.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")

            configureKotlinJvm()

            dependencies {
                testImplementation("kotlin.test", libs)
            }
        }
    }
}