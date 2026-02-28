package com.emreaytac.convention.ext

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

internal val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")



internal fun DependencyHandler.implementation(library: String, versionCatalog: VersionCatalog) {
    add("implementation", versionCatalog.findLibrary(library).get())
}

internal fun DependencyHandler.testImplementation(library: String, versionCatalog: VersionCatalog) {
    add("testImplementation", versionCatalog.findLibrary(library).get())
}

internal fun DependencyHandler.androidTestImplementation(library: String, versionCatalog: VersionCatalog) {
    add("androidTestImplementation", versionCatalog.findLibrary(library).get())
}

internal fun DependencyHandler.debugImplementation(
    library: String,
    versionCatalog: VersionCatalog
) {
    add("debugImplementation", versionCatalog.findLibrary(library).get())
}

internal fun DependencyHandler.kapt(library: String, versionCatalog: VersionCatalog) {
    add("kapt", versionCatalog.findLibrary(library).get())
}

internal fun DependencyHandler.ksp(library: String, versionCatalog: VersionCatalog) {
    add("ksp", versionCatalog.findLibrary(library).get())
}

internal fun DependencyHandler.implementationProject(p: String) {
    add("implementation", project(p))
}

internal fun DependencyHandler.implementationPlatform(
    library: String,
    versionCatalog: VersionCatalog
) {
    add("implementation", platform(versionCatalog.findLibrary(library).get()))
}