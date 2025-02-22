plugins {
    kotlin("jvm") version "2.0.2.1"
}

group = "s42.site"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

tasks {
    processResources { filesMatching("plugin.yml") { expand("version" to project.version) } }
    jar { archiveFileName.set("${rootProject.name}-${project.name}-${project.version}.jar") }
}
kotlin {
    jvmToolchain(21)
}