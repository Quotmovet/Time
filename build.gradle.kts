// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
    id ("org.jetbrains.kotlin.plugin.serialization") version "2.2.0"
    id("com.google.dagger.hilt.android") version "2.56.2" apply false
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

detekt {
    @Suppress("DEPRECATION")
    config = files("$rootDir/conf/detekt.yml")
    buildUponDefaultConfig = false
    parallel = true
    autoCorrect = false
    @Suppress("DEPRECATION")
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(false)
    }
}