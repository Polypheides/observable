/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.io.CloseableKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlin.text.Charsets
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.json.Json
 *  kotlinx.serialization.json.JsonBuilder
 *  kotlinx.serialization.json.JsonKt
 *  net.minecraft.ChatFormatting
 *  net.minecraft.network.chat.Component
 *  net.minecraft.network.chat.MutableComponent
 *  net.minecraft.network.chat.Style
 *  org.jetbrains.annotations.NotNull
 */
package observable.client;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import observable.server.ProfilingData;
import observable.util.ClickEventUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"Lobservable/client/ProfileExporter;", "", "<init>", "()V", "dir", "Ljava/io/File;", "sdf", "Ljava/text/SimpleDateFormat;", "json", "Lkotlinx/serialization/json/Json;", "export", "Lnet/minecraft/network/chat/Component;", "data", "Lobservable/server/ProfilingData;", "observable"})
@SourceDebugExtension(value={"SMAP\nProfileExporter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProfileExporter.kt\nobservable/client/ProfileExporter\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,38:1\n205#2:39\n*S KotlinDebug\n*F\n+ 1 ProfileExporter.kt\nobservable/client/ProfileExporter\n*L\n28#1:39\n*E\n"})
public final class ProfileExporter {
    @NotNull
    public static final ProfileExporter INSTANCE = new ProfileExporter();
    @NotNull
    private static final File dir = new File("observable_profiles");
    @NotNull
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd--HH.mm.ss");
    @NotNull
    private static final Json json;

    private ProfileExporter() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - void declaration
     */
    @NotNull
    public final Component export(@NotNull ProfilingData data) {
        Intrinsics.checkNotNullParameter((Object)data, (String)"data");
        File file = new File(dir, sdf.format(new Date()) + ".json");
        Object object = file;
        Object object2 = Charsets.UTF_8;
        File file2 = object;
        int n = 8192;
        Object object3 = file2;
        object3 = new OutputStreamWriter((OutputStream)new FileOutputStream((File)object3), (Charset)object2);
        object = new PrintWriter(object3 instanceof BufferedWriter ? (BufferedWriter)object3 : new BufferedWriter((Writer)object3, n));
        object2 = null;
        try {
            void this_$iv;
            PrintWriter it = (PrintWriter)object;
            boolean bl = false;
            object3 = json;
            ProfilingData value$iv = data;
            boolean $i$f$encodeToString = false;
            this_$iv.getSerializersModule();
            it.println(this_$iv.encodeToString((SerializationStrategy)ProfilingData.Companion.serializer(), (Object)value$iv));
            file2 = Unit.INSTANCE;
        }
        catch (Throwable throwable) {
            object2 = throwable;
            throw throwable;
        }
        finally {
            CloseableKt.closeFinally((Closeable)object, (Throwable)object2);
        }
        MutableComponent mutableComponent = Component.literal((String)file.getName()).withStyle(ChatFormatting.UNDERLINE).withStyle(ProfileExporter::export$lambda$1);
        Intrinsics.checkNotNullExpressionValue((Object)mutableComponent, (String)"withStyle(...)");
        MutableComponent link = mutableComponent;
        return (Component)link;
    }

    private static final Unit json$lambda$0(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter((Object)$this$Json, (String)"$this$Json");
        $this$Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }

    private static final Style export$lambda$1(Style it) {
        Intrinsics.checkNotNullParameter((Object)it, (String)"it");
        return it.withClickEvent(ClickEventUtils.openFile(dir.getAbsolutePath()));
    }

    static {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        json = JsonKt.Json$default(null, ProfileExporter::json$lambda$0, (int)1, null);
    }
}

