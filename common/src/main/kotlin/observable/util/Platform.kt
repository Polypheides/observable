package observable.util

import observable.net.Channel
import net.minecraft.resources.Identifier
import java.nio.file.Path

interface Platform {
    fun createChannel(id: Identifier): Channel
    fun getConfigDir(): Path
    fun isDevelopment(): Boolean
    
    fun remapClassName(className: String): String = className
    fun remapMethodName(className: String, methodName: String, descriptor: String): String = methodName

    fun getModVersion(modId: String): String
    fun getModList(): List<Pair<String, String>>
    fun getLoaderName(): String

    companion object {
        lateinit var INSTANCE: Platform
    }
}
