package observable.server

import kotlinx.serialization.json.*
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.SystemReport
import observable.Observable

fun Profiler.getDiagnostics(): JsonObject {
    val duration = System.currentTimeMillis() - startTime

    val systemReport = SystemReport()
    if (!ServerSettings.includeJvmArgs) {
        systemReport.setDetail("JVM Flags", "<REDACTED>")
    }

    val fabricLoader = FabricLoader.getInstance()

    return buildJsonObject {
        put("user", player?.gameProfile?.id?.toString())
        put("start", startTime)
        put("duration", duration)
        put("minecraftVersion", fabricLoader.getModContainer("minecraft").get().metadata.version.friendlyString)
        put("modLoader", "FABRIC")
        put("observableVersion", fabricLoader.getModContainer(Observable.MOD_ID).get().metadata.version.friendlyString)
        put(
            "additionalDiagnostics",
            buildJsonObject {
                put("System Report", systemReport.toLineSeparatedString())
                put(
                    "Mods",
                    fabricLoader.allMods.joinToString("\n") { mod ->
                        "'${mod.metadata.name}' (version: ${mod.metadata.version.friendlyString})"
                    }
                )
            }
        )
    }
}
