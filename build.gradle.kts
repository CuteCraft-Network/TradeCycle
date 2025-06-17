import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin

plugins {
    kotlin("jvm").version(libs.versions.jvm)
    alias(libs.plugins.shadow)
    id("java")
}

group = "net.cutecraft"
version = "1.5.0"

repositories { mavenCentral() }

val stdlib: String = libs.stdlib.get().toString()
subprojects {
    version = rootProject.version
    apply {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        plugin<JavaPlugin>()
        plugin<JavaLibraryPlugin>()
        plugin<ShadowPlugin>()
    }
    repositories {
        mavenCentral()
        maven(uri("https://repo.papermc.io/repository/maven-public/"))
        maven(uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots"))
    }
    kotlin {
        jvmToolchain(21)
    }
    if (project.name == "core") {
        return@subprojects
    }
    tasks {
        jar { enabled = false }
        build { dependsOn(shadowJar) }
        shadowJar {
            archiveBaseName.set(rootProject.name)
            archiveVersion.set(project.version.toString())
            archiveClassifier.set(project.name)
            destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
            relocate("kotlin", "net.cutecraft.libs.kotlin")
            minimize()
        }
    }
    dependencies {
        implementation(project(":core"))
        implementation(stdlib)
    }
}

tasks.jar { enabled = false }