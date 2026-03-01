plugins {
    alias(libs.plugins.multibank.android.library)
}

android {
    namespace = "com.emreaytac.domain"
}

dependencies {
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.core.ktx)
    // api(project(":core:data"))
    api(project(":core:model"))
    implementation(project(":core:websocket"))
}