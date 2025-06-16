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
    compileOnly(libs.spigot)
}

kotlin {
    jvmToolchain(21)
}