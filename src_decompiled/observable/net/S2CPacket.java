/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.LazyThreadSafetyMode
 *  kotlin.Metadata
 *  kotlin.enums.EnumEntries
 *  kotlin.enums.EnumEntriesKt
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlinx.serialization.KSerializer
 *  kotlinx.serialization.Serializable
 *  kotlinx.serialization.SerializationStrategy
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.CompositeEncoder
 *  kotlinx.serialization.internal.EnumsKt
 *  kotlinx.serialization.internal.ObjectSerializer
 *  kotlinx.serialization.internal.PluginExceptionsKt
 *  kotlinx.serialization.internal.SerializationConstructorMarker
 *  kotlinx.serialization.internal.StringSerializer
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.net;

import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.EnumsKt;
import kotlinx.serialization.internal.ObjectSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import observable.net.S2CPacket$ConsiderProfiling$;
import observable.net.S2CPacket$ProfilingResult$;
import observable.net.S2CPacket$ProfilingStarted$;
import observable.server.ProfilingData;
import observable.server.ProfilingData$;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0006\u0004\u0005\u0006\u0007\b\tB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\n"}, d2={"Lobservable/net/S2CPacket;", "", "<init>", "()V", "ProfilingStarted", "ProfilingCompleted", "ProfilerInactive", "ProfilingResult", "Availability", "ConsiderProfiling", "observable"})
public final class S2CPacket {

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0087\u0081\u0002\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/S2CPacket$Availability;", "", "<init>", "(Ljava/lang/String;I)V", "Available", "NoPermissions", "Companion", "observable"})
    public static final class Availability
    extends Enum<Availability> {
        @NotNull
        public static final Companion Companion;
        @NotNull
        private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;
        public static final /* enum */ Availability Available;
        public static final /* enum */ Availability NoPermissions;
        private static final /* synthetic */ Availability[] $VALUES;
        private static final /* synthetic */ EnumEntries $ENTRIES;

        public static Availability[] values() {
            return (Availability[])$VALUES.clone();
        }

        public static Availability valueOf(String value) {
            return Enum.valueOf(Availability.class, value);
        }

        @NotNull
        public static EnumEntries<Availability> getEntries() {
            return $ENTRIES;
        }

        static {
            Available = new Availability();
            NoPermissions = new Availability();
            $VALUES = availabilityArray = new Availability[]{Availability.Available, Availability.NoPermissions};
            $ENTRIES = EnumEntriesKt.enumEntries((Enum[])$VALUES);
            Companion = new Companion(null);
            $cachedSerializer$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> EnumsKt.createSimpleEnumSerializer((String)"observable.net.S2CPacket.Availability", (Enum[])Availability.values()));
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/S2CPacket$Availability$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/net/S2CPacket$Availability;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<Availability> serializer() {
                return this.get$cachedSerializer();
            }

            private final /* synthetic */ KSerializer get$cachedSerializer() {
                return (KSerializer)$cachedSerializer$delegate.getValue();
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005B#\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\u0004\u0010\nJ%\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0001\u00a2\u0006\u0002\b\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2={"Lobservable/net/S2CPacket$ConsiderProfiling;", "", "tps", "", "<init>", "(D)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IDLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTps", "()D", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    public static final class ConsiderProfiling {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final double tps;

        public ConsiderProfiling(double tps) {
            this.tps = tps;
        }

        public final double getTps() {
            return this.tps;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(ConsiderProfiling self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeDoubleElement(serialDesc, 0, self.tps);
        }

        public /* synthetic */ ConsiderProfiling(int seen0, double tps, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (1 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)1, (SerialDescriptor)ConsiderProfiling$$serializer.INSTANCE.getDescriptor());
            }
            this.tps = tps;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/S2CPacket$ConsiderProfiling$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/net/S2CPacket$ConsiderProfiling;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<ConsiderProfiling> serializer() {
                return (KSerializer)ConsiderProfiling$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u00a8\u0006\u0006"}, d2={"Lobservable/net/S2CPacket$ProfilerInactive;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "observable"})
    public static final class ProfilerInactive {
        @NotNull
        public static final ProfilerInactive INSTANCE = new ProfilerInactive();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate;

        private ProfilerInactive() {
        }

        @NotNull
        public final KSerializer<ProfilerInactive> serializer() {
            return this.get$cachedSerializer();
        }

        private final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer)$cachedSerializer$delegate.getValue();
        }

        static {
            $cachedSerializer$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new ObjectSerializer("observable.net.S2CPacket.ProfilerInactive", (Object)INSTANCE, new Annotation[0]));
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u00a8\u0006\u0006"}, d2={"Lobservable/net/S2CPacket$ProfilingCompleted;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "observable"})
    public static final class ProfilingCompleted {
        @NotNull
        public static final ProfilingCompleted INSTANCE = new ProfilingCompleted();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate;

        private ProfilingCompleted() {
        }

        @NotNull
        public final KSerializer<ProfilingCompleted> serializer() {
            return this.get$cachedSerializer();
        }

        private final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer)$cachedSerializer$delegate.getValue();
        }

        static {
            $cachedSerializer$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new ObjectSerializer("observable.net.S2CPacket.ProfilingCompleted", (Object)INSTANCE, new Annotation[0]));
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007B/\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0006\u0010\fJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u001f\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u0017\u001a\u00020\tH\u00d6\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005H\u00d6\u0081\u0004J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001\u00a2\u0006\u0002\b R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006#"}, d2={"Lobservable/net/S2CPacket$ProfilingResult;", "", "data", "Lobservable/server/ProfilingData;", "link", "", "<init>", "(Lobservable/server/ProfilingData;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILobservable/server/ProfilingData;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getData", "()Lobservable/server/ProfilingData;", "getLink", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    public static final class ProfilingResult {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final ProfilingData data;
        @Nullable
        private final String link;

        public ProfilingResult(@NotNull ProfilingData data, @Nullable String link) {
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            this.data = data;
            this.link = link;
        }

        @NotNull
        public final ProfilingData getData() {
            return this.data;
        }

        @Nullable
        public final String getLink() {
            return this.link;
        }

        @NotNull
        public final ProfilingData component1() {
            return this.data;
        }

        @Nullable
        public final String component2() {
            return this.link;
        }

        @NotNull
        public final ProfilingResult copy(@NotNull ProfilingData data, @Nullable String link) {
            Intrinsics.checkNotNullParameter((Object)data, (String)"data");
            return new ProfilingResult(data, link);
        }

        public static /* synthetic */ ProfilingResult copy$default(ProfilingResult profilingResult, ProfilingData profilingData, String string, int n, Object object) {
            if ((n & 1) != 0) {
                profilingData = profilingResult.data;
            }
            if ((n & 2) != 0) {
                string = profilingResult.link;
            }
            return profilingResult.copy(profilingData, string);
        }

        @NotNull
        public String toString() {
            return "ProfilingResult(data=" + this.data + ", link=" + this.link + ")";
        }

        public int hashCode() {
            int result = this.data.hashCode();
            result = result * 31 + (this.link == null ? 0 : this.link.hashCode());
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProfilingResult)) {
                return false;
            }
            ProfilingResult profilingResult = (ProfilingResult)other;
            if (!Intrinsics.areEqual((Object)this.data, (Object)profilingResult.data)) {
                return false;
            }
            return Intrinsics.areEqual((Object)this.link, (Object)profilingResult.link);
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(ProfilingResult self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeSerializableElement(serialDesc, 0, (SerializationStrategy)ProfilingData$.serializer.INSTANCE, (Object)self.data);
            output.encodeNullableSerializableElement(serialDesc, 1, (SerializationStrategy)StringSerializer.INSTANCE, (Object)self.link);
        }

        public /* synthetic */ ProfilingResult(int seen0, ProfilingData data, String link, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (3 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)3, (SerialDescriptor)ProfilingResult$$serializer.INSTANCE.getDescriptor());
            }
            this.data = data;
            this.link = link;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/S2CPacket$ProfilingResult$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/net/S2CPacket$ProfilingResult;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<ProfilingResult> serializer() {
                return (KSerializer)ProfilingResult$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005B#\b\u0010\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0004\b\u0004\u0010\nJ\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0007H\u00d6\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014H\u00d6\u0081\u0004J%\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0001\u00a2\u0006\u0002\b\u001cR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u001f"}, d2={"Lobservable/net/S2CPacket$ProfilingStarted;", "", "endMillis", "", "<init>", "(J)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getEndMillis", "()J", "component1", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    public static final class ProfilingStarted {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final long endMillis;

        public ProfilingStarted(long endMillis) {
            this.endMillis = endMillis;
        }

        public final long getEndMillis() {
            return this.endMillis;
        }

        public final long component1() {
            return this.endMillis;
        }

        @NotNull
        public final ProfilingStarted copy(long endMillis) {
            return new ProfilingStarted(endMillis);
        }

        public static /* synthetic */ ProfilingStarted copy$default(ProfilingStarted profilingStarted, long l, int n, Object object) {
            if ((n & 1) != 0) {
                l = profilingStarted.endMillis;
            }
            return profilingStarted.copy(l);
        }

        @NotNull
        public String toString() {
            return "ProfilingStarted(endMillis=" + this.endMillis + ")";
        }

        public int hashCode() {
            return Long.hashCode(this.endMillis);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProfilingStarted)) {
                return false;
            }
            ProfilingStarted profilingStarted = (ProfilingStarted)other;
            return this.endMillis == profilingStarted.endMillis;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(ProfilingStarted self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeLongElement(serialDesc, 0, self.endMillis);
        }

        public /* synthetic */ ProfilingStarted(int seen0, long endMillis, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (1 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)1, (SerialDescriptor)ProfilingStarted$$serializer.INSTANCE.getDescriptor());
            }
            this.endMillis = endMillis;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/S2CPacket$ProfilingStarted$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/net/S2CPacket$ProfilingStarted;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<ProfilingStarted> serializer() {
                return (KSerializer)ProfilingStarted$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

