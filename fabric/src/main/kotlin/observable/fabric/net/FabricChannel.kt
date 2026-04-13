package observable.fabric.net

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import observable.net.Channel
import observable.util.Compression
import observable.util.serializer
import kotlin.reflect.KClass

class FabricChannel(override val id: Identifier) : Channel {

    private val packetType = CustomPacketPayload.Type<GzipPayload>(id)
    private val codec: StreamCodec<RegistryFriendlyByteBuf, GzipPayload> = CustomPacketPayload.codec(GzipPayload.ID_CODEC, GzipPayload.CODEC)

    init {
        PayloadTypeRegistry.playS2C().register(packetType, codec)
        PayloadTypeRegistry.playC2B().register(packetType, codec)
    }

    class GzipPayload(val jsonData: String) : CustomPacketPayload {
        companion object {
            val ID_CODEC: CustomPacketPayload.Type<GzipPayload> = CustomPacketPayload.Type(Identifier.fromNamespaceAndPath("observable", "main"))
            val CODEC: StreamCodec<RegistryFriendlyByteBuf, GzipPayload> = StreamCodec.of(
                { buf, payload -> 
                    val bytes = Compression.gzip(payload.jsonData)
                    buf.writeInt(bytes.size)
                    buf.writeBytes(bytes)
                },
                { buf -> 
                    val size = buf.readInt()
                    val bytes = ByteArray(size)
                    buf.readBytes(bytes)
                    GzipPayload(Compression.ungzip(bytes))
                }
            )
        }
        override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = ID_CODEC
    }

    override fun <T : Any> register(type: KClass<T>, consumer: (T, ServerPlayer?) -> Unit) {
        val typeName = type.qualifiedName ?: return

        ServerPlayNetworking.registerGlobalReceiver(packetType) { payload, context ->
            val json = payload.jsonData
            if (json.startsWith("{\"type\":\"$typeName\"")) {
                val data = Json.decodeFromString<Envelope>(json).data
                val obj = Json.decodeFromString(type.serializer(), data)
                context.server().execute {
                    consumer(obj, context.player())
                }
            }
        }

        ClientPlayNetworking.registerGlobalReceiver(packetType) { payload, context ->
            val json = payload.jsonData
            if (json.startsWith("{\"type\":\"$typeName\"")) {
                val data = Json.decodeFromString<Envelope>(json).data
                val obj = Json.decodeFromString(type.serializer(), data)
                context.client().execute {
                    consumer(obj, null)
                }
            }
        }
    }

    override fun <T : Any> sendToPlayers(players: Iterable<ServerPlayer>, type: KClass<T>, msg: T) {
        val envelope = Envelope(type.qualifiedName!!, Json.encodeToString(type.serializer(), msg))
        val payload = GzipPayload(Json.encodeToString(envelope))
        players.forEach { ServerPlayNetworking.send(it, payload) }
    }

    override fun <T : Any> sendToServer(type: KClass<T>, msg: T) {
        val envelope = Envelope(type.qualifiedName!!, Json.encodeToString(type.serializer(), msg))
        val payload = GzipPayload(Json.encodeToString(envelope))
        ClientPlayNetworking.send(payload)
    }

    @kotlinx.serialization.Serializable
    data class Envelope(val type: String, val data: String)
}
