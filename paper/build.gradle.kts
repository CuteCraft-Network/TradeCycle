plugins {
    kotlin("jvm").version(libs.versions.jvm)
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(libs.stdlib)
    compileOnly(libs.paper)
}

tasks {
    processResources { filesMatching("paper-plugin.yml") { expand("version" to project.version) } }
}

kotlin {
    jvmToolchain(21)
}