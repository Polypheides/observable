package observable.neoforge.net

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.protocol.common.custom.CustomPacketPayload
import net.minecraft.resources.Identifier
import net.minecraft.server.level.ServerPlayer
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.registration.PayloadRegistrar
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.handling.IPayloadContext
import observable.net.Channel
import observable.util.Compression
import observable.util.serializer
import observable.neoforge.ObservableNeo
import kotlin.reflect.KClass

@kotlinx.serialization.Serializable
data class Envelope(val type: String, val data: String)

class NeoChannel(override val id: Identifier) : Channel {

    private val packetType = CustomPacketPayload.Type<NeoGzipPayload>(id)
    private val consumers = mutableMapOf<String, (String, ServerPlayer?) -> Unit>()

    init {
        // Explicitly called from ObservableNeo
    }

    fun onRegisterPayloads(event: RegisterPayloadHandlersEvent) {
        val registrar: PayloadRegistrar = event.registrar(id.namespace)
        
        // In this version of NeoForge, playBidirectional requires two handlers to be truly bidirectional.
        registrar.playBidirectional(
            packetType,
            NeoGzipPayload.CODEC,
            { payload: NeoGzipPayload, context: IPayloadContext -> handle(payload, context) }, // Server side
            { payload: NeoGzipPayload, context: IPayloadContext -> handle(payload, context) }  // Client side
        )
    }

    private fun handle(payload: NeoGzipPayload, context: IPayloadContext) {
        val json = payload.jsonData
        val envelope = Json.decodeFromString<Envelope>(json)
        val player = if (context.flow().isServerbound) context.player() as? ServerPlayer else null
        consumers[envelope.type]?.invoke(envelope.data, player)
    }

    class NeoGzipPayload(val jsonData: String) : CustomPacketPayload {
        companion object {
            val ID: CustomPacketPayload.Type<NeoGzipPayload> = CustomPacketPayload.Type(Identifier.fromNamespaceAndPath("observable", "main"))
            val CODEC: StreamCodec<RegistryFriendlyByteBuf, NeoGzipPayload> = StreamCodec.of(
                { buf, payload -> 
                    val bytes = Compression.gzip(payload.jsonData)
                    buf.writeInt(bytes.size)
                    buf.writeBytes(bytes)
                },
                { buf -> 
                    val size = buf.readInt()
                    val bytes = ByteArray(size)
                    buf.readBytes(bytes)
                    NeoGzipPayload(Compression.ungzip(bytes))
                }
            )
        }
        override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = ID
    }

    override fun <T : Any> register(type: KClass<T>, consumer: (T, ServerPlayer?) -> Unit) {
        val typeName = type.qualifiedName ?: return
        consumers[typeName] = { data, player ->
            val obj = Json.decodeFromString(type.serializer(), data)
            consumer(obj, player)
        }
    }

    override fun <T : Any> sendToPlayers(players: Iterable<ServerPlayer>, type: KClass<T>, msg: T) {
        val envelope = Envelope(type.qualifiedName!!, Json.encodeToString(type.serializer(), msg))
        val payload = NeoGzipPayload(Json.encodeToString(envelope))
        players.forEach { PacketDistributor.sendToPlayer(it, payload) }
    }

    override fun <T : Any> sendToServer(type: KClass<T>, msg: T) {
        val envelope = Envelope(type.qualifiedName!!, Json.encodeToString(type.serializer(), msg))
        val payload = NeoGzipPayload(Json.encodeToString(envelope))
        net.neoforged.neoforge.client.network.ClientPacketDistributor.sendToServer(payload)
    }
}
