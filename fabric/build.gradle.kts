plugins {
    id("net.fabricmc.fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.rootProject.extra["minecraft_version"]}")

    mappings(loom.officialMojangMappings())

    "modImplementation"("net.fabricmc:fabric-loader:${project.rootProject.extra["fabric_loader_version"]}")
    "modImplementation"("net.fabricmc.fabric-api:fabric-api:${project.rootProject.extra["fabric_api_version"]}")
    "modImplementation"("net.fabricmc:fabric-language-kotlin:1.13.10+kotlin.2.3.20")

    implementation(project(":common"))
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
