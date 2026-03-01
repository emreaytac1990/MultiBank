plugins {
    alias(libs.plugins.multibank.android.library)
    alias(libs.plugins.multibank.android.library.compose)
}

android {
    namespace = "com.emreaytac.designsystem"
}

dependencies {
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}