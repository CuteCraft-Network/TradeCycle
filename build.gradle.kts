plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

group = "s42.site"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        filesMatching("plugin.yml") { expand("version" to project.version) }
    }
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        archiveFileName.set("${rootProject.name}-${project.name}-${project.version}.jar")
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    }
}
kotlin {
    jvmToolchain(21)
}