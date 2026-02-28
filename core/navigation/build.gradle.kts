plugins {
    alias(libs.plugins.multibank.jvm.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies{
    implementation(libs.kotlinx.serialization.json)
}