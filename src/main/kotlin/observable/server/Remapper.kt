package observable.server

import net.fabricmc.loader.api.FabricLoader
import org.objectweb.asm.Type

object Remapper {
    private val resolver by lazy { FabricLoader.getInstance().mappingResolver }
    private val methodCache = mutableMapOf<String, String>()

    fun init() {
        // Native initialization: no more URLs to fetch
    }

    fun transform(map: TraceMap) {
        val className = map.className
        val methodName = map.methodName

        // 1. Map Class Name
        try {
            val mappedClass = resolver.mapClassName("intermediary", className.replace(".", "/")).replace("/", ".")
            if (mappedClass != className) {
                map.className = mappedClass
            }
        } catch (e: Exception) {
            // Not a Minecraft class or no mapping found
        }

        // 2. Map Method Name
        if (methodName != "null") {
            val cacheKey = "$className#$methodName"
            methodCache[cacheKey]?.let {
                map.methodName = it
                return
            }

            try {
                // We use reflection to find the descriptor because StackTraceElement doesn't provide it
                val clazz = Class.forName(className)
                val internalName = className.replace(".", "/")
                
                // Find potential mappings for this method name
                val methods = clazz.declaredMethods.filter { it.name == methodName }
                if (methods.isNotEmpty()) {
                    // Try to map the first one found (usually sufficient for Intermediary)
                    val descriptor = Type.getMethodDescriptor(methods[0])
                    val mappedMethod = resolver.mapMethodName("intermediary", internalName, methodName, descriptor)
                    
                    if (mappedMethod != methodName) {
                        map.methodName = mappedMethod
                        methodCache[cacheKey] = mappedMethod
                    }
                }
            } catch (e: Exception) {
                // Silent fail for non-obfuscated or non-existent classes
            }
        }
    }
}
