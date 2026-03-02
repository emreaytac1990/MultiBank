plugins {
    alias(libs.plugins.multibank.android.library)
    alias(libs.plugins.multibank.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.emreaytac.datastore"
}

dependencies {
    api(libs.androidx.dataStore)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(project(":core:di"))
    api(project(":core:model"))
    api(project(":core:datastore-proto"))
}