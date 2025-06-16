plugins {
    kotlin("jvm").version(libs.versions.jvm)
}

repositories {
    mavenCentral()
    maven {
        name = "spigot-repo"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.stdlib)
    compileOnly(libs.spigot)
}

tasks {
    processResources { filesMatching("plugin.yml") { expand("version" to project.version) } }
}

kotlin {
    jvmToolchain(21)
}