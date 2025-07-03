import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

detekt {
    config = files("${project.rootDir}/conf/detekt.yml")
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