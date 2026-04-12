/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.LazyThreadSafetyMode
 *  kotlin.Metadata
 *  kotlin.TuplesKt
 *  kotlin.collections.CollectionsKt
 *  kotlin.collections.MapsKt
 *  kotlin.comparisons.ComparisonsKt
 *  kotlin.jvm.JvmField
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlinx.serialization.KSerializer
 *  kotlinx.serialization.Serializable
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.CompositeEncoder
 *  kotlinx.serialization.internal.ArrayListSerializer
 *  kotlinx.serialization.internal.IntSerializer
 *  kotlinx.serialization.internal.LinkedHashMapSerializer
 *  kotlinx.serialization.internal.PluginExceptionsKt
 *  kotlinx.serialization.internal.SerializationConstructorMarker
 *  kotlinx.serialization.internal.StringSerializer
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.Identifier
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import observable.net.BlockPosSerializer;
import observable.net.ResourceLocationSerializer;
import observable.server.NativeTimingData;
import observable.server.ProfilingData$;
import observable.server.ProfilingData$Entry$;
import observable.server.ProfilingData$SerializedStackTrace$;
import observable.server.ProfilingData$SerializedTraceMap$;
import observable.server.ProfilingDataKt;
import observable.server.Remapper;
import observable.server.TraceMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0087\b\u0018\u0000 ,2\u00020\u0001:\u0005,-./0BM\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\b\f\u0010\rBe\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\u001a\u0010\u0007\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u00a2\u0006\u0004\b\f\u0010\u0011J\u001b\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u00c6\u0003J\u001b\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u000bH\u00c6\u0003JW\u0010\u001d\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00032\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010!\u001a\u00020\u000bH\u00d6\u0081\u0004J\n\u0010\"\u001a\u00020#H\u00d6\u0081\u0004J%\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00002\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0001\u00a2\u0006\u0002\b+R#\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u00061"}, d2={"Lobservable/server/ProfilingData;", "", "entities", "", "Lnet/minecraft/resources/Identifier;", "", "Lobservable/server/ProfilingData$Entry;", "blocks", "traces", "Lobservable/server/ProfilingData$SerializedTraceMap;", "ticks", "", "<init>", "(Ljava/util/Map;Ljava/util/Map;Lobservable/server/ProfilingData$SerializedTraceMap;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Lobservable/server/ProfilingData$SerializedTraceMap;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEntities", "()Ljava/util/Map;", "getBlocks", "getTraces", "()Lobservable/server/ProfilingData$SerializedTraceMap;", "getTicks", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "Companion", "Entry", "SerializedStackTrace", "SerializedTraceMap", "$serializer", "observable"})
public final class ProfilingData {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Map<Identifier, List<Entry>> entities;
    @NotNull
    private final Map<Identifier, List<Entry>> blocks;
    @Nullable
    private final SerializedTraceMap traces;
    private final int ticks;
    @JvmField
    @NotNull
    private static final Lazy<KSerializer<Object>>[] $childSerializers;

    public ProfilingData(@NotNull Map<Identifier, ? extends List<Entry>> entities, @NotNull Map<Identifier, ? extends List<Entry>> blocks, @Nullable SerializedTraceMap traces, int ticks) {
        Intrinsics.checkNotNullParameter(entities, (String)"entities");
        Intrinsics.checkNotNullParameter(blocks, (String)"blocks");
        this.entities = entities;
        this.blocks = blocks;
        this.traces = traces;
        this.ticks = ticks;
    }

    @NotNull
    public final Map<Identifier, List<Entry>> getEntities() {
        return this.entities;
    }

    @NotNull
    public final Map<Identifier, List<Entry>> getBlocks() {
        return this.blocks;
    }

    @Nullable
    public final SerializedTraceMap getTraces() {
        return this.traces;
    }

    public final int getTicks() {
        return this.ticks;
    }

    @NotNull
    public final Map<Identifier, List<Entry>> component1() {
        return this.entities;
    }

    @NotNull
    public final Map<Identifier, List<Entry>> component2() {
        return this.blocks;
    }

    @Nullable
    public final SerializedTraceMap component3() {
        return this.traces;
    }

    public final int component4() {
        return this.ticks;
    }

    @NotNull
    public final ProfilingData copy(@NotNull Map<Identifier, ? extends List<Entry>> entities, @NotNull Map<Identifier, ? extends List<Entry>> blocks, @Nullable SerializedTraceMap traces, int ticks) {
        Intrinsics.checkNotNullParameter(entities, (String)"entities");
        Intrinsics.checkNotNullParameter(blocks, (String)"blocks");
        return new ProfilingData(entities, blocks, traces, ticks);
    }

    public static /* synthetic */ ProfilingData copy$default(ProfilingData profilingData, Map map, Map map2, SerializedTraceMap serializedTraceMap, int n, int n2, Object object) {
        if ((n2 & 1) != 0) {
            map = profilingData.entities;
        }
        if ((n2 & 2) != 0) {
            map2 = profilingData.blocks;
        }
        if ((n2 & 4) != 0) {
            serializedTraceMap = profilingData.traces;
        }
        if ((n2 & 8) != 0) {
            n = profilingData.ticks;
        }
        return profilingData.copy(map, map2, serializedTraceMap, n);
    }

    @NotNull
    public String toString() {
        return "ProfilingData(entities=" + this.entities + ", blocks=" + this.blocks + ", traces=" + this.traces + ", ticks=" + this.ticks + ")";
    }

    public int hashCode() {
        int result = ((Object)this.entities).hashCode();
        result = result * 31 + ((Object)this.blocks).hashCode();
        result = result * 31 + (this.traces == null ? 0 : this.traces.hashCode());
        result = result * 31 + Integer.hashCode(this.ticks);
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProfilingData)) {
            return false;
        }
        ProfilingData profilingData = (ProfilingData)other;
        if (!Intrinsics.areEqual(this.entities, profilingData.entities)) {
            return false;
        }
        if (!Intrinsics.areEqual(this.blocks, profilingData.blocks)) {
            return false;
        }
        if (!Intrinsics.areEqual((Object)this.traces, (Object)profilingData.traces)) {
            return false;
        }
        return this.ticks == profilingData.ticks;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$observable(ProfilingData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArray = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, (SerializationStrategy)lazyArray[0].getValue(), self.entities);
        output.encodeSerializableElement(serialDesc, 1, (SerializationStrategy)lazyArray[1].getValue(), self.blocks);
        output.encodeNullableSerializableElement(serialDesc, 2, (SerializationStrategy)SerializedTraceMap$$serializer.INSTANCE, (Object)self.traces);
        output.encodeIntElement(serialDesc, 3, self.ticks);
    }

    public /* synthetic */ ProfilingData(int seen0, Map entities, Map blocks, SerializedTraceMap traces, int ticks, SerializationConstructorMarker serializationConstructorMarker) {
        if (15 != (0xF & seen0)) {
            PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)15, (SerialDescriptor)$serializer.INSTANCE.getDescriptor());
        }
        this.entities = entities;
        this.blocks = blocks;
        this.traces = traces;
        this.ticks = ticks;
    }

    public static final /* synthetic */ Lazy[] access$get$childSerializers$cp() {
        return $childSerializers;
    }

    static {
        Lazy[] lazyArray = new Lazy[]{LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new LinkedHashMapSerializer((KSerializer)new ResourceLocationSerializer(), (KSerializer)new ArrayListSerializer((KSerializer)Entry$$serializer.INSTANCE))), LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new LinkedHashMapSerializer((KSerializer)new ResourceLocationSerializer(), (KSerializer)new ArrayListSerializer((KSerializer)Entry$$serializer.INSTANCE))), null, null};
        $childSerializers = lazyArray;
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JT\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072$\u0010\n\u001a \u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\u00070\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013\u00a8\u0006\u0014"}, d2={"Lobservable/server/ProfilingData$Companion;", "", "<init>", "()V", "create", "Lobservable/server/ProfilingData;", "entities", "", "Lnet/minecraft/world/entity/Entity;", "Lobservable/server/NativeTimingData;", "blocks", "Lnet/minecraft/resources/ResourceKey;", "Lnet/minecraft/world/level/Level;", "Lnet/minecraft/core/BlockPos;", "ticks", "", "traceMap", "Lobservable/server/TraceMap;", "serializer", "Lkotlinx/serialization/KSerializer;", "observable"})
    @SourceDebugExtension(value={"SMAP\nProfilingData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProfilingData.kt\nobservable/server/ProfilingData$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,134:1\n1512#2:135\n1538#2,3:136\n1541#2,3:146\n1266#2,2:151\n1586#2:153\n1661#2,3:154\n1269#2:157\n383#3,7:139\n466#3:149\n415#3:150\n129#4:158\n158#4,2:159\n129#4:161\n158#4,3:162\n160#4:165\n1#5:166\n*S KotlinDebug\n*F\n+ 1 ProfilingData.kt\nobservable/server/ProfilingData$Companion\n*L\n45#1:135\n45#1:136,3\n45#1:146,3\n46#1:151,2\n47#1:153\n47#1:154,3\n46#1:157\n45#1:139,7\n46#1:149\n46#1:150\n54#1:158\n54#1:159,2\n55#1:161\n55#1:162,3\n54#1:165\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        /*
         * WARNING - void declaration
         */
        @NotNull
        public final ProfilingData create(@NotNull Map<Entity, ? extends NativeTimingData> entities, @NotNull Map<ResourceKey<Level>, ? extends Map<BlockPos, ? extends NativeTimingData>> blocks, int ticks, @Nullable TraceMap traceMap) {
            SerializedTraceMap serializedTraceMap;
            void $this$mapTo$iv$iv;
            void $this$map$iv;
            Object object;
            Object object2;
            List entries;
            Object object3;
            Map.Entry entry;
            void $this$associateByTo$iv$iv$iv;
            void $this$mapValuesTo$iv$iv;
            void $this$mapValues$iv;
            Map.Entry $this$getOrPut$iv$iv$iv;
            Object key$iv$iv$iv;
            void $this$groupByTo$iv$iv;
            Map $this$groupBy$iv;
            Intrinsics.checkNotNullParameter(entities, (String)"entities");
            Intrinsics.checkNotNullParameter(blocks, (String)"blocks");
            Iterable iterable = entities.entrySet();
            boolean $i$f$groupBy = false;
            void var8_8 = $this$groupBy$iv;
            Map destination$iv$iv = new LinkedHashMap();
            boolean $i$f$groupByTo = false;
            for (Object element$iv$iv : $this$groupByTo$iv$iv) {
                Object object4;
                Map.Entry it = (Map.Entry)element$iv$iv;
                boolean bl = false;
                Identifier key$iv$iv = ((Entity)it.getKey()).level().dimension().identifier();
                Map map = destination$iv$iv;
                key$iv$iv$iv = key$iv$iv;
                boolean $i$f$getOrPut = false;
                Object value$iv$iv$iv = $this$getOrPut$iv$iv$iv.get(key$iv$iv$iv);
                if (value$iv$iv$iv == null) {
                    boolean bl2 = false;
                    List answer$iv$iv$iv = new ArrayList();
                    $this$getOrPut$iv$iv$iv.put(key$iv$iv$iv, answer$iv$iv$iv);
                    object4 = answer$iv$iv$iv;
                } else {
                    object4 = value$iv$iv$iv;
                }
                List list$iv$iv = (List)object4;
                list$iv$iv.add(element$iv$iv);
            }
            $this$groupBy$iv = destination$iv$iv;
            boolean $i$f$mapValues22 = false;
            $this$groupByTo$iv$iv = $this$mapValues$iv;
            destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity((int)$this$mapValues$iv.size()));
            boolean $i$f$mapValuesTo = false;
            Iterable iterable2 = $this$mapValuesTo$iv$iv.entrySet();
            Map destination$iv$iv$iv = destination$iv$iv;
            boolean $i$f$associateByTo = false;
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                void $this$mapTo$iv$iv2;
                void it$iv$iv;
                $this$getOrPut$iv$iv$iv = (Map.Entry)element$iv$iv$iv;
                key$iv$iv$iv = destination$iv$iv$iv;
                boolean bl5 = false;
                entry = (Map.Entry)element$iv$iv$iv;
                Object k = it$iv$iv.getKey();
                object3 = key$iv$iv$iv;
                boolean bl3 = false;
                entries = (List)entry.getValue();
                Iterable $this$map$iv2 = entries;
                boolean $i$f$map = false;
                object2 = $this$map$iv2;
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map$iv2, (int)10));
                boolean $i$f$mapTo = false;
                for (Object item$iv$iv : $this$mapTo$iv$iv2) {
                    Map.Entry entry2 = (Map.Entry)item$iv$iv;
                    Collection collection = destination$iv$iv2;
                    boolean bl4 = false;
                    Entity entity = (Entity)entry2.getKey();
                    NativeTimingData data = (NativeTimingData)entry2.getValue();
                    String string = BuiltInRegistries.ENTITY_TYPE.getKey((Object)entity.getType()).toString();
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
                    collection.add(new Entry(entity, string, data));
                }
                object = (List)destination$iv$iv2;
                object3.put(k, object);
            }
            Map entityEntries = destination$iv$iv$iv;
            Map<ResourceKey<Level>, ? extends Map<BlockPos, ? extends NativeTimingData>> $i$f$mapValues22 = blocks;
            boolean $i$f$map3 = false;
            destination$iv$iv = $this$map$iv;
            Collection destination$iv$iv3 = new ArrayList($this$map$iv.size());
            boolean $i$f$mapTo = false;
            for (Map.Entry item$iv$iv : $this$mapTo$iv$iv.entrySet()) {
                void $this$mapTo$iv$iv3;
                void $this$map$iv3;
                Map posMap;
                Map.Entry entry3 = item$iv$iv;
                object3 = destination$iv$iv3;
                boolean bl = false;
                ResourceKey level = (ResourceKey)entry3.getKey();
                Map bl5 = posMap = (Map)entry3.getValue();
                entry = level.identifier();
                boolean $i$f$map2 = false;
                entries = $this$map$iv3;
                Collection destination$iv$iv4 = new ArrayList($this$map$iv3.size());
                boolean $i$f$mapTo2 = false;
                object2 = $this$mapTo$iv$iv3.entrySet().iterator();
                while (object2.hasNext()) {
                    Map.Entry item$iv$iv2;
                    Map.Entry entry4 = item$iv$iv2 = (Map.Entry)object2.next();
                    Collection collection = destination$iv$iv4;
                    boolean bl6 = false;
                    BlockPos pos = (BlockPos)entry4.getKey();
                    NativeTimingData data = (NativeTimingData)entry4.getValue();
                    String string = data.name;
                    Intrinsics.checkNotNullExpressionValue((Object)string, (String)"name");
                    collection.add(new Entry(pos, string, data));
                }
                object3.add(TuplesKt.to((Object)entry, (Object)((List)destination$iv$iv4)));
            }
            Map blockEntries = MapsKt.toMap((Iterable)((List)destination$iv$iv3));
            Map map = entityEntries;
            Map map2 = blockEntries;
            TraceMap traceMap2 = traceMap;
            if (traceMap2 != null) {
                void it;
                TraceMap $i$f$map3 = traceMap2;
                Map map3 = map2;
                object = map;
                boolean bl = false;
                SerializedTraceMap serializedTraceMap2 = SerializedTraceMap.Companion.create((TraceMap)it);
                map = object;
                map2 = map3;
                serializedTraceMap = serializedTraceMap2;
            } else {
                serializedTraceMap = null;
            }
            int n = ticks;
            SerializedTraceMap serializedTraceMap3 = serializedTraceMap;
            Map map4 = map2;
            Map map5 = map;
            return new ProfilingData(map5, map4, serializedTraceMap3, n);
        }

        public static /* synthetic */ ProfilingData create$default(Companion companion, Map map, Map map2, int n, TraceMap traceMap, int n2, Object object) {
            if ((n2 & 8) != 0) {
                traceMap = null;
            }
            return companion.create(map, map2, n, traceMap);
        }

        @NotNull
        public final KSerializer<ProfilingData> serializer() {
            return (KSerializer)$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 :2\u00020\u0001:\u00029:B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eB!\b\u0016\u0012\u0006\u0010\u000f\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\u0004\b\r\u0010\u0012BS\b\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0004\b\r\u0010\u0016J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\t\u0010'\u001a\u00020\tH\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\fH\u00c6\u0003JL\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001\u00a2\u0006\u0002\u0010+J\u0014\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010/\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u00100\u001a\u00020\u0007H\u00d6\u0081\u0004J%\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0001\u00a2\u0006\u0002\b8R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#\u00a8\u0006;"}, d2={"Lobservable/server/ProfilingData$Entry;", "", "entityId", "", "position", "Lnet/minecraft/core/BlockPos;", "type", "", "rate", "", "ticks", "traces", "Lobservable/server/ProfilingData$SerializedTraceMap;", "<init>", "(Ljava/lang/Integer;Lnet/minecraft/core/BlockPos;Ljava/lang/String;DILobservable/server/ProfilingData$SerializedTraceMap;)V", "obj", "data", "Lobservable/server/NativeTimingData;", "(Ljava/lang/Object;Ljava/lang/String;Lobservable/server/NativeTimingData;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Integer;Lnet/minecraft/core/BlockPos;Ljava/lang/String;DILobservable/server/ProfilingData$SerializedTraceMap;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEntityId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPosition", "()Lnet/minecraft/core/BlockPos;", "getType", "()Ljava/lang/String;", "getRate", "()D", "getTicks", "()I", "getTraces", "()Lobservable/server/ProfilingData$SerializedTraceMap;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Integer;Lnet/minecraft/core/BlockPos;Ljava/lang/String;DILobservable/server/ProfilingData$SerializedTraceMap;)Lobservable/server/ProfilingData$Entry;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    public static final class Entry {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @Nullable
        private final Integer entityId;
        @NotNull
        private final BlockPos position;
        @NotNull
        private final String type;
        private final double rate;
        private final int ticks;
        @NotNull
        private final SerializedTraceMap traces;
        @JvmField
        @NotNull
        private static final Lazy<KSerializer<Object>>[] $childSerializers;

        public Entry(@Nullable Integer entityId, @NotNull BlockPos position, @NotNull String type, double rate, int ticks, @NotNull SerializedTraceMap traces) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)type, (String)"type");
            Intrinsics.checkNotNullParameter((Object)traces, (String)"traces");
            this.entityId = entityId;
            this.position = position;
            this.type = type;
            this.rate = rate;
            this.ticks = ticks;
            this.traces = traces;
        }

        public /* synthetic */ Entry(Integer n, BlockPos blockPos, String string, double d, int n2, SerializedTraceMap serializedTraceMap, int n3, DefaultConstructorMarker defaultConstructorMarker) {
            if ((n3 & 1) != 0) {
                n = null;
            }
            this(n, blockPos, string, d, n2, serializedTraceMap);
        }

        @Nullable
        public final Integer getEntityId() {
            return this.entityId;
        }

        @NotNull
        public final BlockPos getPosition() {
            return this.position;
        }

        @NotNull
        public final String getType() {
            return this.type;
        }

        public final double getRate() {
            return this.rate;
        }

        public final int getTicks() {
            return this.ticks;
        }

        @NotNull
        public final SerializedTraceMap getTraces() {
            return this.traces;
        }

        public Entry(@NotNull Object obj, @NotNull String type, @NotNull NativeTimingData data) {
            Intrinsics.checkNotNullParameter((Object)obj, (String)"obj");
            Intrinsics.checkNotNullParameter((Object)type, (String)"type");
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            Entity entity = obj instanceof Entity ? (Entity)obj : null;
            Integer n = entity != null ? Integer.valueOf(entity.getId()) : null;
            BlockPos blockPos = ProfilingDataKt.getPosition(obj);
            double d = (double)data.time / (double)data.ticks;
            int n2 = data.ticks;
            Object object = data.traces;
            Intrinsics.checkNotNull((Object)object, (String)"null cannot be cast to non-null type observable.server.TraceMap");
            this(n, blockPos, type, d, n2, SerializedTraceMap.Companion.create((TraceMap)object));
        }

        @Nullable
        public final Integer component1() {
            return this.entityId;
        }

        @NotNull
        public final BlockPos component2() {
            return this.position;
        }

        @NotNull
        public final String component3() {
            return this.type;
        }

        public final double component4() {
            return this.rate;
        }

        public final int component5() {
            return this.ticks;
        }

        @NotNull
        public final SerializedTraceMap component6() {
            return this.traces;
        }

        @NotNull
        public final Entry copy(@Nullable Integer entityId, @NotNull BlockPos position, @NotNull String type, double rate, int ticks, @NotNull SerializedTraceMap traces) {
            Intrinsics.checkNotNullParameter((Object)position, (String)"position");
            Intrinsics.checkNotNullParameter((Object)type, (String)"type");
            Intrinsics.checkNotNullParameter((Object)traces, (String)"traces");
            return new Entry(entityId, position, type, rate, ticks, traces);
        }

        public static /* synthetic */ Entry copy$default(Entry entry, Integer n, BlockPos blockPos, String string, double d, int n2, SerializedTraceMap serializedTraceMap, int n3, Object object) {
            if ((n3 & 1) != 0) {
                n = entry.entityId;
            }
            if ((n3 & 2) != 0) {
                blockPos = entry.position;
            }
            if ((n3 & 4) != 0) {
                string = entry.type;
            }
            if ((n3 & 8) != 0) {
                d = entry.rate;
            }
            if ((n3 & 0x10) != 0) {
                n2 = entry.ticks;
            }
            if ((n3 & 0x20) != 0) {
                serializedTraceMap = entry.traces;
            }
            return entry.copy(n, blockPos, string, d, n2, serializedTraceMap);
        }

        @NotNull
        public String toString() {
            return "Entry(entityId=" + this.entityId + ", position=" + this.position + ", type=" + this.type + ", rate=" + this.rate + ", ticks=" + this.ticks + ", traces=" + this.traces + ")";
        }

        public int hashCode() {
            int result = this.entityId == null ? 0 : ((Object)this.entityId).hashCode();
            result = result * 31 + this.position.hashCode();
            result = result * 31 + this.type.hashCode();
            result = result * 31 + Double.hashCode(this.rate);
            result = result * 31 + Integer.hashCode(this.ticks);
            result = result * 31 + this.traces.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry)other;
            if (!Intrinsics.areEqual((Object)this.entityId, (Object)entry.entityId)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.position, (Object)entry.position)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.type, (Object)entry.type)) {
                return false;
            }
            if (Double.compare(this.rate, entry.rate) != 0) {
                return false;
            }
            if (this.ticks != entry.ticks) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.traces, (Object)entry.traces);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(Entry self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArray = $childSerializers;
            if (output.shouldEncodeElementDefault(serialDesc, 0) ? true : self.entityId != null) {
                output.encodeNullableSerializableElement(serialDesc, 0, (SerializationStrategy)IntSerializer.INSTANCE, (Object)self.entityId);
            }
            output.encodeSerializableElement(serialDesc, 1, (SerializationStrategy)lazyArray[1].getValue(), (Object)self.position);
            output.encodeStringElement(serialDesc, 2, self.type);
            output.encodeDoubleElement(serialDesc, 3, self.rate);
            output.encodeIntElement(serialDesc, 4, self.ticks);
            output.encodeSerializableElement(serialDesc, 5, (SerializationStrategy)SerializedTraceMap$$serializer.INSTANCE, (Object)self.traces);
        }

        public /* synthetic */ Entry(int seen0, Integer entityId, BlockPos position, String type, double rate, int ticks, SerializedTraceMap traces, SerializationConstructorMarker serializationConstructorMarker) {
            if (62 != (0x3E & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)62, (SerialDescriptor)Entry$$serializer.INSTANCE.getDescriptor());
            }
            this.entityId = (seen0 & 1) == 0 ? null : entityId;
            this.position = position;
            this.type = type;
            this.rate = rate;
            this.ticks = ticks;
            this.traces = traces;
        }

        public static final /* synthetic */ Lazy[] access$get$childSerializers$cp() {
            return $childSerializers;
        }

        static {
            Lazy[] lazyArray = new Lazy[]{null, LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> new BlockPosSerializer()), null, null, null, null};
            $childSerializers = lazyArray;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/server/ProfilingData$Entry$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/server/ProfilingData$Entry;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<Entry> serializer() {
                return (KSerializer)Entry$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002)*B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0004\b\b\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0004\b\b\u0010\fBA\b\u0010\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0004\b\b\u0010\u0010J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J3\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u001f\u001a\u00020\u0006H\u00d6\u0081\u0004J\n\u0010 \u001a\u00020\u0003H\u00d6\u0081\u0004J%\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0001\u00a2\u0006\u0002\b(R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012\u00a8\u0006+"}, d2={"Lobservable/server/ProfilingData$SerializedStackTrace;", "", "classname", "", "fileName", "lineNumber", "", "methodName", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "el", "Ljava/lang/StackTraceElement;", "(Ljava/lang/StackTraceElement;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getClassname", "()Ljava/lang/String;", "getFileName", "getLineNumber", "()I", "getMethodName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    public static final class SerializedStackTrace {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final String classname;
        @Nullable
        private final String fileName;
        private final int lineNumber;
        @NotNull
        private final String methodName;

        public SerializedStackTrace(@NotNull String classname, @Nullable String fileName, int lineNumber, @NotNull String methodName) {
            Intrinsics.checkNotNullParameter((Object)classname, (String)"classname");
            Intrinsics.checkNotNullParameter((Object)methodName, (String)"methodName");
            this.classname = classname;
            this.fileName = fileName;
            this.lineNumber = lineNumber;
            this.methodName = methodName;
        }

        @NotNull
        public final String getClassname() {
            return this.classname;
        }

        @Nullable
        public final String getFileName() {
            return this.fileName;
        }

        public final int getLineNumber() {
            return this.lineNumber;
        }

        @NotNull
        public final String getMethodName() {
            return this.methodName;
        }

        public SerializedStackTrace(@NotNull StackTraceElement el) {
            Intrinsics.checkNotNullParameter((Object)el, (String)"el");
            String string = el.getClassName();
            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"getClassName(...)");
            String string2 = el.getFileName();
            int n = el.getLineNumber();
            String string3 = el.getMethodName();
            Intrinsics.checkNotNullExpressionValue((Object)string3, (String)"getMethodName(...)");
            this(string, string2, n, string3);
        }

        @NotNull
        public final String component1() {
            return this.classname;
        }

        @Nullable
        public final String component2() {
            return this.fileName;
        }

        public final int component3() {
            return this.lineNumber;
        }

        @NotNull
        public final String component4() {
            return this.methodName;
        }

        @NotNull
        public final SerializedStackTrace copy(@NotNull String classname, @Nullable String fileName, int lineNumber, @NotNull String methodName) {
            Intrinsics.checkNotNullParameter((Object)classname, (String)"classname");
            Intrinsics.checkNotNullParameter((Object)methodName, (String)"methodName");
            return new SerializedStackTrace(classname, fileName, lineNumber, methodName);
        }

        public static /* synthetic */ SerializedStackTrace copy$default(SerializedStackTrace serializedStackTrace, String string, String string2, int n, String string3, int n2, Object object) {
            if ((n2 & 1) != 0) {
                string = serializedStackTrace.classname;
            }
            if ((n2 & 2) != 0) {
                string2 = serializedStackTrace.fileName;
            }
            if ((n2 & 4) != 0) {
                n = serializedStackTrace.lineNumber;
            }
            if ((n2 & 8) != 0) {
                string3 = serializedStackTrace.methodName;
            }
            return serializedStackTrace.copy(string, string2, n, string3);
        }

        @NotNull
        public String toString() {
            return "SerializedStackTrace(classname=" + this.classname + ", fileName=" + this.fileName + ", lineNumber=" + this.lineNumber + ", methodName=" + this.methodName + ")";
        }

        public int hashCode() {
            int result = this.classname.hashCode();
            result = result * 31 + (this.fileName == null ? 0 : this.fileName.hashCode());
            result = result * 31 + Integer.hashCode(this.lineNumber);
            result = result * 31 + this.methodName.hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SerializedStackTrace)) {
                return false;
            }
            SerializedStackTrace serializedStackTrace = (SerializedStackTrace)other;
            if (!Intrinsics.areEqual((Object)this.classname, (Object)serializedStackTrace.classname)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.fileName, (Object)serializedStackTrace.fileName)) {
                return false;
            }
            if (this.lineNumber != serializedStackTrace.lineNumber) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.methodName, (Object)serializedStackTrace.methodName);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(SerializedStackTrace self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.classname);
            output.encodeNullableSerializableElement(serialDesc, 1, (SerializationStrategy)StringSerializer.INSTANCE, (Object)self.fileName);
            output.encodeIntElement(serialDesc, 2, self.lineNumber);
            output.encodeStringElement(serialDesc, 3, self.methodName);
        }

        public /* synthetic */ SerializedStackTrace(int seen0, String classname, String fileName, int lineNumber, String methodName, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (0xF & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)15, (SerialDescriptor)SerializedStackTrace$$serializer.INSTANCE.getDescriptor());
            }
            this.classname = classname;
            this.fileName = fileName;
            this.lineNumber = lineNumber;
            this.methodName = methodName;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/server/ProfilingData$SerializedStackTrace$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/server/ProfilingData$SerializedStackTrace;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<SerializedStackTrace> serializer() {
                return (KSerializer)SerializedStackTrace$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 *2\u00020\u0001:\u0002*+B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0004\b\t\u0010\nBG\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0004\b\t\u0010\u000eJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J7\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u00c6\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010 \u001a\u00020\bH\u00d6\u0081\u0004J\n\u0010!\u001a\u00020\u0003H\u00d6\u0081\u0004J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001\u00a2\u0006\u0002\b)R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00038F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0010\u00a8\u0006,"}, d2={"Lobservable/server/ProfilingData$SerializedTraceMap;", "", "className", "", "methodName", "children", "", "count", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/util/List;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getClassName", "()Ljava/lang/String;", "getMethodName", "getChildren", "()Ljava/util/List;", "getCount", "()I", "classMethod", "getClassMethod", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "Companion", "$serializer", "observable"})
    public static final class SerializedTraceMap {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final String className;
        @NotNull
        private final String methodName;
        @NotNull
        private final List<SerializedTraceMap> children;
        private final int count;
        @JvmField
        @NotNull
        private static final Lazy<KSerializer<Object>>[] $childSerializers;

        public SerializedTraceMap(@NotNull String className, @NotNull String methodName, @NotNull List<SerializedTraceMap> children, int count) {
            Intrinsics.checkNotNullParameter((Object)className, (String)"className");
            Intrinsics.checkNotNullParameter((Object)methodName, (String)"methodName");
            Intrinsics.checkNotNullParameter(children, (String)"children");
            this.className = className;
            this.methodName = methodName;
            this.children = children;
            this.count = count;
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        @NotNull
        public final String getMethodName() {
            return this.methodName;
        }

        @NotNull
        public final List<SerializedTraceMap> getChildren() {
            return this.children;
        }

        public final int getCount() {
            return this.count;
        }

        @NotNull
        public final String getClassMethod() {
            return this.className + "." + this.methodName;
        }

        @NotNull
        public final String component1() {
            return this.className;
        }

        @NotNull
        public final String component2() {
            return this.methodName;
        }

        @NotNull
        public final List<SerializedTraceMap> component3() {
            return this.children;
        }

        public final int component4() {
            return this.count;
        }

        @NotNull
        public final SerializedTraceMap copy(@NotNull String className, @NotNull String methodName, @NotNull List<SerializedTraceMap> children, int count) {
            Intrinsics.checkNotNullParameter((Object)className, (String)"className");
            Intrinsics.checkNotNullParameter((Object)methodName, (String)"methodName");
            Intrinsics.checkNotNullParameter(children, (String)"children");
            return new SerializedTraceMap(className, methodName, children, count);
        }

        public static /* synthetic */ SerializedTraceMap copy$default(SerializedTraceMap serializedTraceMap, String string, String string2, List list, int n, int n2, Object object) {
            if ((n2 & 1) != 0) {
                string = serializedTraceMap.className;
            }
            if ((n2 & 2) != 0) {
                string2 = serializedTraceMap.methodName;
            }
            if ((n2 & 4) != 0) {
                list = serializedTraceMap.children;
            }
            if ((n2 & 8) != 0) {
                n = serializedTraceMap.count;
            }
            return serializedTraceMap.copy(string, string2, list, n);
        }

        @NotNull
        public String toString() {
            return "SerializedTraceMap(className=" + this.className + ", methodName=" + this.methodName + ", children=" + this.children + ", count=" + this.count + ")";
        }

        public int hashCode() {
            int result = this.className.hashCode();
            result = result * 31 + this.methodName.hashCode();
            result = result * 31 + ((Object)this.children).hashCode();
            result = result * 31 + Integer.hashCode(this.count);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SerializedTraceMap)) {
                return false;
            }
            SerializedTraceMap serializedTraceMap = (SerializedTraceMap)other;
            if (!Intrinsics.areEqual((Object)this.className, (Object)serializedTraceMap.className)) {
                return false;
            }
            if (!Intrinsics.areEqual((Object)this.methodName, (Object)serializedTraceMap.methodName)) {
                return false;
            }
            if (!Intrinsics.areEqual(this.children, serializedTraceMap.children)) {
                return false;
            }
            return this.count == serializedTraceMap.count;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(SerializedTraceMap self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Lazy<KSerializer<Object>>[] lazyArray = $childSerializers;
            output.encodeStringElement(serialDesc, 0, self.className);
            output.encodeStringElement(serialDesc, 1, self.methodName);
            output.encodeSerializableElement(serialDesc, 2, (SerializationStrategy)lazyArray[2].getValue(), self.children);
            output.encodeIntElement(serialDesc, 3, self.count);
        }

        public /* synthetic */ SerializedTraceMap(int seen0, String className, String methodName, List children, int count, SerializationConstructorMarker serializationConstructorMarker) {
            if (15 != (0xF & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)15, (SerialDescriptor)SerializedTraceMap$$serializer.INSTANCE.getDescriptor());
            }
            this.className = className;
            this.methodName = methodName;
            this.children = children;
            this.count = count;
        }

        public static final /* synthetic */ Lazy[] access$get$childSerializers$cp() {
            return $childSerializers;
        }

        static {
            Lazy[] lazyArray = new Lazy[]{null, null, LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new ArrayListSerializer((KSerializer)SerializedTraceMap$$serializer.INSTANCE)), null};
            $childSerializers = lazyArray;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a8\u0006\n"}, d2={"Lobservable/server/ProfilingData$SerializedTraceMap$Companion;", "", "<init>", "()V", "create", "Lobservable/server/ProfilingData$SerializedTraceMap;", "traceMap", "Lobservable/server/TraceMap;", "serializer", "Lkotlinx/serialization/KSerializer;", "observable"})
        @SourceDebugExtension(value={"SMAP\nProfilingData.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProfilingData.kt\nobservable/server/ProfilingData$SerializedTraceMap$Companion\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,134:1\n129#2:135\n158#2,3:136\n1080#3:139\n*S KotlinDebug\n*F\n+ 1 ProfilingData.kt\nobservable/server/ProfilingData$SerializedTraceMap$Companion\n*L\n118#1:135\n118#1:136,3\n122#1:139\n*E\n"})
        public static final class Companion {
            private Companion() {
            }

            /*
             * WARNING - void declaration
             */
            @NotNull
            public final SerializedTraceMap create(@NotNull TraceMap traceMap) {
                Collection<SerializedTraceMap> collection;
                void $this$mapTo$iv$iv;
                void $this$map$iv;
                Intrinsics.checkNotNullParameter((Object)traceMap, (String)"traceMap");
                Remapper.INSTANCE.transform(traceMap);
                Map<TraceMap.MapKey, TraceMap> map = traceMap.getChildren();
                String string = traceMap.getMethodName();
                String string2 = traceMap.getClassName();
                boolean $i$f$map = false;
                void var4_6 = $this$map$iv;
                Collection destination$iv$iv = new ArrayList($this$map$iv.size());
                boolean $i$f$mapTo = false;
                Iterator iterator = $this$mapTo$iv$iv.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry item$iv$iv;
                    Map.Entry entry = item$iv$iv = iterator.next();
                    collection = destination$iv$iv;
                    boolean bl = false;
                    TraceMap map2 = (TraceMap)entry.getValue();
                    Remapper.INSTANCE.transform(map2);
                    collection.add(Companion.create(map2));
                }
                collection = (List)destination$iv$iv;
                Iterable $this$sortedByDescending$iv = collection;
                boolean $i$f$sortedByDescending = false;
                int n = traceMap.getCount();
                List list = CollectionsKt.sortedWith((Iterable)$this$sortedByDescending$iv, (Comparator)new Comparator(){

                    public final int compare(T a, T b) {
                        SerializedTraceMap it = (SerializedTraceMap)b;
                        boolean bl = false;
                        Comparable comparable = Integer.valueOf(it.getCount());
                        it = (SerializedTraceMap)a;
                        Comparable comparable2 = comparable;
                        bl = false;
                        return ComparisonsKt.compareValues((Comparable)comparable2, (Comparable)Integer.valueOf(it.getCount()));
                    }
                });
                String string3 = string;
                String string4 = string2;
                return new SerializedTraceMap(string4, string3, list, n);
            }

            @NotNull
            public final KSerializer<SerializedTraceMap> serializer() {
                return (KSerializer)SerializedTraceMap$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

