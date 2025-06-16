plugins {
    kotlin("jvm").version(libs.versions.jvm)
    id("com.gradleup.shadow") version "8.3.3"
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
    processResources { 
        filesMatching("paper-plugin.yml") { 
            expand("version" to project.version) 
        } 
    }
    
    shadowJar {
        archiveBaseName.set("TradeCycle")
        archiveVersion.set(project.version.toString())
        archiveClassifier.set("paper")
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