/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.Metadata
 *  kotlin.collections.MapsKt
 *  kotlin.io.TextStreamsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlinx.serialization.json.Json
 *  kotlinx.serialization.json.JsonElement
 *  kotlinx.serialization.json.JsonElementKt
 *  kotlinx.serialization.json.JsonObject
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import observable.Observable;
import observable.server.ModLoader;
import observable.server.RemapperKt;
import observable.server.TraceMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0016"}, d2={"Lobservable/server/Remapper;", "", "<init>", "()V", "modLoader", "Lobservable/server/ModLoader;", "getModLoader", "()Lobservable/server/ModLoader;", "setModLoader", "(Lobservable/server/ModLoader;)V", "remappingData", "Lobservable/server/Remapper$RemappingData;", "getRemappingData", "()Lobservable/server/Remapper$RemappingData;", "remappingData$delegate", "Lkotlin/Lazy;", "init", "", "transform", "map", "Lobservable/server/TraceMap;", "RemappingData", "observable"})
@SourceDebugExtension(value={"SMAP\nRemapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Remapper.kt\nobservable/server/Remapper\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Remapper.kt\nobservable/server/RemapperKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,56:1\n1#2:57\n24#3:58\n24#3:65\n466#4:59\n415#4:60\n466#4:66\n415#4:67\n1266#5,4:61\n1266#5,4:68\n*S KotlinDebug\n*F\n+ 1 Remapper.kt\nobservable/server/Remapper\n*L\n35#1:58\n36#1:65\n35#1:59\n35#1:60\n36#1:66\n36#1:67\n35#1:61,4\n36#1:68,4\n*E\n"})
public final class Remapper {
    @NotNull
    public static final Remapper INSTANCE = new Remapper();
    @NotNull
    private static ModLoader modLoader = ModLoader.FABRIC;
    @NotNull
    private static final Lazy remappingData$delegate = LazyKt.lazy(Remapper::remappingData_delegate$lambda$0);

    private Remapper() {
    }

    @NotNull
    public final ModLoader getModLoader() {
        return modLoader;
    }

    public final void setModLoader(@NotNull ModLoader modLoader) {
        Intrinsics.checkNotNullParameter((Object)((Object)modLoader), (String)"<set-?>");
        Remapper.modLoader = modLoader;
    }

    @NotNull
    public final RemappingData getRemappingData() {
        Lazy lazy = remappingData$delegate;
        return (RemappingData)lazy.getValue();
    }

    public final void init() {
        modLoader = ModLoader.FABRIC;
    }

    public final void transform(@NotNull TraceMap map) {
        block1: {
            String it;
            Intrinsics.checkNotNullParameter((Object)map, (String)"map");
            String string = this.getRemappingData().getClasses().get(map.getClassName());
            if (string != null) {
                it = string;
                boolean bl = false;
                map.setClassName(it);
            }
            String string2 = this.getRemappingData().getMethods().get(map.getMethodName());
            if (string2 == null) break block1;
            it = string2;
            boolean bl = false;
            map.setMethodName(it);
        }
    }

    private static final RemappingData remappingData_delegate$lambda$0() {
        RemappingData remappingData;
        try {
            Map map;
            Map map2;
            String string;
            Map.Entry it$iv;
            boolean bl;
            Map map3;
            Object k;
            Map.Entry it$iv$iv$iv;
            boolean bl2;
            Map map4;
            Iterable $this$associateByTo$iv$iv$iv$iv;
            boolean $i$f$associateByTo;
            Map destination$iv$iv$iv$iv;
            Map $this$mapValuesTo$iv$iv$iv;
            boolean $i$f$mapValuesTo;
            Map destination$iv$iv$iv;
            boolean $i$f$mapValues;
            Map $this$mapValues$iv$iv;
            JsonElement $this$stringMap$iv;
            String string2 = RemapperKt.getMAPPING_URLS().get((Object)modLoader);
            Intrinsics.checkNotNull((Object)string2);
            URL uRL = new URL(string2);
            Charset charset = Charsets.UTF_8;
            byte[] byArray = TextStreamsKt.readBytes((URL)uRL);
            JsonObject jsonData = JsonElementKt.getJsonObject((JsonElement)Json.Default.parseToJsonElement(new String(byArray, charset)));
            if (modLoader == ModLoader.FABRIC) {
                $this$stringMap$iv = (JsonElement)jsonData.get((Object)"classes");
                boolean $i$f$getStringMap = false;
                JsonElement jsonElement = $this$stringMap$iv;
                if (jsonElement != null && (jsonElement = JsonElementKt.getJsonObject((JsonElement)jsonElement)) != null) {
                    $this$mapValues$iv$iv = (Map)jsonElement;
                    $i$f$mapValues = false;
                    Map map5 = $this$mapValues$iv$iv;
                    destination$iv$iv$iv = new LinkedHashMap(MapsKt.mapCapacity((int)$this$mapValues$iv$iv.size()));
                    $i$f$mapValuesTo = false;
                    Iterable iterable = $this$mapValuesTo$iv$iv$iv.entrySet();
                    destination$iv$iv$iv$iv = destination$iv$iv$iv;
                    $i$f$associateByTo = false;
                    for (Object element$iv$iv$iv$iv : $this$associateByTo$iv$iv$iv$iv) {
                        Map.Entry entry = (Map.Entry)element$iv$iv$iv$iv;
                        map4 = destination$iv$iv$iv$iv;
                        bl2 = false;
                        Map.Entry entry2 = (Map.Entry)element$iv$iv$iv$iv;
                        k = it$iv$iv$iv.getKey();
                        map3 = map4;
                        bl = false;
                        string = JsonElementKt.getJsonPrimitive((JsonElement)((JsonElement)it$iv.getValue())).getContent();
                        map3.put(k, string);
                    }
                    map2 = destination$iv$iv$iv$iv;
                } else {
                    map2 = MapsKt.emptyMap();
                }
            } else {
                map2 = MapsKt.emptyMap();
            }
            $this$stringMap$iv = (JsonElement)jsonData.get((Object)"methods");
            Map map6 = map2;
            boolean $i$f$getStringMap = false;
            JsonElement jsonElement = $this$stringMap$iv;
            if (jsonElement != null && (jsonElement = JsonElementKt.getJsonObject((JsonElement)jsonElement)) != null) {
                $this$mapValues$iv$iv = (Map)jsonElement;
                $i$f$mapValues = false;
                $this$mapValuesTo$iv$iv$iv = $this$mapValues$iv$iv;
                destination$iv$iv$iv = new LinkedHashMap(MapsKt.mapCapacity((int)$this$mapValues$iv$iv.size()));
                $i$f$mapValuesTo = false;
                $this$associateByTo$iv$iv$iv$iv = $this$mapValuesTo$iv$iv$iv.entrySet();
                destination$iv$iv$iv$iv = destination$iv$iv$iv;
                $i$f$associateByTo = false;
                for (Object element$iv$iv$iv$iv : $this$associateByTo$iv$iv$iv$iv) {
                    it$iv$iv$iv = (Map.Entry)element$iv$iv$iv$iv;
                    map4 = destination$iv$iv$iv$iv;
                    bl2 = false;
                    it$iv = (Map.Entry)element$iv$iv$iv$iv;
                    k = it$iv$iv$iv.getKey();
                    map3 = map4;
                    bl = false;
                    string = JsonElementKt.getJsonPrimitive((JsonElement)((JsonElement)it$iv.getValue())).getContent();
                    map3.put(k, string);
                }
                map = destination$iv$iv$iv$iv;
            } else {
                map = MapsKt.emptyMap();
            }
            Map map7 = map;
            Map map8 = map6;
            remappingData = new RemappingData(map8, map7);
        }
        catch (Exception e) {
            Observable.INSTANCE.getLOGGER().warn("Unable to get profiling data! " + e.getMessage());
            e.printStackTrace();
            Observable.INSTANCE.getLOGGER().warn("Remapping data will be unavailable for the remainder of the session");
            remappingData = new RemappingData(MapsKt.emptyMap(), MapsKt.emptyMap());
        }
        return remappingData;
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0015\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J5\u0010\r\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u00d6\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0004H\u00d6\u0081\u0004R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t\u00a8\u0006\u0014"}, d2={"Lobservable/server/Remapper$RemappingData;", "", "classes", "", "", "methods", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", "getClasses", "()Ljava/util/Map;", "getMethods", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "observable"})
    public static final class RemappingData {
        @NotNull
        private final Map<String, String> classes;
        @NotNull
        private final Map<String, String> methods;

        public RemappingData(@NotNull Map<String, String> classes, @NotNull Map<String, String> methods) {
            Intrinsics.checkNotNullParameter(classes, (String)"classes");
            Intrinsics.checkNotNullParameter(methods, (String)"methods");
            this.classes = classes;
            this.methods = methods;
        }

        @NotNull
        public final Map<String, String> getClasses() {
            return this.classes;
        }

        @NotNull
        public final Map<String, String> getMethods() {
            return this.methods;
        }

        @NotNull
        public final Map<String, String> component1() {
            return this.classes;
        }

        @NotNull
        public final Map<String, String> component2() {
            return this.methods;
        }

        @NotNull
        public final RemappingData copy(@NotNull Map<String, String> classes, @NotNull Map<String, String> methods) {
            Intrinsics.checkNotNullParameter(classes, (String)"classes");
            Intrinsics.checkNotNullParameter(methods, (String)"methods");
            return new RemappingData(classes, methods);
        }

        public static /* synthetic */ RemappingData copy$default(RemappingData remappingData, Map map, Map map2, int n, Object object) {
            if ((n & 1) != 0) {
                map = remappingData.classes;
            }
            if ((n & 2) != 0) {
                map2 = remappingData.methods;
            }
            return remappingData.copy(map, map2);
        }

        @NotNull
        public String toString() {
            return "RemappingData(classes=" + this.classes + ", methods=" + this.methods + ")";
        }

        public int hashCode() {
            int result = ((Object)this.classes).hashCode();
            result = result * 31 + ((Object)this.methods).hashCode();
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RemappingData)) {
                return false;
            }
            RemappingData remappingData = (RemappingData)other;
            if (!Intrinsics.areEqual(this.classes, remappingData.classes)) {
                return false;
            }
            return Intrinsics.areEqual(this.methods, remappingData.methods);
        }
    }
}

