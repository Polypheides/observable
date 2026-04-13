plugins {
    id("net.fabricmc.fabric-loom")
    kotlin("plugin.serialization") version "2.3.20"
}

dependencies {
    minecraft("com.mojang:minecraft:${project.rootProject.extra["minecraft_version"]}")

    implementation("net.fabricmc:fabric-loader:${project.rootProject.extra["fabric_loader_version"]}")
    implementation("net.fabricmc.fabric-api:fabric-api:${project.rootProject.extra["fabric_api_version"]}")
    implementation("net.fabricmc:fabric-language-kotlin:1.13.10+kotlin.2.3.20")

    implementation(project(":common"))

    include(project(":common"))
}

tasks {
    processResources {
        inputs.property("id", project.rootProject.extra["modId"])
        inputs.property("name", project.rootProject.extra["modName"])
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(
                mapOf(
                    "id" to project.rootProject.extra["modId"],
                    "version" to project.version,
                    "name" to project.rootProject.extra["modName"],
                )
            )
        }
    }
}
tasks.register<Copy>("copyToFabric") {
    dependsOn("jar")
    from(tasks.jar.flatMap { it.archiveFile })
    into("D:/Program Files (x86)/PrismLauncher-Windows-MSVC-Portable-8.2/instances\26.1.2/.minecraft/mods")
}
