package com.emreaytac.convention.ext

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension,
) {
    commonExtension.apply {
        buildFeatures.apply {
            compose = true
        }

    }

    dependencies {
        implementationPlatform("androidx-compose-bom", libs)
        implementation("androidx-activity-compose", libs)
        implementation("androidx-compose-ui", libs)
        implementation("androidx-compose-ui-graphics", libs)
        implementation("androidx-compose-ui-tooling-preview", libs)
        implementation("androidx-compose-material3", libs)
        implementation("androidx-compose-navigation", libs)
        implementation("androidx-compose-navigation-hilt", libs)
        implementation("kotlinx-collections-immutable", libs)
        add("implementation", "androidx.compose.material:material-icons-core:1.7.8")
        debugImplementation("androidx-compose-ui-tooling", libs)
        debugImplementation("androidx-compose-ui-test-manifest", libs)
    }

    extensions.configure<ComposeCompilerGradlePluginExtension> {
        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }
        fun Provider<*>.relativeToRootProject(dir: String) = map {
            @Suppress("UnstableApiUsage")
            isolated.rootProject.projectDirectory
                .dir("build")
                .dir(projectDir.toRelativeString(rootDir))
        }.map { it.dir(dir) }

        project.providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
            .relativeToRootProject("compose-metrics")
            .let(metricsDestination::set)

        project.providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()
            .relativeToRootProject("compose-reports")
            .let(reportsDestination::set)

        @Suppress("UnstableApiUsage")
        stabilityConfigurationFiles
            .add(isolated.rootProject.projectDirectory.file("compose_compiler_config.conf"))
    }
}