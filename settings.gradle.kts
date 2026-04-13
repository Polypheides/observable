pluginManagement {
    repositories {
        mavenCentral()
        maven(url = "https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        gradlePluginPortal()
    }
}


rootProject.name = "observable"

include("common")
include("fabric")
include("neoforge")
