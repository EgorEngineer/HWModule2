import java.io.File
import java.time.LocalDate

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

    val projectVersion = project.version.toString()
    val outputFile = layout.projectDirectory.file("build-info.txt")
    outputs.file(outputFile)

    doLast {
        val target: File = outputFile.asFile
        target.parentFile.mkdirs()
        target.writeText("Version: $projectVersion\n" +
                "Build date: ${LocalDate.now()}")
    }
}

tasks.named("build") {
    dependsOn(generateBuildInfo)
}