plugins {
    alias(libs.plugins.multibank.android.library)
    alias(libs.plugins.multibank.hilt)
}

android {
    namespace = "com.emreaytac.websocket"
}

dependencies {
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.websockets)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)

    implementation(libs.kotlinx.coroutines.android)
}