package observable.server

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import observable.util.Platform
import java.nio.file.Path
import kotlin.io.path.*

val configFile: Path by lazy { Platform.INSTANCE.getConfigDir().resolve("observable.json") }
var ServerSettings = loadSettings()

@Serializable
data class ServerSettingsData(
    var traceInterval: Int = 3,
    var deviation: Int = 1,
    var notifyInterval: Int = 120 * 60 * 1000,
    var allPlayersAllowed: Boolean = false,
    var allowedPlayers: MutableSet<String> = mutableSetOf(),
    var includeJvmArgs: Boolean = true,
    var uploadURL: String = "https://observable.tas.sh/v1/add"
) {
    fun sync() = configFile.writeText(Json.encodeToString(this))
}

fun loadSettings(): ServerSettingsData {
    if (!configFile.exists()) {
        val settings = ServerSettingsData()
        configFile.writeText(Json.encodeToString(settings))
        return settings
    }
    return try {
        Json.decodeFromString(configFile.readText())
    } catch (e: Exception) {
        ServerSettingsData()
    }
}

fun resetSettings() {
    configFile.deleteIfExists()
    ServerSettings = loadSettings()
}
