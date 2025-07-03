plugins {
    id("org.jlleitschuh.gradle.ktlint")
}

ktlint {
    version.set("1.2.1")
    android.set(true)
    outputColorName.set("RED")
    verbose.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}