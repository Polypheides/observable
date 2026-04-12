/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.io.path.PathsKt
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlinx.serialization.DeserializationStrategy
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.json.Json
 *  net.fabricmc.loader.api.FabricLoader
 *  org.jetbrains.annotations.NotNull
 */
package observable.server;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.io.path.PathsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import net.fabricmc.loader.api.FabricLoader;
import observable.server.ServerSettingsData;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=2, xi=48, d1={"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u001a\u0006\u0010\n\u001a\u00020\u0005\u001a\u0006\u0010\u000b\u001a\u00020\f\"\u0011\u0010\u0000\u001a\u00020\u0001\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\r"}, d2={"configFile", "Ljava/nio/file/Path;", "getConfigFile", "()Ljava/nio/file/Path;", "ServerSettings", "Lobservable/server/ServerSettingsData;", "getServerSettings", "()Lobservable/server/ServerSettingsData;", "setServerSettings", "(Lobservable/server/ServerSettingsData;)V", "loadSettings", "resetSettings", "", "observable"})
@SourceDebugExtension(value={"SMAP\nServerSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServerSettings.kt\nobservable/server/ServerSettingsKt\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,39:1\n205#2:40\n222#2:41\n*S KotlinDebug\n*F\n+ 1 ServerSettings.kt\nobservable/server/ServerSettingsKt\n*L\n29#1:40\n32#1:41\n*E\n"})
public final class ServerSettingsKt {
    @NotNull
    private static final Path configFile;
    @NotNull
    private static ServerSettingsData ServerSettings;

    @NotNull
    public static final Path getConfigFile() {
        return configFile;
    }

    @NotNull
    public static final ServerSettingsData getServerSettings() {
        return ServerSettings;
    }

    public static final void setServerSettings(@NotNull ServerSettingsData serverSettingsData) {
        Intrinsics.checkNotNullParameter((Object)serverSettingsData, (String)"<set-?>");
        ServerSettings = serverSettingsData;
    }

    /*
     * WARNING - void declaration
     */
    @NotNull
    public static final ServerSettingsData loadSettings() {
        void this_$iv;
        LinkOption[] linkOptionArray = new LinkOption[]{};
        if (!Files.exists(configFile, Arrays.copyOf(linkOptionArray, linkOptionArray.length))) {
            void this_$iv2;
            ServerSettingsData settings = new ServerSettingsData(0, 0, 0, false, null, false, null, 127, null);
            Json json = (Json)Json.Default;
            ServerSettingsData value$iv = settings;
            boolean $i$f$encodeToString = false;
            this_$iv2.getSerializersModule();
            PathsKt.writeText$default((Path)configFile, (CharSequence)this_$iv2.encodeToString((SerializationStrategy)ServerSettingsData.Companion.serializer(), (Object)value$iv), null, (OpenOption[])new OpenOption[0], (int)2, null);
            return settings;
        }
        Json settings = (Json)Json.Default;
        String string$iv = PathsKt.readText$default((Path)configFile, null, (int)1, null);
        boolean $i$f$decodeFromString = false;
        this_$iv.getSerializersModule();
        return (ServerSettingsData)this_$iv.decodeFromString((DeserializationStrategy)ServerSettingsData.Companion.serializer(), string$iv);
    }

    public static final void resetSettings() {
        Files.deleteIfExists(configFile);
        ServerSettings = ServerSettingsKt.loadSettings();
    }

    static {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("observable.json");
        Intrinsics.checkNotNullExpressionValue((Object)path, (String)"resolve(...)");
        configFile = path;
        ServerSettings = ServerSettingsKt.loadSettings();
    }
}

