package com.example.time.plugins

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("io.gitlab.arturbosch.detekt")

        extensions.configure<DetektExtension> {
            config = files("${rootDir}/conf/detekt.yml")
            buildUponDefaultConfig = false
            parallel = true
            autoCorrect = false
            reports {
                xml.required.set(true)
                html.required.set(true)
                txt.required.set(false)
            }
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = "11"
        }
    }
}