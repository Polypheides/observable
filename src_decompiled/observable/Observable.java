/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlinx.serialization.DeserializationStrategy
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.json.Json
 *  net.fabricmc.api.ModInitializer
 *  net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
 *  net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.client.DeltaTracker
 *  net.minecraft.client.KeyMapping
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphicsExtractor
 *  net.minecraft.client.gui.screens.Screen
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.resources.Identifier
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.permissions.Permissions
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.phys.Vec3
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.joml.Matrix4fStack
 *  org.joml.Matrix4fc
 */
package observable;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permissions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import observable.client.Overlay;
import observable.client.ProfileScreen;
import observable.client.ProfilerBridge;
import observable.fabric.Client;
import observable.net.BetterChannel;
import observable.net.C2SPacket;
import observable.net.S2CPacket;
import observable.server.ObservableCommands;
import observable.server.Profiler;
import observable.server.ProfilingData;
import observable.server.Remapper;
import observable.server.ServerSettingsKt;
import observable.server.Tracing;
import observable.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0007J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u001dH\u0016J\b\u0010#\u001a\u00020\u001dH\u0007J\b\u0010$\u001a\u00020\u001dH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2={"Lobservable/Observable;", "Lnet/fabricmc/api/ModInitializer;", "<init>", "()V", "MOD_ID", "", "LOGGER", "Lorg/apache/logging/log4j/Logger;", "getLOGGER", "()Lorg/apache/logging/log4j/Logger;", "CHANNEL", "Lobservable/net/BetterChannel;", "getCHANNEL", "()Lobservable/net/BetterChannel;", "PROFILER", "Lobservable/server/Profiler;", "getPROFILER", "()Lobservable/server/Profiler;", "PROFILER$delegate", "Lkotlin/Lazy;", "SERVER_INSTANCE", "Lnet/minecraft/server/MinecraftServer;", "getSERVER_INSTANCE", "()Lnet/minecraft/server/MinecraftServer;", "setSERVER_INSTANCE", "(Lnet/minecraft/server/MinecraftServer;)V", "RESULTS", "Lobservable/server/ProfilingData;", "clearResults", "", "hasPermission", "", "player", "Lnet/minecraft/world/entity/player/Player;", "onInitialize", "openProfileScreen", "clientInit", "observable"})
@SourceDebugExtension(value={"SMAP\nObservable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Observable.kt\nobservable/Observable\n+ 2 BetterChannel.kt\nobservable/net/BetterChannel\n+ 3 Overlay.kt\nobservable/client/Overlay\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,149:1\n80#2,3:150\n80#2,3:153\n80#2,3:156\n80#2,3:159\n80#2,3:162\n80#2,3:165\n80#2,3:168\n93#2:173\n85#2,2:174\n87#2,4:177\n91#2:182\n102#3:171\n1#4:172\n1915#5:176\n1916#5:181\n*S KotlinDebug\n*F\n+ 1 Observable.kt\nobservable/Observable\n*L\n69#1:150,3\n81#1:153,3\n95#1:156,3\n101#1:159,3\n109#1:162,3\n114#1:165,3\n117#1:168,3\n83#1:173\n83#1:174,2\n83#1:177,4\n83#1:182\n98#1:171\n98#1:172\n83#1:176\n83#1:181\n*E\n"})
public final class Observable
implements ModInitializer {
    @NotNull
    public static final Observable INSTANCE = new Observable();
    @NotNull
    public static final String MOD_ID = "observable";
    @NotNull
    private static final Logger LOGGER;
    @NotNull
    private static final BetterChannel CHANNEL;
    @NotNull
    private static final Lazy PROFILER$delegate;
    @Nullable
    private static MinecraftServer SERVER_INSTANCE;
    @JvmField
    @Nullable
    public static ProfilingData RESULTS;

    private Observable() {
    }

    @NotNull
    public final Logger getLOGGER() {
        return LOGGER;
    }

    @NotNull
    public final BetterChannel getCHANNEL() {
        return CHANNEL;
    }

    @NotNull
    public final Profiler getPROFILER() {
        Lazy lazy = PROFILER$delegate;
        return (Profiler)lazy.getValue();
    }

    @Nullable
    public final MinecraftServer getSERVER_INSTANCE() {
        return SERVER_INSTANCE;
    }

    public final void setSERVER_INSTANCE(@Nullable MinecraftServer minecraftServer) {
        SERVER_INSTANCE = minecraftServer;
    }

    @JvmStatic
    public static final void clearResults() {
        RESULTS = null;
    }

    public final boolean hasPermission(@NotNull Player player) {
        Intrinsics.checkNotNullParameter((Object)player, (String)"player");
        if (ServerSettingsKt.getServerSettings().getAllPlayersAllowed()) {
            return true;
        }
        if (ServerSettingsKt.getServerSettings().getAllowedPlayers().contains(player.getGameProfile().id().toString())) {
            return true;
        }
        if (player instanceof ServerPlayer && ((ServerPlayer)player).createCommandSourceStack().permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER)) {
            return true;
        }
        MinecraftServer minecraftServer = SERVER_INSTANCE;
        if (!(minecraftServer != null && (minecraftServer = minecraftServer.getPlayerList()) != null ? !minecraftServer.isOp(player.nameAndId()) : false)) {
            return true;
        }
        MinecraftServer minecraftServer2 = SERVER_INSTANCE;
        return minecraftServer2 != null ? minecraftServer2.isSingleplayer() : false;
    }

    public void onInitialize() {
        BetterChannel this_$iv;
        Constants.INSTANCE.load();
        Tracing.INSTANCE.init();
        this.getPROFILER().init();
        Remapper.INSTANCE.init();
        ServerLifecycleEvents.SERVER_STARTED.register(Observable::onInitialize$lambda$0);
        ServerLifecycleEvents.SERVER_STOPPING.register(Observable::onInitialize$lambda$1);
        ObservableCommands.INSTANCE.register();
        BetterChannel betterChannel = CHANNEL;
        Function2 consumer$iv = Observable::onInitialize$lambda$2;
        boolean $i$f$register = false;
        this_$iv.getHandlers().put(C2SPacket.InitTPSProfile.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)C2SPacket.InitTPSProfile.Companion.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + C2SPacket.InitTPSProfile.class.getName());
        this_$iv = CHANNEL;
        consumer$iv = Observable::onInitialize$lambda$3;
        $i$f$register = false;
        this_$iv.getHandlers().put(C2SPacket.RequestAvailability.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)C2SPacket.RequestAvailability.INSTANCE.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + C2SPacket.RequestAvailability.class.getName());
        this_$iv = CHANNEL;
        consumer$iv = Observable::onInitialize$lambda$4;
        $i$f$register = false;
        this_$iv.getHandlers().put(S2CPacket.ProfilingResult.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)S2CPacket.ProfilingResult.Companion.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + S2CPacket.ProfilingResult.class.getName());
        this_$iv = CHANNEL;
        consumer$iv = Observable::onInitialize$lambda$5;
        $i$f$register = false;
        this_$iv.getHandlers().put(S2CPacket.Availability.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)S2CPacket.Availability.Companion.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + S2CPacket.Availability.class.getName());
        this_$iv = CHANNEL;
        consumer$iv = Observable::onInitialize$lambda$6;
        $i$f$register = false;
        this_$iv.getHandlers().put(S2CPacket.ProfilingStarted.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)S2CPacket.ProfilingStarted.Companion.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + S2CPacket.ProfilingStarted.class.getName());
        this_$iv = CHANNEL;
        consumer$iv = Observable::onInitialize$lambda$7;
        $i$f$register = false;
        this_$iv.getHandlers().put(S2CPacket.ProfilingCompleted.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)S2CPacket.ProfilingCompleted.INSTANCE.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + S2CPacket.ProfilingCompleted.class.getName());
        this_$iv = CHANNEL;
        consumer$iv = Observable::onInitialize$lambda$8;
        $i$f$register = false;
        this_$iv.getHandlers().put(S2CPacket.ProfilerInactive.class.getName(), new Function2<byte[], ServerPlayer, Unit>(consumer$iv){
            final /* synthetic */ Function2 $consumer;
            {
                this.$consumer = $consumer;
            }

            public final void invoke(byte[] buf, ServerPlayer player) {
                Intrinsics.checkNotNullParameter((Object)buf, (String)"buf");
                this.$consumer.invoke(Json.Default.decodeFromString((DeserializationStrategy)S2CPacket.ProfilerInactive.INSTANCE.serializer(), new String(buf, Charsets.UTF_8)), (Object)player);
            }
        });
        BetterChannel.Companion.getLOGGER().info("Registered " + S2CPacket.ProfilerInactive.class.getName());
    }

    @JvmStatic
    public static final void openProfileScreen() {
        Minecraft.getInstance().setScreen((Screen)Client.Companion.getPROFILE_SCREEN());
    }

    @JvmStatic
    public static final void clientInit() {
        KeyMappingHelper.registerKeyMapping((KeyMapping)Overlay.INSTANCE.getKEY_OPEN_SETTINGS());
        KeyMappingHelper.registerKeyMapping((KeyMapping)Overlay.INSTANCE.getKEY_TOGGLE_OVERLAY());
        ProfilerBridge.setBridge(Overlay.INSTANCE);
        ProfilerBridge.setScreenOpener(Observable::clientInit$lambda$0);
        ProfilerBridge.setHudRenderer(Observable::clientInit$lambda$1);
        ProfilerBridge.setWorldRenderer(Observable::clientInit$lambda$2);
    }

    private static final Profiler PROFILER_delegate$lambda$0() {
        return new Profiler();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static final void onInitialize$lambda$4$0(S2CPacket.ProfilingResult $pkt) {
        RESULTS = $pkt.getData();
        Overlay $this$iv = Overlay.INSTANCE;
        ClientLevel lvl$iv = null;
        boolean $i$f$loadSync = false;
        Overlay overlay = $this$iv;
        synchronized (overlay) {
            boolean bl = false;
            Overlay.INSTANCE.load(lvl$iv);
            Unit unit = Unit.INSTANCE;
        }
    }

    private static final void onInitialize$lambda$0(MinecraftServer server) {
        Intrinsics.checkNotNullParameter((Object)server, (String)"server");
        SERVER_INSTANCE = server;
    }

    private static final void onInitialize$lambda$1(MinecraftServer it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        SERVER_INSTANCE = null;
    }

    private static final Unit onInitialize$lambda$2(C2SPacket.InitTPSProfile t, ServerPlayer player) {
        Intrinsics.checkNotNullParameter((Object)t, (String)"t");
        if (player == null) {
            return Unit.INSTANCE;
        }
        if (!INSTANCE.hasPermission((Player)player)) {
            LOGGER.info(player.getName().getString() + " lacks permissions to start profiling");
            return Unit.INSTANCE;
        }
        if (INSTANCE.getPROFILER().getNotProcessing()) {
            ServerPlayer serverPlayer = player;
            INSTANCE.getPROFILER().runWithDuration(serverPlayer, t.getDuration(), t.getSample());
        }
        LOGGER.info(player.getGameProfile().name() + " started profiler for " + t.getDuration() + " s");
        return Unit.INSTANCE;
    }

    /*
     * WARNING - void declaration
     */
    private static final Unit onInitialize$lambda$3(C2SPacket.RequestAvailability requestAvailability, ServerPlayer player) {
        block2: {
            void players$iv$iv;
            void this_$iv$iv;
            void msg$iv;
            void player$iv;
            void this_$iv;
            Intrinsics.checkNotNullParameter((Object)requestAvailability, (String)"<unused var>");
            ServerPlayer serverPlayer = player;
            if (serverPlayer == null) {
                serverPlayer = null;
            }
            if (serverPlayer == null) break block2;
            ServerPlayer it = serverPlayer;
            boolean bl = false;
            BetterChannel betterChannel = CHANNEL;
            ServerPlayer serverPlayer2 = it;
            S2CPacket.Availability availability = INSTANCE.hasPermission((Player)it) ? S2CPacket.Availability.Available : S2CPacket.Availability.NoPermissions;
            boolean $i$f$sendToPlayer = false;
            void var8_8 = this_$iv;
            Iterable iterable = CollectionsKt.listOf((Object)player$iv);
            void msg$iv$iv = msg$iv;
            boolean $i$f$sendToPlayers = false;
            String string = S2CPacket.Availability.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
            byte[] byArray = Json.Default.encodeToString((SerializationStrategy)S2CPacket.Availability.Companion.serializer(), (Object)msg$iv$iv).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
            BetterChannel.SerializedPayload payload$iv$iv = new BetterChannel.SerializedPayload(string, byArray, this_$iv$iv.getS2cLocation());
            void $this$forEach$iv$iv$iv = players$iv$iv;
            boolean $i$f$forEach = false;
            for (Object element$iv$iv$iv : $this$forEach$iv$iv$iv) {
                ServerPlayer player$iv$iv = (ServerPlayer)element$iv$iv$iv;
                boolean bl2 = false;
                if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv$iv, (Identifier)this_$iv$iv.getS2cLocation())) continue;
                ServerPlayNetworking.send((ServerPlayer)player$iv$iv, (CustomPacketPayload)payload$iv$iv);
            }
        }
        return Unit.INSTANCE;
    }

    private static final Unit onInitialize$lambda$4(S2CPacket.ProfilingResult pkt, ServerPlayer serverPlayer) {
        Intrinsics.checkNotNullParameter((Object)pkt, (String)"pkt");
        Minecraft.getInstance().execute(() -> Observable.onInitialize$lambda$4$0(pkt));
        return Unit.INSTANCE;
    }

    private static final Unit onInitialize$lambda$5(S2CPacket.Availability pkt, ServerPlayer serverPlayer) {
        Intrinsics.checkNotNullParameter((Object)((Object)pkt), (String)"pkt");
        ProfileScreen profileScreen = Client.Companion.getPROFILE_SCREEN();
        profileScreen.setAction(pkt == S2CPacket.Availability.Available ? (ProfileScreen.Action)ProfileScreen.Action.Companion.getDEFAULT() : (ProfileScreen.Action)ProfileScreen.Action.Companion.getNO_PERMISSIONS());
        return Unit.INSTANCE;
    }

    private static final Unit onInitialize$lambda$6(S2CPacket.ProfilingStarted pkt, ServerPlayer serverPlayer) {
        block0: {
            Intrinsics.checkNotNullParameter((Object)pkt, (String)"pkt");
            ProfileScreen profileScreen = Client.Companion.getPROFILE_SCREEN();
            profileScreen.setAction(new ProfileScreen.Action.TPSProfilerRunning(pkt.getEndMillis()));
            if (profileScreen.getStartBtn() == null) break block0;
            profileScreen.getStartBtn().active = false;
        }
        return Unit.INSTANCE;
    }

    private static final Unit onInitialize$lambda$7(S2CPacket.ProfilingCompleted profilingCompleted, ServerPlayer serverPlayer) {
        Intrinsics.checkNotNullParameter((Object)profilingCompleted, (String)"<unused var>");
        Client.Companion.getPROFILE_SCREEN().setAction(ProfileScreen.Action.TPSProfilerCompleted.INSTANCE);
        return Unit.INSTANCE;
    }

    private static final Unit onInitialize$lambda$8(S2CPacket.ProfilerInactive profilerInactive, ServerPlayer serverPlayer) {
        block0: {
            Intrinsics.checkNotNullParameter((Object)profilerInactive, (String)"<unused var>");
            ProfileScreen profileScreen = Client.Companion.getPROFILE_SCREEN();
            profileScreen.setAction(ProfileScreen.Action.Companion.getDEFAULT());
            if (profileScreen.getStartBtn() == null) break block0;
            profileScreen.getStartBtn().active = true;
        }
        return Unit.INSTANCE;
    }

    private static final void clientInit$lambda$0(DeltaTracker it) {
        Observable.openProfileScreen();
    }

    private static final void clientInit$lambda$1(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        Intrinsics.checkNotNull((Object)graphics);
        Intrinsics.checkNotNull((Object)delta);
        Overlay.INSTANCE.renderHud(graphics, delta);
    }

    private static final void clientInit$lambda$2(Matrix4fStack stack, MultiBufferSource bufferSource, Vec3 camera, Matrix4fc rot) {
        Intrinsics.checkNotNullParameter((Object)stack, (String)"stack");
        Intrinsics.checkNotNullParameter((Object)bufferSource, (String)"bufferSource");
        Intrinsics.checkNotNullParameter((Object)camera, (String)"camera");
        Intrinsics.checkNotNullParameter((Object)rot, (String)"rot");
        Overlay.INSTANCE.render(stack, bufferSource, camera, rot);
    }

    static {
        Logger logger = LogManager.getLogger((String)"Observable");
        Intrinsics.checkNotNullExpressionValue((Object)logger, (String)"getLogger(...)");
        LOGGER = logger;
        Identifier identifier = Identifier.fromNamespaceAndPath((String)MOD_ID, (String)"channel");
        Intrinsics.checkNotNullExpressionValue((Object)identifier, (String)"fromNamespaceAndPath(...)");
        CHANNEL = new BetterChannel(identifier);
        PROFILER$delegate = LazyKt.lazy(Observable::PROFILER_delegate$lambda$0);
    }
}

