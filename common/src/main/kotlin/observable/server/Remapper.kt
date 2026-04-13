package observable.server

import org.objectweb.asm.Type
import observable.util.Platform

object Remapper {
    private val methodCache = mutableMapOf<String, String>()

    fun init() {
    }

    fun transform(map: TraceMap) {
        val className = map.className
        val methodName = map.methodName

        // 1. Map Class Name
        try {
            val mappedClass = Platform.INSTANCE.remapClassName(className)
            if (mappedClass != className) {
                map.className = mappedClass
            }
        } catch (e: Exception) {
            // No mapping found
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
                
                // Find potential mappings for this method name
                val methods = clazz.declaredMethods.filter { it.name == methodName }
                if (methods.isNotEmpty()) {
                    // Try to map the first one found
                    val descriptor = Type.getMethodDescriptor(methods[0])
                    val mappedMethod = Platform.INSTANCE.remapMethodName(className, methodName, descriptor)
                    
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
