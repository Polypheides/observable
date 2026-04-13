plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.20" apply false
    id("net.fabricmc.fabric-loom") version "1.16-SNAPSHOT" apply false
    id("net.neoforged.moddev") version "2.0.141" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.20" apply false
    java
}
tasks.jar {
    enabled = false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.nucleoid.xyz/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
        maven("https://thedarkcolour.github.io/KotlinForForge/")
        maven("https://maven.parchmentmc.org")
    }

    base {
        archivesName.set("${project.name}-${project.rootProject.extra["modId"]}")
    }

    version = project.rootProject.extra["modVersion"] as String
    group = project.rootProject.extra["mavenGroup"] as String

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }

    afterEvaluate {
        tasks.withType<org.gradle.api.tasks.bundling.Jar>().configureEach {
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
            // Only move platform jars to the root libs folder
            if (project.name != "common") {
                destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
            }
        }
    }
}
