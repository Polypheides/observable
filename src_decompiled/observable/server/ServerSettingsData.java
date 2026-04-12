/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.LazyThreadSafetyMode
 *  kotlin.Metadata
 *  kotlin.io.path.PathsKt
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
 *  kotlinx.serialization.internal.LinkedHashSetSerializer
 *  kotlinx.serialization.internal.PluginExceptionsKt
 *  kotlinx.serialization.internal.SerializationConstructorMarker
 *  kotlinx.serialization.internal.StringSerializer
 *  kotlinx.serialization.json.Json
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.io.path.PathsKt;
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
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import observable.server.ServerSettingsData$;
import observable.server.ServerSettingsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 ?2\u00020\u0001:\u0002>?BS\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u00a2\u0006\u0004\b\r\u0010\u000eB]\b\u0010\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\u0004\b\r\u0010\u0012J\u0006\u0010)\u001a\u00020*J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u00100\u001a\u00020\u0007H\u00c6\u0003J\t\u00101\u001a\u00020\nH\u00c6\u0003JU\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\nH\u00c6\u0001J\u0014\u00103\u001a\u00020\u00072\b\u00104\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u00105\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u00106\u001a\u00020\nH\u00d6\u0081\u0004J%\u00107\u001a\u00020*2\u0006\u00108\u001a\u00020\u00002\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0001\u00a2\u0006\u0002\b=R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u000b\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u001a\u0010\f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(\u00a8\u0006@"}, d2={"Lobservable/server/ServerSettingsData;", "", "traceInterval", "", "deviation", "notifyInterval", "allPlayersAllowed", "", "allowedPlayers", "", "", "includeJvmArgs", "uploadURL", "<init>", "(IIIZLjava/util/Set;ZLjava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIIZLjava/util/Set;ZLjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTraceInterval", "()I", "setTraceInterval", "(I)V", "getDeviation", "setDeviation", "getNotifyInterval", "setNotifyInterval", "getAllPlayersAllowed", "()Z", "setAllPlayersAllowed", "(Z)V", "getAllowedPlayers", "()Ljava/util/Set;", "setAllowedPlayers", "(Ljava/util/Set;)V", "getIncludeJvmArgs", "setIncludeJvmArgs", "getUploadURL", "()Ljava/lang/String;", "setUploadURL", "(Ljava/lang/String;)V", "sync", "", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
@SourceDebugExtension(value={"SMAP\nServerSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ServerSettings.kt\nobservable/server/ServerSettingsData\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,39:1\n205#2:40\n*S KotlinDebug\n*F\n+ 1 ServerSettings.kt\nobservable/server/ServerSettingsData\n*L\n23#1:40\n*E\n"})
public final class ServerSettingsData {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private int traceInterval;
    private int deviation;
    private int notifyInterval;
    private boolean allPlayersAllowed;
    @NotNull
    private Set<String> allowedPlayers;
    private boolean includeJvmArgs;
    @NotNull
    private String uploadURL;
    @JvmField
    @NotNull
    private static final Lazy<KSerializer<Object>>[] $childSerializers;

    public ServerSettingsData(int traceInterval, int deviation, int notifyInterval, boolean allPlayersAllowed, @NotNull Set<String> allowedPlayers, boolean includeJvmArgs, @NotNull String uploadURL) {
        Intrinsics.checkNotNullParameter(allowedPlayers, (String)"allowedPlayers");
        Intrinsics.checkNotNullParameter((Object)uploadURL, (String)"uploadURL");
        this.traceInterval = traceInterval;
        this.deviation = deviation;
        this.notifyInterval = notifyInterval;
        this.allPlayersAllowed = allPlayersAllowed;
        this.allowedPlayers = allowedPlayers;
        this.includeJvmArgs = includeJvmArgs;
        this.uploadURL = uploadURL;
    }

    public /* synthetic */ ServerSettingsData(int n, int n2, int n3, boolean bl, Set set, boolean bl2, String string, int n4, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n4 & 1) != 0) {
            n = 3;
        }
        if ((n4 & 2) != 0) {
            n2 = 1;
        }
        if ((n4 & 4) != 0) {
            n3 = 0x6DDD00;
        }
        if ((n4 & 8) != 0) {
            bl = false;
        }
        if ((n4 & 0x10) != 0) {
            set = new LinkedHashSet();
        }
        if ((n4 & 0x20) != 0) {
            bl2 = true;
        }
        if ((n4 & 0x40) != 0) {
            string = "https://observable.tas.sh/v1/add";
        }
        this(n, n2, n3, bl, set, bl2, string);
    }

    public final int getTraceInterval() {
        return this.traceInterval;
    }

    public final void setTraceInterval(int n) {
        this.traceInterval = n;
    }

    public final int getDeviation() {
        return this.deviation;
    }

    public final void setDeviation(int n) {
        this.deviation = n;
    }

    public final int getNotifyInterval() {
        return this.notifyInterval;
    }

    public final void setNotifyInterval(int n) {
        this.notifyInterval = n;
    }

    public final boolean getAllPlayersAllowed() {
        return this.allPlayersAllowed;
    }

    public final void setAllPlayersAllowed(boolean bl) {
        this.allPlayersAllowed = bl;
    }

    @NotNull
    public final Set<String> getAllowedPlayers() {
        return this.allowedPlayers;
    }

    public final void setAllowedPlayers(@NotNull Set<String> set) {
        Intrinsics.checkNotNullParameter(set, (String)"<set-?>");
        this.allowedPlayers = set;
    }

    public final boolean getIncludeJvmArgs() {
        return this.includeJvmArgs;
    }

    public final void setIncludeJvmArgs(boolean bl) {
        this.includeJvmArgs = bl;
    }

    @NotNull
    public final String getUploadURL() {
        return this.uploadURL;
    }

    public final void setUploadURL(@NotNull String string) {
        Intrinsics.checkNotNullParameter((Object)string, (String)"<set-?>");
        this.uploadURL = string;
    }

    /*
     * WARNING - void declaration
     */
    public final void sync() {
        void this_$iv;
        Path path = ServerSettingsKt.getConfigFile();
        Json json = (Json)Json.Default;
        ServerSettingsData value$iv = this;
        boolean $i$f$encodeToString = false;
        this_$iv.getSerializersModule();
        PathsKt.writeText$default((Path)path, (CharSequence)this_$iv.encodeToString((SerializationStrategy)Companion.serializer(), (Object)value$iv), null, (OpenOption[])new OpenOption[0], (int)2, null);
    }

    public final int component1() {
        return this.traceInterval;
    }

    public final int component2() {
        return this.deviation;
    }

    public final int component3() {
        return this.notifyInterval;
    }

    public final boolean component4() {
        return this.allPlayersAllowed;
    }

    @NotNull
    public final Set<String> component5() {
        return this.allowedPlayers;
    }

    public final boolean component6() {
        return this.includeJvmArgs;
    }

    @NotNull
    public final String component7() {
        return this.uploadURL;
    }

    @NotNull
    public final ServerSettingsData copy(int traceInterval, int deviation, int notifyInterval, boolean allPlayersAllowed, @NotNull Set<String> allowedPlayers, boolean includeJvmArgs, @NotNull String uploadURL) {
        Intrinsics.checkNotNullParameter(allowedPlayers, (String)"allowedPlayers");
        Intrinsics.checkNotNullParameter((Object)uploadURL, (String)"uploadURL");
        return new ServerSettingsData(traceInterval, deviation, notifyInterval, allPlayersAllowed, allowedPlayers, includeJvmArgs, uploadURL);
    }

    public static /* synthetic */ ServerSettingsData copy$default(ServerSettingsData serverSettingsData, int n, int n2, int n3, boolean bl, Set set, boolean bl2, String string, int n4, Object object) {
        if ((n4 & 1) != 0) {
            n = serverSettingsData.traceInterval;
        }
        if ((n4 & 2) != 0) {
            n2 = serverSettingsData.deviation;
        }
        if ((n4 & 4) != 0) {
            n3 = serverSettingsData.notifyInterval;
        }
        if ((n4 & 8) != 0) {
            bl = serverSettingsData.allPlayersAllowed;
        }
        if ((n4 & 0x10) != 0) {
            set = serverSettingsData.allowedPlayers;
        }
        if ((n4 & 0x20) != 0) {
            bl2 = serverSettingsData.includeJvmArgs;
        }
        if ((n4 & 0x40) != 0) {
            string = serverSettingsData.uploadURL;
        }
        return serverSettingsData.copy(n, n2, n3, bl, set, bl2, string);
    }

    @NotNull
    public String toString() {
        return "ServerSettingsData(traceInterval=" + this.traceInterval + ", deviation=" + this.deviation + ", notifyInterval=" + this.notifyInterval + ", allPlayersAllowed=" + this.allPlayersAllowed + ", allowedPlayers=" + this.allowedPlayers + ", includeJvmArgs=" + this.includeJvmArgs + ", uploadURL=" + this.uploadURL + ")";
    }

    public int hashCode() {
        int result = Integer.hashCode(this.traceInterval);
        result = result * 31 + Integer.hashCode(this.deviation);
        result = result * 31 + Integer.hashCode(this.notifyInterval);
        result = result * 31 + Boolean.hashCode(this.allPlayersAllowed);
        result = result * 31 + ((Object)this.allowedPlayers).hashCode();
        result = result * 31 + Boolean.hashCode(this.includeJvmArgs);
        result = result * 31 + this.uploadURL.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServerSettingsData)) {
            return false;
        }
        ServerSettingsData serverSettingsData = (ServerSettingsData)other;
        if (this.traceInterval != serverSettingsData.traceInterval) {
            return false;
        }
        if (this.deviation != serverSettingsData.deviation) {
            return false;
        }
        if (this.notifyInterval != serverSettingsData.notifyInterval) {
            return false;
        }
        if (this.allPlayersAllowed != serverSettingsData.allPlayersAllowed) {
            return false;
        }
        if (!Intrinsics.areEqual(this.allowedPlayers, serverSettingsData.allowedPlayers)) {
            return false;
        }
        if (this.includeJvmArgs != serverSettingsData.includeJvmArgs) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.uploadURL, (Object)serverSettingsData.uploadURL);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$observable(ServerSettingsData self, CompositeEncoder output, SerialDescriptor serialDesc) {
        Lazy<KSerializer<Object>>[] lazyArray = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) ? true : self.traceInterval != 3) {
            output.encodeIntElement(serialDesc, 0, self.traceInterval);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) ? true : self.deviation != 1) {
            output.encodeIntElement(serialDesc, 1, self.deviation);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) ? true : self.notifyInterval != 0x6DDD00) {
            output.encodeIntElement(serialDesc, 2, self.notifyInterval);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) ? true : self.allPlayersAllowed) {
            output.encodeBooleanElement(serialDesc, 3, self.allPlayersAllowed);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) ? true : !Intrinsics.areEqual(self.allowedPlayers, (Object)new LinkedHashSet())) {
            output.encodeSerializableElement(serialDesc, 4, (SerializationStrategy)lazyArray[4].getValue(), self.allowedPlayers);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) ? true : !self.includeJvmArgs) {
            output.encodeBooleanElement(serialDesc, 5, self.includeJvmArgs);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) ? true : !Intrinsics.areEqual((Object)self.uploadURL, (Object)"https://observable.tas.sh/v1/add")) {
            output.encodeStringElement(serialDesc, 6, self.uploadURL);
        }
    }

    public /* synthetic */ ServerSettingsData(int seen0, int traceInterval, int deviation, int notifyInterval, boolean allPlayersAllowed, Set allowedPlayers, boolean includeJvmArgs, String uploadURL, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)0, (SerialDescriptor)$serializer.INSTANCE.getDescriptor());
        }
        this.traceInterval = (seen0 & 1) == 0 ? 3 : traceInterval;
        this.deviation = (seen0 & 2) == 0 ? 1 : deviation;
        this.notifyInterval = (seen0 & 4) == 0 ? 0x6DDD00 : notifyInterval;
        this.allPlayersAllowed = (seen0 & 8) == 0 ? false : allPlayersAllowed;
        this.allowedPlayers = (seen0 & 0x10) == 0 ? (Set)new LinkedHashSet() : allowedPlayers;
        this.includeJvmArgs = (seen0 & 0x20) == 0 ? true : includeJvmArgs;
        this.uploadURL = (seen0 & 0x40) == 0 ? "https://observable.tas.sh/v1/add" : uploadURL;
    }

    public ServerSettingsData() {
        this(0, 0, 0, false, null, false, null, 127, null);
    }

    public static final /* synthetic */ Lazy[] access$get$childSerializers$cp() {
        return $childSerializers;
    }

    static {
        Lazy[] lazyArray = new Lazy[]{null, null, null, null, LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new LinkedHashSetSerializer((KSerializer)StringSerializer.INSTANCE)), null, null};
        $childSerializers = lazyArray;
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/server/ServerSettingsData$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/server/ServerSettingsData;", "observable"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<ServerSettingsData> serializer() {
            return (KSerializer)$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

