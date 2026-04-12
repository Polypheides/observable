package observable.net

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import net.fabricmc.api.EnvType
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import org.apache.logging.log4j.LogManager
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

class BetterChannel(val id: Identifier) {
    companion object {
        val LOGGER = LogManager.getLogger("ObservableNet")
    }

    val s2cLocation: Identifier = Identifier.fromNamespaceAndPath(id.namespace, id.path + "-s2c")
    val c2sLocation: Identifier = Identifier.fromNamespaceAndPath(id.namespace, id.path + "-c2s")

    data class SerializedPayload(val className: String, val data: ByteArray, val payloadId: Identifier) : CustomPacketPayload {
        companion object {
            fun type(id: Identifier) = CustomPacketPayload.Type<SerializedPayload>(id)
        }
        override fun type(): CustomPacketPayload.Type<SerializedPayload> = type(payloadId)
    }

    private fun createCodec(payloadId: Identifier) = object : StreamCodec<RegistryFriendlyByteBuf, SerializedPayload> {
        override fun decode(buf: RegistryFriendlyByteBuf): SerializedPayload {
            val name = buf.readUtf()
            val rawBytes = buf.readByteArray()
            val bytes = GZIPInputStream(ByteArrayInputStream(rawBytes)).use { it.readAllBytes() }
            return SerializedPayload(name, bytes, payloadId)
        }

        override fun encode(buf: RegistryFriendlyByteBuf, payload: SerializedPayload) {
            buf.writeUtf(payload.className)
            val bos = ByteArrayOutputStream()
            GZIPOutputStream(bos).use { it.write(payload.data) }
            buf.writeByteArray(bos.toByteArray())
        }
    }

    val handlers = mutableMapOf<String, (ByteArray, ServerPlayer?) -> Unit>()

    init {
        val s2cType = SerializedPayload.type(s2cLocation)
        val c2sType = SerializedPayload.type(c2sLocation)

        // Register S2C
        PayloadTypeRegistry.clientboundPlay().register(s2cType, createCodec(s2cLocation))
        if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(s2cType) { payload, context ->
                context.client().execute {
                    handlers[payload.className]?.invoke(payload.data, null)
                }
            }
        }

        // Register C2S
        PayloadTypeRegistry.serverboundPlay().register(c2sType, createCodec(c2sLocation))
        ServerPlayNetworking.registerGlobalReceiver(c2sType) { payload, context ->
            context.server().execute {
                handlers[payload.className]?.invoke(payload.data, context.player())
            }
        }
    }

    inline fun <reified T : Any> register(
        noinline consumer: (T, ServerPlayer?) -> Unit
    ) {
        handlers[T::class.java.name] = { buf, player -> consumer(Json.decodeFromString(serializer<T>(), String(buf)), player) }
        LOGGER.info("Registered ${T::class.java.name}")
    }

    inline fun <reified T : Any> sendToPlayers(players: Iterable<ServerPlayer>, msg: T) {
        val payload = SerializedPayload(T::class.java.name, Json.encodeToString(serializer<T>(), msg).toByteArray(), s2cLocation)
        players.forEach { player ->
            if (ServerPlayNetworking.canSend(player, s2cLocation)) {
                ServerPlayNetworking.send(player, payload)
            }
        }
    }

    inline fun <reified T : Any> sendToPlayer(player: ServerPlayer, msg: T) = sendToPlayers(listOf(player), msg)

    inline fun <reified T : Any> sendToServer(msg: T) {
        if (FabricLoader.getInstance().environmentType == EnvType.CLIENT) {
            val payload = SerializedPayload(T::class.java.name, Json.encodeToString(serializer<T>(), msg).toByteArray(), c2sLocation)
            ClientPlayNetworking.send(payload)
        }
    }
}
