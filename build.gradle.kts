import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.20"
    id("net.fabricmc.fabric-loom") version "1.15.+"
    kotlin("plugin.serialization") version "2.3.20"
    `maven-publish`
}

val modId: String by project
val modName: String by project
val modVersion: String by project
val mavenGroup: String by project

base.archivesName.set(modId)
version = modVersion
group = mavenGroup

repositories {
    mavenCentral()
    maven("https://maven.fabricmc.net/")
    maven("https://maven.nucleoid.xyz/")
    maven("https://repo.spongepowered.org/repository/maven-public/")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.extra["minecraft_version"]}")

    implementation("net.fabricmc:fabric-loader:${project.extra["fabric_loader_version"]}")
    implementation("net.fabricmc.fabric-api:fabric-api:${project.extra["fabric_api_version"]}")
    implementation("net.fabricmc:fabric-language-kotlin:1.13.10+kotlin.2.3.20")
}

tasks {
    processResources {
        inputs.property("id", modId)
        inputs.property("name", modName)
        inputs.property("version", version)

        filesMatching("fabric.mod.json") {
            expand(
                mapOf(
                    "id" to modId,
                    "version" to version,
                    "name" to modName,
                )
            )
        }
    }

    jar {
        archiveFileName.set("${modId}-${modVersion}.jar")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
    withSourcesJar()
}

kotlin {
    jvmToolchain(25)

    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_25)
    }
}

tasks.withType<org.gradle.api.tasks.bundling.Jar>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

/* KEEP YOUR TASK */
tasks.register<Copy>("copyToPrism") {
    dependsOn("jar")
    from(tasks.jar.flatMap { it.archiveFile })
    into("D:/Program Files (x86)/PrismLauncher-Windows-MSVC-Portable-8.2/instances/26.1.2/.minecraft/mods")
}
