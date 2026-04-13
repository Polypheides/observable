plugins {
    id("net.neoforged.moddev")
    kotlin("jvm")
    kotlin("plugin.serialization")
}

neoForge {
    version = "26.1.2.7-beta"

    // No parchment needed as 26.1+ is not obfuscated!

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", project.rootProject.extra["modId"] as String)
        }
        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", project.rootProject.extra["modId"] as String)
        }
    }

    mods {
        create(project.rootProject.extra["modId"] as String) {
            sourceSet(project.sourceSets.main.get())
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation(kotlin("reflect"))
}

tasks.jar {
    from(project(":common").sourceSets.main.get().output)
}

tasks.register<Copy>("copyToNeo") {
    dependsOn("jar")
    from(tasks.jar.flatMap { it.archiveFile })
    into("D:/Program Files (x86)/PrismLauncher-Windows-MSVC-Portable-8.2/instances/26.1.2(1)/.minecraft/mods")
}
