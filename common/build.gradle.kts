plugins {
    id("net.fabricmc.fabric-loom")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.rootProject.extra["minecraft_version"]}")
    
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
    implementation("org.joml:joml:1.10.5")
    implementation("org.ow2.asm:asm:9.7.1")
    
    implementation("org.spongepowered:mixin:0.8.5")
}
