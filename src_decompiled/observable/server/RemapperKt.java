/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Pair
 *  kotlin.TuplesKt
 *  kotlin.collections.MapsKt
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlinx.serialization.json.JsonElement
 *  kotlinx.serialization.json.JsonElementKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import observable.server.ModLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=2, xi=48, d1={"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\"$\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u0005*\u0004\u0018\u00010\n8\u00c6\u0002\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\r"}, d2={"BASE_URL", "", "getBASE_URL", "()Ljava/lang/String;", "MAPPING_URLS", "", "Lobservable/server/ModLoader;", "getMAPPING_URLS", "()Ljava/util/Map;", "stringMap", "Lkotlinx/serialization/json/JsonElement;", "getStringMap", "(Lkotlinx/serialization/json/JsonElement;)Ljava/util/Map;", "observable"})
@SourceDebugExtension(value={"SMAP\nRemapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Remapper.kt\nobservable/server/RemapperKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,56:1\n466#2:57\n415#2:58\n1266#3,4:59\n*S KotlinDebug\n*F\n+ 1 Remapper.kt\nobservable/server/RemapperKt\n*L\n24#1:57\n24#1:58\n24#1:59,4\n*E\n"})
public final class RemapperKt {
    @NotNull
    private static final String BASE_URL = "https://raw.githubusercontent.com/lucko/spark-mappings/master/dist/1_21";
    @NotNull
    private static final Map<ModLoader, String> MAPPING_URLS;

    @NotNull
    public static final String getBASE_URL() {
        return BASE_URL;
    }

    @NotNull
    public static final Map<ModLoader, String> getMAPPING_URLS() {
        return MAPPING_URLS;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final Map<String, String> getStringMap(@Nullable JsonElement $this$stringMap) {
        Map map;
        boolean $i$f$getStringMap = false;
        JsonElement jsonElement = $this$stringMap;
        if (jsonElement != null && (jsonElement = JsonElementKt.getJsonObject((JsonElement)jsonElement)) != null) {
            void $this$associateByTo$iv$iv$iv;
            void $this$mapValuesTo$iv$iv;
            Map $this$mapValues$iv = (Map)jsonElement;
            boolean $i$f$mapValues = false;
            Map map2 = $this$mapValues$iv;
            Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity((int)$this$mapValues$iv.size()));
            boolean $i$f$mapValuesTo = false;
            Iterable iterable = $this$mapValuesTo$iv$iv.entrySet();
            Map destination$iv$iv$iv = destination$iv$iv;
            boolean $i$f$associateByTo = false;
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                void it;
                void it$iv$iv;
                Map.Entry entry = (Map.Entry)element$iv$iv$iv;
                Map map3 = destination$iv$iv$iv;
                boolean bl = false;
                Map.Entry entry2 = (Map.Entry)element$iv$iv$iv;
                Object k = it$iv$iv.getKey();
                Map map4 = map3;
                boolean bl2 = false;
                String string = JsonElementKt.getJsonPrimitive((JsonElement)((JsonElement)it.getValue())).getContent();
                map4.put(k, string);
            }
            map = destination$iv$iv$iv;
        } else {
            map = MapsKt.emptyMap();
        }
        return map;
    }

    static {
        Pair[] pairArray = new Pair[]{TuplesKt.to((Object)((Object)ModLoader.FABRIC), (Object)(BASE_URL + "/yarn.json")), TuplesKt.to((Object)((Object)ModLoader.FORGE), (Object)(BASE_URL + "/mcp.json"))};
        MAPPING_URLS = MapsKt.mapOf((Pair[])pairArray);
    }
}

