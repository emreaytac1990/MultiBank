plugins {
    alias(libs.plugins.multibank.android.library)
    alias(libs.plugins.multibank.android.library.compose)
}

android {
    namespace = "com.emreaytac.stock"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(project(":core:navigation"))
}