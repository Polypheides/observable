package observable.net

import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import kotlin.reflect.KClass

interface Channel {
    val id: Identifier

    fun <T : Any> register(type: KClass<T>, consumer: (T, ServerPlayer?) -> Unit)
    fun <T : Any> sendToPlayers(players: Iterable<ServerPlayer>, type: KClass<T>, msg: T)
    fun <T : Any> sendToServer(type: KClass<T>, msg: T)

    fun <T : Any> sendToPlayer(player: ServerPlayer, type: KClass<T>, msg: T) {
        sendToPlayers(listOf(player), type, msg)
    }
}

// Inline extension functions to allow reified syntax
inline fun <reified T : Any> Channel.register(noinline consumer: (T, ServerPlayer?) -> Unit) {
    register(T::class, consumer)
}

inline fun <reified T : Any> Channel.sendToPlayers(players: Iterable<ServerPlayer>, msg: T) {
    sendToPlayers(players, T::class, msg)
}

inline fun <reified T : Any> Channel.sendToServer(msg: T) {
    sendToServer(T::class, msg)
}

inline fun <reified T : Any> Channel.sendToPlayer(player: ServerPlayer, msg: T) {
    sendToPlayer(player, T::class, msg)
}
