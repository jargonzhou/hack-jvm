import org.gradle.kotlin.dsl.version

plugins {
    id("buildlogic.kotlin-library-conventions")

    // https://kotlinlang.org/docs/dokka-introduction.html
    id("org.jetbrains.dokka") version "2.2.0"
    // id("org.jetbrains.dokka-javadoc") version "2.2.0"

    // https://docs.gradle.org/current/userguide/antlr_plugin.html
    antlr
}

dependencies {
    implementation(kotlin("reflect"))
    antlr("org.antlr:antlr4:4.13.2") // ANTLR

    // https://github.com/kotlin/kotlinx.html
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.12.0")

    // https://github.com/Kotlin/kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
    // https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-test/
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.11.0")
    // https://github.com/cashapp/turbine
    testImplementation("app.cash.turbine:turbine:1.2.1")
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-visitor", "-long-messages")
    outputDirectory = file("src/main/java")
}