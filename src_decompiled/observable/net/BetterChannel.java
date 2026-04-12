/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.MagicApiIntrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlinx.serialization.DeserializationStrategy
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.SerializersKt
 *  kotlinx.serialization.json.Json
 *  net.fabricmc.api.EnvType
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
 *  net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking$Context
 *  net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking$Context
 *  net.fabricmc.loader.api.FabricLoader
 *  net.minecraft.network.RegistryFriendlyByteBuf
 *  net.minecraft.network.codec.StreamCodec
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload$Type
 *  net.minecraft.resources.Identifier
 *  net.minecraft.server.level.ServerPlayer
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import observable.net.BetterChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000=\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u001c\n\u0002\b\n*\u0001\r\u0018\u0000 &2\u00020\u0001:\u0002&'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0002\u00a2\u0006\u0002\u0010\u000fJ6\u0010\u0019\u001a\u00020\u0016\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u00012\u001c\b\b\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u0002H\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00160\u0013H\u0086\b\u00f8\u0001\u0000J0\u0010\u001c\u001a\u00020\u0016\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00150\u001e2\u0006\u0010\u001f\u001a\u0002H\u001aH\u0086\b\u00a2\u0006\u0002\u0010 J*\u0010!\u001a\u00020\u0016\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u00012\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u0002H\u001aH\u0086\b\u00a2\u0006\u0002\u0010#J\"\u0010$\u001a\u00020\u0016\"\n\b\u0000\u0010\u001a\u0018\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u0002H\u001aH\u0086\b\u00a2\u0006\u0002\u0010%R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R1\u0010\u0010\u001a\"\u0012\u0004\u0012\u00020\u0012\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0012\u0004\u0012\u00020\u00160\u00130\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00a8\u0006("}, d2={"Lobservable/net/BetterChannel;", "", "id", "Lnet/minecraft/resources/Identifier;", "<init>", "(Lnet/minecraft/resources/Identifier;)V", "getId", "()Lnet/minecraft/resources/Identifier;", "s2cLocation", "getS2cLocation", "c2sLocation", "getC2sLocation", "createCodec", "observable/net/BetterChannel$createCodec$1", "payloadId", "(Lnet/minecraft/resources/Identifier;)Lobservable/net/BetterChannel$createCodec$1;", "handlers", "", "", "Lkotlin/Function2;", "", "Lnet/minecraft/server/level/ServerPlayer;", "", "getHandlers", "()Ljava/util/Map;", "register", "T", "consumer", "sendToPlayers", "players", "", "msg", "(Ljava/lang/Iterable;Ljava/lang/Object;)V", "sendToPlayer", "player", "(Lnet/minecraft/server/level/ServerPlayer;Ljava/lang/Object;)V", "sendToServer", "(Ljava/lang/Object;)V", "Companion", "SerializedPayload", "observable"})
@SourceDebugExtension(value={"SMAP\nBetterChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BetterChannel.kt\nobservable/net/BetterChannel\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,102:1\n85#1,2:105\n87#1,4:108\n91#1:113\n1915#2,2:103\n1915#2:107\n1916#2:112\n*S KotlinDebug\n*F\n+ 1 BetterChannel.kt\nobservable/net/BetterChannel\n*L\n93#1:105,2\n93#1:108,4\n93#1:113\n86#1:103,2\n93#1:107\n93#1:112\n*E\n"})
public final class BetterChannel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Identifier id;
    @NotNull
    private final Identifier s2cLocation;
    @NotNull
    private final Identifier c2sLocation;
    @NotNull
    private final Map<String, Function2<byte[], ServerPlayer, Unit>> handlers;
    private static final Logger LOGGER = LogManager.getLogger((String)"ObservableNet");

    public BetterChannel(@NotNull Identifier id) {
        Intrinsics.checkNotNullParameter((Object)id, (String)"id");
        this.id = id;
        Identifier identifier = Identifier.fromNamespaceAndPath((String)this.id.getNamespace(), (String)(this.id.getPath() + "-s2c"));
        Intrinsics.checkNotNullExpressionValue((Object)identifier, (String)"fromNamespaceAndPath(...)");
        this.s2cLocation = identifier;
        Identifier identifier2 = Identifier.fromNamespaceAndPath((String)this.id.getNamespace(), (String)(this.id.getPath() + "-c2s"));
        Intrinsics.checkNotNullExpressionValue((Object)identifier2, (String)"fromNamespaceAndPath(...)");
        this.c2sLocation = identifier2;
        this.handlers = new LinkedHashMap();
        CustomPacketPayload.Type<SerializedPayload> s2cType = SerializedPayload.Companion.type(this.s2cLocation);
        CustomPacketPayload.Type<SerializedPayload> c2sType = SerializedPayload.Companion.type(this.c2sLocation);
        PayloadTypeRegistry.clientboundPlay().register(s2cType, (StreamCodec)this.createCodec(this.s2cLocation));
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ClientPlayNetworking.registerGlobalReceiver(s2cType, (arg_0, arg_1) -> BetterChannel._init_$lambda$0(this, arg_0, arg_1));
        }
        PayloadTypeRegistry.serverboundPlay().register(c2sType, (StreamCodec)this.createCodec(this.c2sLocation));
        ServerPlayNetworking.registerGlobalReceiver(c2sType, (arg_0, arg_1) -> BetterChannel._init_$lambda$1(this, arg_0, arg_1));
    }

    @NotNull
    public final Identifier getId() {
        return this.id;
    }

    @NotNull
    public final Identifier getS2cLocation() {
        return this.s2cLocation;
    }

    @NotNull
    public final Identifier getC2sLocation() {
        return this.c2sLocation;
    }

    private final createCodec.1 createCodec(Identifier payloadId) {
        return new StreamCodec<RegistryFriendlyByteBuf, SerializedPayload>(payloadId){
            final /* synthetic */ Identifier $payloadId;
            {
                this.$payloadId = $payloadId;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public SerializedPayload decode(RegistryFriendlyByteBuf buf) {
                byte[] byArray;
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                String string = buf.readUtf();
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"readUtf(...)");
                String name = string;
                byte[] byArray2 = buf.readByteArray();
                Intrinsics.checkNotNullExpressionValue((Object)byArray2, (String)"readByteArray(...)");
                byte[] rawBytes = byArray2;
                Closeable closeable = new GZIPInputStream(new ByteArrayInputStream(rawBytes));
                Throwable throwable = null;
                try {
                    GZIPInputStream it = (GZIPInputStream)closeable;
                    boolean bl = false;
                    byArray = it.readAllBytes();
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                }
                byte[] bytes = byArray;
                Intrinsics.checkNotNull((Object)bytes);
                return new SerializedPayload(name, bytes, this.$payloadId);
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public void encode(RegistryFriendlyByteBuf buf, SerializedPayload payload) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                Intrinsics.checkNotNullParameter((Object)payload, (String)"payload");
                buf.writeUtf(payload.getClassName());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                Closeable closeable = new GZIPOutputStream(bos);
                Throwable throwable = null;
                try {
                    GZIPOutputStream it = (GZIPOutputStream)closeable;
                    boolean bl = false;
                    it.write(payload.getData());
                    Unit unit = Unit.INSTANCE;
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    CloseableKt.closeFinally((Closeable)closeable, (Throwable)throwable);
                }
                buf.writeByteArray(bos.toByteArray());
            }
        };
    }

    @NotNull
    public final Map<String, Function2<byte[], ServerPlayer, Unit>> getHandlers() {
        return this.handlers;
    }

    public final /* synthetic */ <T> void register(Function2<? super T, ? super ServerPlayer, Unit> consumer) {
        Intrinsics.checkNotNullParameter(consumer, (String)"consumer");
        boolean $i$f$register = false;
        Map<String, Function2<byte[], ServerPlayer, Unit>> map = this.getHandlers();
        Intrinsics.reifiedOperationMarker((int)4, (String)"T");
        String string = Object.class.getName();
        Intrinsics.needClassReification();
        map.put(string, new Function2<byte[], ServerPlayer, Unit>(consumer){
            final /* synthetic */ Function2<T, ServerPlayer, Unit> $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                Intrinsics.reifiedOperationMarker((int)6, (String)"T");
                MagicApiIntrinsics.voidMagicApiCall((Object)"kotlinx.serialization.serializer.simple");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)SerializersKt.serializer(null), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        Logger logger = Companion.getLOGGER();
        Intrinsics.reifiedOperationMarker((int)4, (String)"T");
        logger.info("Registered " + Object.class.getName());
    }

    public final /* synthetic */ <T> void sendToPlayers(Iterable<? extends ServerPlayer> players, T msg) {
        Intrinsics.checkNotNullParameter(players, (String)"players");
        Intrinsics.checkNotNullParameter(msg, (String)"msg");
        boolean $i$f$sendToPlayers = false;
        Intrinsics.reifiedOperationMarker((int)4, (String)"T");
        String string = Object.class.getName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
        Intrinsics.reifiedOperationMarker((int)6, (String)"T");
        MagicApiIntrinsics.voidMagicApiCall((Object)"kotlinx.serialization.serializer.simple");
        String string2 = Json.Default.encodeToString((SerializationStrategy)SerializersKt.serializer(null), msg);
        byte[] byArray = string2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        SerializedPayload payload = new SerializedPayload(string, byArray, this.getS2cLocation());
        Iterable<? extends ServerPlayer> $this$forEach$iv = players;
        boolean $i$f$forEach = false;
        Iterator<? extends ServerPlayer> iterator = $this$forEach$iv.iterator();
        while (iterator.hasNext()) {
            ServerPlayer element$iv;
            ServerPlayer player = element$iv = iterator.next();
            boolean bl = false;
            if (!ServerPlayNetworking.canSend((ServerPlayer)player, (Identifier)this.getS2cLocation())) continue;
            ServerPlayNetworking.send((ServerPlayer)player, (CustomPacketPayload)payload);
        }
    }

    /*
     * WARNING - void declaration
     */
    public final /* synthetic */ <T> void sendToPlayer(ServerPlayer player, T msg) {
        void players$iv;
        void this_$iv;
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        Intrinsics.checkNotNullParameter(msg, (String)"msg");
        boolean $i$f$sendToPlayer = false;
        BetterChannel betterChannel = this;
        Iterable iterable = CollectionsKt.listOf((Object)player);
        T msg$iv = msg;
        boolean $i$f$sendToPlayers = false;
        Intrinsics.reifiedOperationMarker((int)4, (String)"T");
        String string = Object.class.getName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
        Intrinsics.reifiedOperationMarker((int)6, (String)"T");
        MagicApiIntrinsics.voidMagicApiCall((Object)"kotlinx.serialization.serializer.simple");
        byte[] byArray = Json.Default.encodeToString((SerializationStrategy)SerializersKt.serializer(null), msg$iv).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        SerializedPayload payload$iv = new SerializedPayload(string, byArray, this_$iv.getS2cLocation());
        void $this$forEach$iv$iv = players$iv;
        boolean $i$f$forEach = false;
        for (Object element$iv$iv : $this$forEach$iv$iv) {
            ServerPlayer player$iv = (ServerPlayer)element$iv$iv;
            boolean bl = false;
            if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv, (Identifier)this_$iv.getS2cLocation())) continue;
            ServerPlayNetworking.send((ServerPlayer)player$iv, (CustomPacketPayload)payload$iv);
        }
    }

    public final /* synthetic */ <T> void sendToServer(T msg) {
        Intrinsics.checkNotNullParameter(msg, (String)"msg");
        boolean $i$f$sendToServer = false;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            Intrinsics.reifiedOperationMarker((int)4, (String)"T");
            String string = Object.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            Intrinsics.reifiedOperationMarker((int)6, (String)"T");
            MagicApiIntrinsics.voidMagicApiCall((Object)"kotlinx.serialization.serializer.simple");
            String string2 = Json.Default.encodeToString((SerializationStrategy)SerializersKt.serializer(null), msg);
            byte[] byArray = string2.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            SerializedPayload payload = new SerializedPayload(string, byArray, this.getC2sLocation());
            ClientPlayNetworking.send((CustomPacketPayload)payload);
        }
    }

    private static final void lambda$0$0(BetterChannel this$0, SerializedPayload $payload) {
        block0: {
            Function2<byte[], ServerPlayer, Unit> function2 = this$0.handlers.get($payload.getClassName());
            if (function2 == null) break block0;
            function2.invoke((Object)$payload.getData(), null);
        }
    }

    private static final void lambda$1$0(BetterChannel this$0, SerializedPayload $payload, ServerPlayNetworking.Context $context) {
        block0: {
            Function2<byte[], ServerPlayer, Unit> function2 = this$0.handlers.get($payload.getClassName());
            if (function2 == null) break block0;
            function2.invoke((Object)$payload.getData(), (Object)$context.player());
        }
    }

    private static final void _init_$lambda$0(BetterChannel this$0, SerializedPayload payload, ClientPlayNetworking.Context context) {
        Intrinsics.checkNotNullParameter((Object)payload, (String)"payload");
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        context.client().execute(() -> BetterChannel.lambda$0$0(this$0, payload));
    }

    private static final void _init_$lambda$1(BetterChannel this$0, SerializedPayload payload, ServerPlayNetworking.Context context) {
        Intrinsics.checkNotNullParameter((Object)payload, (String)"payload");
        Intrinsics.checkNotNullParameter((Object)context, (String)"context");
        context.server().execute(() -> BetterChannel.lambda$1$0(this$0, payload, context));
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Lobservable/net/BetterChannel$Companion;", "", "<init>", "()V", "LOGGER", "Lorg/apache/logging/log4j/Logger;", "kotlin.jvm.PlatformType", "getLOGGER", "()Lorg/apache/logging/log4j/Logger;", "observable"})
    public static final class Companion {
        private Companion() {
        }

        public final Logger getLOGGER() {
            return LOGGER;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\u0011H\u0016J\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0007H\u00c6\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u00d6\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bH\u00d6\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003H\u00d6\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001e"}, d2={"Lobservable/net/BetterChannel$SerializedPayload;", "Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;", "className", "", "data", "", "payloadId", "Lnet/minecraft/resources/Identifier;", "<init>", "(Ljava/lang/String;[BLnet/minecraft/resources/Identifier;)V", "getClassName", "()Ljava/lang/String;", "getData", "()[B", "getPayloadId", "()Lnet/minecraft/resources/Identifier;", "type", "Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload$Type;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "observable"})
    public static final class SerializedPayload
    implements CustomPacketPayload {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final String className;
        @NotNull
        private final byte[] data;
        @NotNull
        private final Identifier payloadId;

        public SerializedPayload(@NotNull String className, @NotNull byte[] data, @NotNull Identifier payloadId) {
            Intrinsics.checkNotNullParameter((Object)className, (String)"className");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            Intrinsics.checkNotNullParameter((Object)payloadId, (String)"payloadId");
            this.className = className;
            this.data = data;
            this.payloadId = payloadId;
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        @NotNull
        public final byte[] getData() {
            return this.data;
        }

        @NotNull
        public final Identifier getPayloadId() {
            return this.payloadId;
        }

        @NotNull
        public CustomPacketPayload.Type<SerializedPayload> type() {
            return Companion.type(this.payloadId);
        }

        @NotNull
        public final String component1() {
            return this.className;
        }

        @NotNull
        public final byte[] component2() {
            return this.data;
        }

        @NotNull
        public final Identifier component3() {
            return this.payloadId;
        }

        @NotNull
        public final SerializedPayload copy(@NotNull String className, @NotNull byte[] data, @NotNull Identifier payloadId) {
            Intrinsics.checkNotNullParameter((Object)className, (String)"className");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            Intrinsics.checkNotNullParameter((Object)payloadId, (String)"payloadId");
            return new SerializedPayload(className, data, payloadId);
        }

        public static /* synthetic */ SerializedPayload copy$default(SerializedPayload serializedPayload, String string, byte[] byArray, Identifier identifier, int n, Object object) {
            if ((n & 1) != 0) {
                string = serializedPayload.className;
            }
            if ((n & 2) != 0) {
                byArray = serializedPayload.data;
            }
            if ((n & 4) != 0) {
                identifier = serializedPayload.payloadId;
            }
            return serializedPayload.copy(string, byArray, identifier);
        }

        @NotNull
        public String toString() {
            return "SerializedPayload(className=" + this.className + ", data=" + Arrays.toString(this.data) + ", payloadId=" + this.payloadId + ")";
        }

        public int hashCode() {
            int result = this.className.hashCode();
            result = result * 31 + Arrays.hashCode(this.data);
            result = result * 31 + this.payloadId.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SerializedPayload)) {
                return false;
            }
            SerializedPayload serializedPayload = (SerializedPayload)other;
            if (!Intrinsics.areEqual((Object)this.className, (Object)serializedPayload.className)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.data, (Object)serializedPayload.data)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.payloadId, (Object)serializedPayload.payloadId);
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2={"Lobservable/net/BetterChannel$SerializedPayload$Companion;", "", "<init>", "()V", "type", "Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload$Type;", "Lobservable/net/BetterChannel$SerializedPayload;", "id", "Lnet/minecraft/resources/Identifier;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final CustomPacketPayload.Type<SerializedPayload> type(@NotNull Identifier id) {
                Intrinsics.checkNotNullParameter((Object)id, (String)"id");
                return new CustomPacketPayload.Type(id);
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

