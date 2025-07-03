plugins {
    `kotlin-dsl`
}

group = "com.example.time.buildlogic"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.8")
    implementation("com.android.tools.build:gradle:8.11.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
    implementation("org.jetbrains.kotlin:kotlin-serialization:2.2.0")
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "com.example.time.plugins.android-application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("detekt") {
            id = "com.example.time.plugins.convention.detekt"
            implementationClass = "DetektConventionPlugin"
        }
        register("ktlint") {
            id = "com.example.time.plugins.ktlint"
            implementationClass = "KtlintConventionPlugin"
        }
    }
}
