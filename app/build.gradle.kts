plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "com.example.time"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.time"
        minSdk = 29
        //noinspection OldTargetApi
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        @Suppress("DEPRECATION")
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation (libs.androidx.foundation)
    ktlint (libs.ktlint.cli)
    implementation(libs.androidx.localbroadcastmanager)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.runtime.livedata)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.kotlinx.serialization.json)

    //Material&UI
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3.window.size.class1.android)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation (libs.accompanist.systemuicontroller)

    //Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    implementation(libs.mockito.core)
    implementation(libs.mockwebserver)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Splashscreen
    implementation(libs.androidx.core.splashscreen)

    //time4j
    implementation(libs.time4j.android)

    //Compose Navigation
    implementation (libs.androidx.navigation.compose)

    //Dagger Hilt
    implementation (libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    //Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    // Compose Coil&Glide
    implementation(libs.coil.compose)
    implementation(libs.glide)
    implementation(libs.glide.compose)

    //Datastore
    implementation (libs.androidx.datastore.preferences)

    //Paging 3
    implementation (libs.androidx.paging.runtime.ktx)
    implementation (libs.androidx.paging.compose)

    //Room
    implementation (libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)
    testImplementation(kotlin("test"))

}