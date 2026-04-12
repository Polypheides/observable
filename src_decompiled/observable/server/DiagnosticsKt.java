/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlinx.serialization.json.JsonElement
 *  kotlinx.serialization.json.JsonElementBuildersKt
 *  kotlinx.serialization.json.JsonObject
 *  kotlinx.serialization.json.JsonObjectBuilder
 *  net.fabricmc.loader.api.FabricLoader
 *  net.fabricmc.loader.api.ModContainer
 *  net.minecraft.SystemReport
 *  org.jetbrains.annotations.NotNull
 */
package observable.server;

import java.util.Collection;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SystemReport;
import observable.server.Profiler;
import observable.server.ServerSettingsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=2, xi=48, d1={"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u00a8\u0006\u0003"}, d2={"getDiagnostics", "Lkotlinx/serialization/json/JsonObject;", "Lobservable/server/Profiler;", "observable"})
@SourceDebugExtension(value={"SMAP\nDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Diagnostics.kt\nobservable/server/DiagnosticsKt\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,39:1\n29#2,2:40\n29#2,3:42\n31#2:45\n*S KotlinDebug\n*F\n+ 1 Diagnostics.kt\nobservable/server/DiagnosticsKt\n*L\n18#1:40,2\n27#1:42,3\n18#1:45\n*E\n"})
public final class DiagnosticsKt {
    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final JsonObject getDiagnostics(@NotNull Profiler $this$getDiagnostics) {
        void $this$getDiagnostics_u24lambda_u240_u240;
        JsonObjectBuilder builder$iv;
        JsonObjectBuilder builder$iv2;
        Intrinsics.checkNotNullParameter((Object)$this$getDiagnostics, (String)"<this>");
        long duration = System.currentTimeMillis() - $this$getDiagnostics.getStartTime();
        SystemReport systemReport = new SystemReport();
        if (!ServerSettingsKt.getServerSettings().getIncludeJvmArgs()) {
            systemReport.setDetail("JVM Flags", "<REDACTED>");
        }
        FabricLoader fabricLoader = FabricLoader.getInstance();
        boolean $i$f$buildJsonObject = false;
        JsonObjectBuilder $this$getDiagnostics_u24lambda_u240 = builder$iv2 = new JsonObjectBuilder();
        boolean bl = false;
        Object object = $this$getDiagnostics.getPlayer();
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240, (String)"user", object != null && (object = object.getGameProfile()) != null && (object = object.id()) != null ? ((UUID)object).toString() : null);
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240, (String)"start", (Number)$this$getDiagnostics.getStartTime());
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240, (String)"duration", (Number)duration);
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240, (String)"minecraftVersion", (String)((ModContainer)fabricLoader.getModContainer("minecraft").get()).getMetadata().getVersion().getFriendlyString());
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240, (String)"modLoader", (String)"FABRIC");
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240, (String)"observableVersion", (String)((ModContainer)fabricLoader.getModContainer("observable").get()).getMetadata().getVersion().getFriendlyString());
        boolean $i$f$buildJsonObject2 = false;
        JsonObjectBuilder jsonObjectBuilder = builder$iv = new JsonObjectBuilder();
        String string = "additionalDiagnostics";
        JsonObjectBuilder jsonObjectBuilder2 = $this$getDiagnostics_u24lambda_u240;
        boolean bl2 = false;
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240_u240, (String)"System Report", (String)systemReport.toLineSeparatedString());
        Collection collection = fabricLoader.getAllMods();
        Intrinsics.checkNotNullExpressionValue((Object)collection, (String)"getAllMods(...)");
        JsonElementBuildersKt.put((JsonObjectBuilder)$this$getDiagnostics_u24lambda_u240_u240, (String)"Mods", (String)CollectionsKt.joinToString$default((Iterable)collection, (CharSequence)"\n", null, null, (int)0, null, DiagnosticsKt::getDiagnostics$lambda$0$0$0, (int)30, null));
        Unit unit = Unit.INSTANCE;
        jsonObjectBuilder2.put(string, (JsonElement)builder$iv.build());
        return builder$iv2.build();
    }

    private static final CharSequence getDiagnostics$lambda$0$0$0(ModContainer mod) {
        return "'" + mod.getMetadata().getName() + "' (version: " + mod.getMetadata().getVersion().getFriendlyString() + ")";
    }
}

