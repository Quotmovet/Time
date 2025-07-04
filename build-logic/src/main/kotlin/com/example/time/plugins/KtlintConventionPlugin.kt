package com.example.time.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.gradle.kotlin.dsl.configure

class KtlintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jlleitschuh.gradle.ktlint")

        extensions.configure<KtlintExtension> {
            version.set("1.2.1")
            android.set(true)
            outputColorName.set("RED")
            verbose.set(true)
            reporters {
                reporter(ReporterType.PLAIN)
                reporter(ReporterType.CHECKSTYLE)
            }
            filter {
                exclude("**/generated/**")
                include("**/kotlin/**")
            }
        }
    }
}