import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */
project.version = "1.0.2"
plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.41"

    // Apply the application plugin to add support for building a CLI application.
    application
    id("com.github.johnrengelman.shadow") version("5.1.0")
    id ("com.bmuschko.docker-remote-api") version("5.2.0")
    id("com.bmuschko.docker-java-application") version "5.2.0"

}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))


    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0")
    implementation( "net.dean.jraw:JRAW:1.1.0")
    implementation( "org.postgresql:postgresql:42.2.8")
    implementation( "com.google.code.gson:gson:2.8.5")
    implementation(group= "org.slf4j", name= "slf4j-api", version= "1.7.28")
    implementation(group= "org.slf4j", name= "slf4j-simple", version= "1.7.28")

    implementation("io.github.microutils:kotlin-logging:1.5.9")
    implementation("com.uchuhimo:konf-core:0.20.0")
    implementation("com.uchuhimo:konf-yaml:0.20.0")
}

application {
    // Define the main class for the application
    mainClassName = "de.rtrx.a.MainKt"
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "1.8"

docker {
    registryCredentials {
        val GITHUB_USER: String by project
        val GITHUB_TOKEN: String by project

        username.set(GITHUB_USER)
        password.set(GITHUB_TOKEN)
        url.set("docker.pkg.github.com")
    }

    javaApplication {
        ports.set(listOf<Int>())
        baseImage.set("openjdk:8")
        maintainer.set("Artraxon a@rtrx.de")
        tag.set("artraxon/unexbot:${project.version}")
    }
}
