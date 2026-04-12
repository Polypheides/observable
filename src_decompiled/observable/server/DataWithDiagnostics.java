/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlinx.serialization.KSerializer
 *  kotlinx.serialization.Serializable
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.CompositeEncoder
 *  kotlinx.serialization.internal.PluginExceptionsKt
 *  kotlinx.serialization.internal.SerializationConstructorMarker
 *  kotlinx.serialization.json.JsonObject
 *  kotlinx.serialization.json.JsonObjectSerializer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;
import observable.server.DataWithDiagnostics$;
import observable.server.ProfilingData;
import observable.server.ProfilingData$;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Serializable
@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 #2\u00020\u0001:\u0002\"#B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u0017\u001a\u00020\tH\u00d6\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019H\u00d6\u0081\u0004J%\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0001\u00a2\u0006\u0002\b!R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006$"}, d2={"Lobservable/server/DataWithDiagnostics;", "", "data", "Lobservable/server/ProfilingData;", "diagnostics", "Lkotlinx/serialization/json/JsonObject;", "<init>", "(Lobservable/server/ProfilingData;Lkotlinx/serialization/json/JsonObject;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILobservable/server/ProfilingData;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData", "()Lobservable/server/ProfilingData;", "getDiagnostics", "()Lkotlinx/serialization/json/JsonObject;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
public final class DataWithDiagnostics {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final ProfilingData data;
    @NotNull
    private final JsonObject diagnostics;

    public DataWithDiagnostics(@NotNull ProfilingData data, @NotNull JsonObject diagnostics) {
        Intrinsics.checkNotNullParameter((Object)data, (String)"data");
        Intrinsics.checkNotNullParameter((Object)diagnostics, (String)"diagnostics");
        this.data = data;
        this.diagnostics = diagnostics;
    }

    @NotNull
    public final ProfilingData getData() {
        return this.data;
    }

    @NotNull
    public final JsonObject getDiagnostics() {
        return this.diagnostics;
    }

    @NotNull
    public final ProfilingData component1() {
        return this.data;
    }

    @NotNull
    public final JsonObject component2() {
        return this.diagnostics;
    }

    @NotNull
    public final DataWithDiagnostics copy(@NotNull ProfilingData data, @NotNull JsonObject diagnostics) {
        Intrinsics.checkNotNullParameter((Object)data, (String)"data");
        Intrinsics.checkNotNullParameter((Object)diagnostics, (String)"diagnostics");
        return new DataWithDiagnostics(data, diagnostics);
    }

    public static /* synthetic */ DataWithDiagnostics copy$default(DataWithDiagnostics dataWithDiagnostics, ProfilingData profilingData, JsonObject jsonObject, int n, Object object) {
        if ((n & 1) != 0) {
            profilingData = dataWithDiagnostics.data;
        }
        if ((n & 2) != 0) {
            jsonObject = dataWithDiagnostics.diagnostics;
        }
        return dataWithDiagnostics.copy(profilingData, jsonObject);
    }

    @NotNull
    public String toString() {
        return "DataWithDiagnostics(data=" + this.data + ", diagnostics=" + this.diagnostics + ")";
    }

    public int hashCode() {
        int result = this.data.hashCode();
        result = result * 31 + this.diagnostics.hashCode();
        return result;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataWithDiagnostics)) {
            return false;
        }
        DataWithDiagnostics dataWithDiagnostics = (DataWithDiagnostics)other;
        if (!Intrinsics.areEqual((Object)this.data, (Object)dataWithDiagnostics.data)) {
            return false;
        }
        return Intrinsics.areEqual((Object)this.diagnostics, (Object)dataWithDiagnostics.diagnostics);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$observable(DataWithDiagnostics self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeSerializableElement(serialDesc, 0, (SerializationStrategy)ProfilingData$.serializer.INSTANCE, (Object)self.data);
        output.encodeSerializableElement(serialDesc, 1, (SerializationStrategy)JsonObjectSerializer.INSTANCE, (Object)self.diagnostics);
    }

    public /* synthetic */ DataWithDiagnostics(int seen0, ProfilingData data, JsonObject diagnostics, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (3 & seen0)) {
            PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)3, (SerialDescriptor)$serializer.INSTANCE.getDescriptor());
        }
        this.data = data;
        this.diagnostics = diagnostics;
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/server/DataWithDiagnostics$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/server/DataWithDiagnostics;", "observable"})
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<DataWithDiagnostics> serializer() {
            return (KSerializer)$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

