package com.example.time.plugins

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.plugin.KotlinAndroidPluginWrapper

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(AppPlugin::class.java)
        pluginManager.apply(KotlinAndroidPluginWrapper::class.java)
        pluginManager.apply("com.google.dagger.hilt.android")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

        extensions.configure<BaseAppModuleExtension> {
            namespace = "com.example.time"
            compileSdk = 34

            defaultConfig.apply {
                applicationId = "com.example.app"
                minSdk = 26
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"
            }

            buildTypes {
                getByName("release") {
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

            buildFeatures.compose = true

            composeOptions {
                kotlinCompilerExtensionVersion = "1.5.13"
            }
        }
    }
}