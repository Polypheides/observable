package observable.util

import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
fun <T : Any> KClass<T>.serializer(): kotlinx.serialization.KSerializer<T> = 
    kotlinx.serialization.serializer(this.java) as kotlinx.serialization.KSerializer<T>
