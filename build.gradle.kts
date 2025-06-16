plugins {
    kotlin("jvm").version(libs.versions.jvm)
    id("com.gradleup.shadow") version "8.3.3" apply false
}

group = "net.cutecraft.tradecycle"
version = "1.5.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(libs.stdlib)
    compileOnly(libs.paper)
}

kotlin {
    jvmToolchain(21)
}

subprojects {
    apply(plugin = "java")
    
    version = rootProject.version
}