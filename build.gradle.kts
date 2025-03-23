plugins {
    kotlin("jvm").version(libs.versions.jvm)
    alias(libs.plugins.shadow)
}

group = "tr.s42.tradecycler"
version = "1.4"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation(libs.stdlib)
    implementation(libs.boosted.yaml)
    compileOnly(libs.paper)
}

tasks {
    processResources { filesMatching("paper-plugin.yml") { expand("version" to project.version) } }
    jar { enabled = false }
    build { dependsOn(shadowJar) }
    shadowJar {
        archiveClassifier.set(null as String?)
        archiveFileName.set("${project.name}-${project.version}.jar")
        relocate("dev.dejvokep.boostedyaml", "tr.s42.tradecycler")
        exclude("META-INF/**")
        minimize()
    }
}

kotlin {
    jvmToolchain(21)
}
