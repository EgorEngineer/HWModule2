import java.io.File
import java.time.LocalDate
import org.gradle.api.plugins.JavaPluginExtension

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

group = "org.example"
version = "1.0-SNAPSHOT"
description = "Project.Module2"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

val generateBuildInfo = tasks.register("generateBuildInfo") {
    group = "build"
    description = "Генерирует build-info.txt с версией проекта и датой сборки"

    val projectVersion = project.version.toString()
    val outputFile = layout.buildDirectory.file("build-info.txt")
    outputs.file(outputFile)

    doLast {
        val target: File = outputFile.get().asFile
        target.parentFile.mkdirs()
        target.writeText("Version: $projectVersion\nBuild date: ${LocalDate.now()}")
    }
}

tasks.named("build") {
    dependsOn(generateBuildInfo)
}