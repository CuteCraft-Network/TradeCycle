plugins {
    kotlin("jvm").version(libs.versions.jvm)
    id("com.gradleup.shadow") version "8.3.3"
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
    processResources { 
        filesMatching("plugin.yml") { 
            expand("version" to project.version) 
        } 
    }
    
    shadowJar {
        archiveBaseName.set("TradeCycle")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("spigot")
        destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
        
        relocate("kotlin", "net.cutecraft.tradecycle.libs.kotlin")
    }
    
    jar {
        enabled = false
    }
    
    build {
        dependsOn(shadowJar)
    }
}

kotlin {
    jvmToolchain(21)
}