package observable.server

import kotlinx.serialization.json.*
import net.minecraft.SystemReport
import observable.Observable
import observable.util.Platform

fun Profiler.getDiagnostics(): JsonObject {
    val duration = System.currentTimeMillis() - startTime

    val systemReport = SystemReport()
    if (!ServerSettings.includeJvmArgs) {
        systemReport.setDetail("JVM Flags", "<REDACTED>")
    }

    return buildJsonObject {
        put("user", player?.gameProfile?.id?.toString())
        put("start", startTime)
        put("duration", duration)
        put("minecraftVersion", Platform.INSTANCE.getModVersion("minecraft"))
        put("modLoader", Platform.INSTANCE.getLoaderName())
        put("observableVersion", Platform.INSTANCE.getModVersion(Observable.MOD_ID))
        put(
            "additionalDiagnostics",
            buildJsonObject {
                put("System Report", systemReport.toLineSeparatedString())
                put(
                    "Mods",
                    Platform.INSTANCE.getModList().joinToString("\n") { (name, version) ->
                        "'$name' (version: $version)"
                    }
                )
            }
        )
    }
}
