/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.io.CloseableKt
 *  kotlin.io.TextStreamsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.Reflection
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.json.Json
 *  kotlinx.serialization.json.JsonObject
 *  net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.network.protocol.common.custom.CustomPacketPayload
 *  net.minecraft.resources.Identifier
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.players.PlayerList
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.TickingBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  org.apache.logging.log4j.Logger
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonObject;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import observable.Observable;
import observable.Props;
import observable.net.BetterChannel;
import observable.net.S2CPacket;
import observable.server.DataWithDiagnostics;
import observable.server.DiagnosticsKt;
import observable.server.NativeProfiler;
import observable.server.NativeTimingData;
import observable.server.ProfilingData;
import observable.server.ServerSettingsKt;
import observable.server.TaggedSampler;
import observable.server.TraceMap;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u0006J\u0016\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u000eJ\u001e\u00103\u001a\u00020\u00072\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u000eJ\u001e\u00107\u001a\u00020\u00072\u0006\u00104\u001a\u0002082\u0006\u00106\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u000eJ\u0010\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u0015J \u0010<\u001a\u00020:2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010=\u001a\u00020(2\u0006\u0010;\u001a\u00020\u0015J\u0018\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CJ\u0006\u0010D\u001a\u00020:J\u0006\u0010E\u001a\u00020:R&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR8\u0010\f\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u00050\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00158F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020(X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,\u00a8\u0006F"}, d2={"Lobservable/server/Profiler;", "", "<init>", "()V", "timingsMap", "Ljava/util/HashMap;", "Lnet/minecraft/world/entity/Entity;", "Lobservable/server/NativeTimingData;", "getTimingsMap", "()Ljava/util/HashMap;", "setTimingsMap", "(Ljava/util/HashMap;)V", "blockTimingsMap", "Lnet/minecraft/resources/ResourceKey;", "Lnet/minecraft/world/level/Level;", "Lnet/minecraft/core/BlockPos;", "getBlockTimingsMap", "setBlockTimingsMap", "lock", "Ljava/lang/Object;", "v", "", "notProcessing", "getNotProcessing", "()Z", "setNotProcessing", "(Z)V", "player", "Lnet/minecraft/server/level/ServerPlayer;", "getPlayer", "()Lnet/minecraft/server/level/ServerPlayer;", "setPlayer", "(Lnet/minecraft/server/level/ServerPlayer;)V", "startTime", "", "getStartTime", "()J", "setStartTime", "(J)V", "startingTicks", "", "getStartingTicks", "()I", "setStartingTicks", "(I)V", "process", "entity", "processBlockEntity", "blockEntity", "Lnet/minecraft/world/level/block/entity/TickingBlockEntity;", "level", "processBlock", "state", "Lnet/minecraft/world/level/block/state/BlockState;", "pos", "processFluid", "Lnet/minecraft/world/level/material/FluidState;", "startRunning", "", "sample", "runWithDuration", "duration", "uploadProfile", "", "data", "Lobservable/server/ProfilingData;", "diagnostics", "Lkotlinx/serialization/json/JsonObject;", "stopRunning", "init", "observable"})
@SourceDebugExtension(value={"SMAP\nProfiler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Profiler.kt\nobservable/server/Profiler\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 BetterChannel.kt\nobservable/net/BetterChannel\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Json.kt\nkotlinx/serialization/json/Json\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,228:1\n383#2,7:229\n383#2,7:236\n383#2,7:243\n383#2,7:250\n383#2,7:257\n383#2,7:264\n383#2,7:271\n85#3,2:278\n87#3,4:281\n91#3:286\n85#3,2:289\n87#3,4:292\n91#3:297\n85#3,2:298\n87#3,4:301\n91#3:306\n85#3,2:310\n87#3,4:313\n91#3:318\n1915#4:280\n1916#4:285\n1915#4:291\n1916#4:296\n1915#4:300\n1916#4:305\n777#4:307\n873#4,2:308\n1915#4:312\n1916#4:317\n205#5:287\n1#6:288\n*S KotlinDebug\n*F\n+ 1 Profiler.kt\nobservable/server/Profiler\n*L\n39#1:229,7\n43#1:236,7\n44#1:243,7\n50#1:250,7\n51#1:257,7\n57#1:264,7\n58#1:271,7\n87#1:278,2\n87#1:281,4\n87#1:286\n137#1:289,2\n137#1:292,4\n137#1:297\n145#1:298,2\n145#1:301,4\n145#1:306\n154#1:310,2\n154#1:313,4\n154#1:318\n87#1:280\n87#1:285\n137#1:291\n137#1:296\n145#1:300\n145#1:305\n153#1:307\n153#1:308,2\n154#1:312\n154#1:317\n105#1:287\n*E\n"})
public final class Profiler {
    @NotNull
    private HashMap<Entity, NativeTimingData> timingsMap = new HashMap();
    @NotNull
    private HashMap<ResourceKey<Level>, HashMap<BlockPos, NativeTimingData>> blockTimingsMap = new HashMap();
    @NotNull
    private final Object lock = new Object();
    @Nullable
    private ServerPlayer player;
    private long startTime;
    private int startingTicks;

    @NotNull
    public final HashMap<Entity, NativeTimingData> getTimingsMap() {
        return this.timingsMap;
    }

    public final void setTimingsMap(@NotNull HashMap<Entity, NativeTimingData> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, (String)"<set-?>");
        this.timingsMap = hashMap;
    }

    @NotNull
    public final HashMap<ResourceKey<Level>, HashMap<BlockPos, NativeTimingData>> getBlockTimingsMap() {
        return this.blockTimingsMap;
    }

    public final void setBlockTimingsMap(@NotNull HashMap<ResourceKey<Level>, HashMap<BlockPos, NativeTimingData>> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, (String)"<set-?>");
        this.blockTimingsMap = hashMap;
    }

    public final boolean getNotProcessing() {
        return Props.notProcessing.get();
    }

    public final void setNotProcessing(boolean v) {
        Props.notProcessing.set(v);
    }

    @Nullable
    public final ServerPlayer getPlayer() {
        return this.player;
    }

    public final void setPlayer(@Nullable ServerPlayer serverPlayer) {
        this.player = serverPlayer;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void setStartTime(long l) {
        this.startTime = l;
    }

    public final int getStartingTicks() {
        return this.startingTicks;
    }

    public final void setStartingTicks(int n) {
        this.startingTicks = n;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public final NativeTimingData process(@NotNull Entity entity) {
        Object object;
        void $this$getOrPut$iv;
        Intrinsics.checkNotNullParameter((Object)entity, (String)"entity");
        Map map = this.timingsMap;
        Entity key$iv = entity;
        boolean $i$f$getOrPut = false;
        Object value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            NativeTimingData answer$iv = new NativeTimingData(0L, 0, "", new TraceMap(Reflection.getOrCreateKotlinClass(entity.getClass())));
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object = answer$iv;
        } else {
            object = value$iv;
        }
        return (NativeTimingData)object;
    }

    @NotNull
    public final NativeTimingData processBlockEntity(@NotNull TickingBlockEntity blockEntity, @NotNull Level level) {
        Object object;
        Object object2;
        Map $this$getOrPut$iv;
        Intrinsics.checkNotNullParameter((Object)blockEntity, (String)"blockEntity");
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        Map map = this.blockTimingsMap;
        ResourceKey key$iv = level.dimension();
        boolean $i$f$getOrPut = false;
        Object value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            HashMap answer$iv = new HashMap();
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object2 = answer$iv;
        } else {
            object2 = value$iv;
        }
        $this$getOrPut$iv = (Map)object2;
        key$iv = blockEntity.getPos();
        $i$f$getOrPut = false;
        value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            NativeTimingData answer$iv = new NativeTimingData(0L, 0, blockEntity.getType().toString(), new TraceMap(Reflection.getOrCreateKotlinClass(blockEntity.getClass())));
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object = answer$iv;
        } else {
            object = value$iv;
        }
        return (NativeTimingData)object;
    }

    @NotNull
    public final NativeTimingData processBlock(@NotNull BlockState state, @NotNull BlockPos pos, @NotNull Level level) {
        Object object;
        Object object2;
        Map $this$getOrPut$iv;
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        Map map = this.blockTimingsMap;
        ResourceKey key$iv = level.dimension();
        boolean $i$f$getOrPut = false;
        Object value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            HashMap answer$iv = new HashMap();
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object2 = answer$iv;
        } else {
            object2 = value$iv;
        }
        $this$getOrPut$iv = (Map)object2;
        key$iv = pos;
        $i$f$getOrPut = false;
        value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            NativeTimingData answer$iv = new NativeTimingData(0L, 0, BuiltInRegistries.BLOCK.getKey((Object)state.getBlock()).toString(), new TraceMap(Reflection.getOrCreateKotlinClass(state.getBlock().getClass())));
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object = answer$iv;
        } else {
            object = value$iv;
        }
        return (NativeTimingData)object;
    }

    @NotNull
    public final NativeTimingData processFluid(@NotNull FluidState state, @NotNull BlockPos pos, @NotNull Level level) {
        Object object;
        Object object2;
        Map $this$getOrPut$iv;
        Intrinsics.checkNotNullParameter((Object)state, (String)"state");
        Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
        Intrinsics.checkNotNullParameter((Object)level, (String)"level");
        Map map = this.blockTimingsMap;
        ResourceKey key$iv = level.dimension();
        boolean $i$f$getOrPut = false;
        Object value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            HashMap answer$iv = new HashMap();
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object2 = answer$iv;
        } else {
            object2 = value$iv;
        }
        $this$getOrPut$iv = (Map)object2;
        key$iv = pos;
        $i$f$getOrPut = false;
        value$iv = $this$getOrPut$iv.get(key$iv);
        if (value$iv == null) {
            boolean bl = false;
            NativeTimingData answer$iv = new NativeTimingData(0L, 0, BuiltInRegistries.FLUID.getKey((Object)state.getType()).toString(), new TraceMap(Reflection.getOrCreateKotlinClass(state.getType().getClass())));
            $this$getOrPut$iv.put(key$iv, answer$iv);
            object = answer$iv;
        } else {
            object = value$iv;
        }
        return (NativeTimingData)object;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void startRunning(boolean sample) {
        this.timingsMap.clear();
        this.blockTimingsMap.clear();
        this.startTime = System.currentTimeMillis();
        Object object = this.lock;
        synchronized (object) {
            boolean bl = false;
            this.setNotProcessing(false);
            MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
            this.startingTicks = minecraftServer != null ? minecraftServer.getTickCount() : 0;
            Unit unit = Unit.INSTANCE;
        }
        if (sample) {
            Thread thread = Thread.currentThread();
            Intrinsics.checkNotNullExpressionValue((Object)thread, (String)"currentThread(...)");
            Thread thread2 = new Thread(new TaggedSampler(thread));
            thread2.setName("Observable-Sampler");
            thread2.setDaemon(true);
            thread2.start();
        }
    }

    public static /* synthetic */ void startRunning$default(Profiler profiler, boolean bl, int n, Object object) {
        if ((n & 1) != 0) {
            bl = false;
        }
        profiler.startRunning(bl);
    }

    /*
     * WARNING - void declaration
     */
    public final void runWithDuration(@Nullable ServerPlayer player, int duration, boolean sample) {
        void players$iv;
        void this_$iv;
        void msg$iv;
        this.player = player;
        this.startRunning(sample);
        long durMs = (long)duration * 1000L;
        Object object = Observable.INSTANCE.getCHANNEL();
        MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
        Intrinsics.checkNotNull((Object)minecraftServer);
        List list = minecraftServer.getPlayerList().getPlayers();
        Intrinsics.checkNotNullExpressionValue((Object)list, (String)"getPlayers(...)");
        Object object2 = list;
        S2CPacket.ProfilingStarted profilingStarted = new S2CPacket.ProfilingStarted(this.startTime + durMs);
        boolean $i$f$sendToPlayers = false;
        String string = S2CPacket.ProfilingStarted.class.getName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
        byte[] byArray = Json.Default.encodeToString((SerializationStrategy)S2CPacket.ProfilingStarted.Companion.serializer(), (Object)msg$iv).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        BetterChannel.SerializedPayload payload$iv = new BetterChannel.SerializedPayload(string, byArray, this_$iv.getS2cLocation());
        void $this$forEach$iv$iv = players$iv;
        boolean $i$f$forEach = false;
        for (Object element$iv$iv : $this$forEach$iv$iv) {
            ServerPlayer player$iv = (ServerPlayer)element$iv$iv;
            boolean bl = false;
            if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv, (Identifier)this_$iv.getS2cLocation())) continue;
            ServerPlayNetworking.send((ServerPlayer)player$iv, (CustomPacketPayload)payload$iv);
        }
        object = new Timer("Profiler", false);
        object2 = new TimerTask(this){
            final /* synthetic */ Profiler this$0;
            {
                this.this$0 = profiler;
            }

            public void run() {
                block0: {
                    TimerTask $this$runWithDuration_u24lambda_u240 = this;
                    boolean bl = false;
                    MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
                    if (minecraftServer == null) break block0;
                    minecraftServer.execute(new Runnable(this.this$0){
                        final /* synthetic */ Profiler this$0;
                        {
                            this.this$0 = $receiver;
                        }

                        public final void run() {
                            this.this$0.stopRunning();
                        }
                    });
                }
            }
        };
        ((Timer)object).schedule((TimerTask)object2, durMs);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    @Nullable
    public final String uploadProfile(@NotNull ProfilingData data, @NotNull JsonObject diagnostics) {
        void this_$iv;
        Intrinsics.checkNotNullParameter((Object)data, (String)"data");
        Intrinsics.checkNotNullParameter((Object)diagnostics, (String)"diagnostics");
        if (((CharSequence)ServerSettingsKt.getServerSettings().getUploadURL()).length() == 0) {
            Observable.INSTANCE.getLOGGER().info("uploadURL not set, skipping upload");
            return null;
        }
        Observable.INSTANCE.getLOGGER().info("Attempting to upload profile");
        Object object = (Json)Json.Default;
        Object value$iv = new DataWithDiagnostics(data, diagnostics);
        boolean $i$f$encodeToString = false;
        this_$iv.getSerializersModule();
        String serialized = this_$iv.encodeToString((SerializationStrategy)DataWithDiagnostics.Companion.serializer(), value$iv);
        try {
            String string;
            URLConnection uRLConnection = new URL(ServerSettingsKt.getServerSettings().getUploadURL()).openConnection();
            Intrinsics.checkNotNull((Object)uRLConnection, (String)"null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn = (HttpURLConnection)uRLConnection;
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            Logger logger = Observable.INSTANCE.getLOGGER();
            Object object2 = "%.2f";
            Object object3 = new Object[]{(double)serialized.length() / 1000.0};
            String string2 = String.format((String)object2, Arrays.copyOf(object3, ((Object[])object3).length));
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"format(...)");
            logger.info("Writing " + string2 + "kb");
            object3 = new OutputStreamWriter((OutputStream)new GZIPOutputStream(conn.getOutputStream()), Charsets.UTF_8);
            int n = 8192;
            value$iv = object3 instanceof BufferedWriter ? (BufferedWriter)object3 : new BufferedWriter((Writer)object3, n);
            object2 = null;
            try {
                BufferedWriter it = (BufferedWriter)value$iv;
                boolean bl = false;
                it.write(serialized);
                object3 = Unit.INSTANCE;
            }
            catch (Throwable bl) {
                object2 = bl;
                throw bl;
            }
            finally {
                CloseableKt.closeFinally((Closeable)value$iv, (Throwable)object2);
            }
            InputStream inputStream = conn.getInputStream();
            Intrinsics.checkNotNullExpressionValue((Object)inputStream, (String)"getInputStream(...)");
            object2 = inputStream;
            object3 = Charsets.UTF_8;
            Reader bl = new InputStreamReader((InputStream)object2, (Charset)object3);
            int n2 = 8192;
            object2 = bl instanceof BufferedReader ? (BufferedReader)bl : new BufferedReader(bl, n2);
            object3 = null;
            try {
                BufferedReader it = (BufferedReader)object2;
                boolean bl2 = false;
                string = TextStreamsKt.readText((Reader)it);
            }
            catch (Throwable throwable) {
                object3 = throwable;
                throw throwable;
            }
            finally {
                CloseableKt.closeFinally((Closeable)object2, (Throwable)object3);
            }
            String profileURL = string;
            Observable.INSTANCE.getLOGGER().info("Profile uploaded to " + profileURL);
            object = profileURL;
        }
        catch (Exception e) {
            e.printStackTrace();
            object = null;
        }
        return object;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    public final void stopRunning() {
        List list;
        PlayerList playerList;
        void players$iv;
        void this_$iv;
        void msg$iv;
        void players$iv2;
        void this_$iv2;
        Object object;
        Object $i$a$-synchronized-Profiler$stopRunning$22;
        Object object2;
        int ticks;
        JsonObject diagnostics;
        block12: {
            block11: {
                diagnostics = DiagnosticsKt.getDiagnostics(this);
                ticks = 0;
                object2 = this.lock;
                synchronized (object2) {
                    boolean $i$a$-synchronized-Profiler$stopRunning$22 = false;
                    this.setNotProcessing(true);
                    MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
                    ticks = (minecraftServer != null ? minecraftServer.getTickCount() : 0) - this.startingTicks;
                    Props.currentTarget.set(null);
                    $i$a$-synchronized-Profiler$stopRunning$22 = Unit.INSTANCE;
                }
                object = this.player;
                if (object == null) break block11;
                ServerPlayer it = object;
                boolean bl = false;
                List list2 = CollectionsKt.listOf((Object)it);
                object = list2;
                if (list2 != null) break block12;
            }
            object = CollectionsKt.emptyList();
        }
        Object playerList2 = object;
        object2 = Observable.INSTANCE.getCHANNEL();
        $i$a$-synchronized-Profiler$stopRunning$22 = (Iterable)playerList2;
        Object msg$iv2 = S2CPacket.ProfilingCompleted.INSTANCE;
        boolean $i$f$sendToPlayers22 = false;
        String string = S2CPacket.ProfilingCompleted.class.getName();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getName(...)");
        byte[] byArray = Json.Default.encodeToString((SerializationStrategy)S2CPacket.ProfilingCompleted.INSTANCE.serializer(), msg$iv2).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue((Object)byArray, (String)"getBytes(...)");
        BetterChannel.SerializedPayload payload$iv = new BetterChannel.SerializedPayload(string, byArray, this_$iv2.getS2cLocation());
        S2CPacket.ProfilingResult $this$forEach$iv$iv = players$iv2;
        boolean $i$f$forEach = false;
        Iterator iterator = $this$forEach$iv$iv.iterator();
        while (iterator.hasNext()) {
            Object element$iv$iv = iterator.next();
            ServerPlayer player$iv = (ServerPlayer)element$iv$iv;
            boolean bl = false;
            if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv, (Identifier)this_$iv2.getS2cLocation())) continue;
            ServerPlayNetworking.send((ServerPlayer)player$iv, (CustomPacketPayload)payload$iv);
        }
        ProfilingData data = ProfilingData.Companion.create$default(ProfilingData.Companion, this.timingsMap, this.blockTimingsMap, ticks, null, 8, null);
        Observable.RESULTS = data;
        Observable.INSTANCE.getLOGGER().info("Profiler ran for " + ticks + " ticks, sending data");
        String link = this.uploadProfile(data, diagnostics);
        msg$iv2 = Observable.INSTANCE.getCHANNEL();
        Iterable $i$f$sendToPlayers22 = (Iterable)playerList2;
        $this$forEach$iv$iv = new S2CPacket.ProfilingResult(data, link);
        boolean $i$f$sendToPlayers = false;
        String string2 = S2CPacket.ProfilingResult.class.getName();
        Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"getName(...)");
        byte[] byArray2 = Json.Default.encodeToString((SerializationStrategy)S2CPacket.ProfilingResult.Companion.serializer(), (Object)msg$iv).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue((Object)byArray2, (String)"getBytes(...)");
        BetterChannel.SerializedPayload payload$iv2 = new BetterChannel.SerializedPayload(string2, byArray2, this_$iv.getS2cLocation());
        Iterable $this$forEach$iv$iv2 = players$iv;
        boolean $i$f$forEach22 = false;
        for (Object element$iv$iv : $this$forEach$iv$iv2) {
            ServerPlayer player$iv = (ServerPlayer)element$iv$iv;
            boolean bl = false;
            if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv, (Identifier)this_$iv.getS2cLocation())) continue;
            ServerPlayNetworking.send((ServerPlayer)player$iv, (CustomPacketPayload)payload$iv2);
        }
        Observable.INSTANCE.getLOGGER().info("Data transfer complete!");
        MinecraftServer minecraftServer = Observable.INSTANCE.getSERVER_INSTANCE();
        if (minecraftServer != null && (playerList = minecraftServer.getPlayerList()) != null && (list = playerList.getPlayers()) != null) {
            void players$iv3;
            void this_$iv3;
            void $this$filterTo$iv$iv;
            void $this$filter$iv;
            $this$forEach$iv$iv2 = list;
            boolean $i$f$filter = false;
            void $i$f$forEach22 = $this$filter$iv;
            Object destination$iv$iv = new ArrayList();
            boolean $i$f$filterTo22 = false;
            for (Object element$iv$iv : $this$filterTo$iv$iv) {
                ServerPlayer it = (ServerPlayer)element$iv$iv;
                boolean bl = false;
                Intrinsics.checkNotNull((Object)it);
                if (!Observable.INSTANCE.hasPermission((Player)it)) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            List it = (List)destination$iv$iv;
            boolean bl = false;
            destination$iv$iv = Observable.INSTANCE.getCHANNEL();
            Iterable $i$f$filterTo22 = it;
            S2CPacket.ProfilerInactive msg$iv3 = S2CPacket.ProfilerInactive.INSTANCE;
            boolean $i$f$sendToPlayers3 = false;
            String string3 = S2CPacket.ProfilerInactive.class.getName();
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"getName(...)");
            byte[] byArray3 = Json.Default.encodeToString((SerializationStrategy)S2CPacket.ProfilerInactive.INSTANCE.serializer(), (Object)msg$iv3).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue((Object)byArray3, (String)"getBytes(...)");
            BetterChannel.SerializedPayload payload$iv3 = new BetterChannel.SerializedPayload(string3, byArray3, this_$iv3.getS2cLocation());
            void $this$forEach$iv$iv3 = players$iv3;
            boolean $i$f$forEach3 = false;
            for (Object element$iv$iv : $this$forEach$iv$iv3) {
                ServerPlayer player$iv = (ServerPlayer)element$iv$iv;
                boolean bl2 = false;
                if (!ServerPlayNetworking.canSend((ServerPlayer)player$iv, (Identifier)this_$iv3.getS2cLocation())) continue;
                ServerPlayNetworking.send((ServerPlayer)player$iv, (CustomPacketPayload)payload$iv3);
            }
        }
    }

    public final void init() {
        NativeProfiler.setBlockEntityTicker((arg_0, arg_1) -> Profiler.init$lambda$0(this, arg_0, arg_1));
        NativeProfiler.setEntityTicker((arg_0, arg_1) -> Profiler.init$lambda$1(this, arg_0, arg_1));
        NativeProfiler.setBlockTicker((arg_0, arg_1, arg_2, arg_3) -> Profiler.init$lambda$2(this, arg_0, arg_1, arg_2, arg_3));
        NativeProfiler.setFluidTicker((arg_0, arg_1, arg_2, arg_3) -> Profiler.init$lambda$3(this, arg_0, arg_1, arg_2, arg_3));
    }

    private static final void init$lambda$0(Profiler this$0, TickingBlockEntity ticker, Level level) {
        NativeTimingData nativeTimingData;
        if (this$0.getNotProcessing()) {
            ticker.tick();
            return;
        }
        Intrinsics.checkNotNull((Object)ticker);
        Intrinsics.checkNotNull((Object)level);
        NativeTimingData timing = this$0.processBlockEntity(ticker, level);
        Props.currentTarget.set(timing);
        long start = System.nanoTime();
        ticker.tick();
        long end = System.nanoTime();
        Props.currentTarget.set(null);
        NativeTimingData $this$init_u24lambda_u240_u240 = nativeTimingData = timing;
        boolean bl = false;
        $this$init_u24lambda_u240_u240.time += end - start;
        int n = $this$init_u24lambda_u240_u240.ticks;
        $this$init_u24lambda_u240_u240.ticks = n + 1;
    }

    private static final void init$lambda$1(Profiler this$0, Entity entity, Consumer tickMethod) {
        NativeTimingData nativeTimingData;
        if (this$0.getNotProcessing()) {
            tickMethod.accept(entity);
            return;
        }
        Intrinsics.checkNotNull((Object)entity);
        NativeTimingData timing = this$0.process(entity);
        Props.currentTarget.set(timing);
        long start = System.nanoTime();
        tickMethod.accept(entity);
        long end = System.nanoTime();
        Props.currentTarget.set(null);
        NativeTimingData $this$init_u24lambda_u241_u240 = nativeTimingData = timing;
        boolean bl = false;
        $this$init_u24lambda_u241_u240.time += end - start;
        int n = $this$init_u24lambda_u241_u240.ticks;
        $this$init_u24lambda_u241_u240.ticks = n + 1;
    }

    private static final void init$lambda$2(Profiler this$0, BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        NativeTimingData nativeTimingData;
        if (this$0.getNotProcessing()) {
            state.tick(level, pos, random);
            return;
        }
        Intrinsics.checkNotNull((Object)state);
        Intrinsics.checkNotNull((Object)pos);
        Intrinsics.checkNotNull((Object)level);
        NativeTimingData timing = this$0.processBlock(state, pos, (Level)level);
        Props.currentTarget.set(timing);
        long start = System.nanoTime();
        state.tick(level, pos, random);
        long end = System.nanoTime();
        Props.currentTarget.set(null);
        NativeTimingData $this$init_u24lambda_u242_u240 = nativeTimingData = timing;
        boolean bl = false;
        $this$init_u24lambda_u242_u240.time += end - start;
        int n = $this$init_u24lambda_u242_u240.ticks;
        $this$init_u24lambda_u242_u240.ticks = n + 1;
    }

    private static final void init$lambda$3(Profiler this$0, FluidState state, ServerLevel level, BlockPos pos, BlockState blockState) {
        NativeTimingData nativeTimingData;
        if (this$0.getNotProcessing()) {
            state.tick(level, pos, blockState);
            return;
        }
        Intrinsics.checkNotNull((Object)state);
        Intrinsics.checkNotNull((Object)pos);
        Intrinsics.checkNotNull((Object)level);
        NativeTimingData timing = this$0.processFluid(state, pos, (Level)level);
        Props.currentTarget.set(timing);
        long start = System.nanoTime();
        state.tick(level, pos, blockState);
        long end = System.nanoTime();
        Props.currentTarget.set(null);
        NativeTimingData $this$init_u24lambda_u243_u240 = nativeTimingData = timing;
        boolean bl = false;
        $this$init_u24lambda_u243_u240.time += end - start;
        int n = $this$init_u24lambda_u243_u240.ticks;
        $this$init_u24lambda_u243_u240.ticks = n + 1;
    }
}

