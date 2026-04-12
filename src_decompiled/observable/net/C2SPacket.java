/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Lazy
 *  kotlin.LazyKt
 *  kotlin.LazyThreadSafetyMode
 *  kotlin.Metadata
 *  kotlin.jvm.JvmStatic
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlinx.serialization.KSerializer
 *  kotlinx.serialization.Serializable
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.CompositeEncoder
 *  kotlinx.serialization.internal.ObjectSerializer
 *  kotlinx.serialization.internal.PluginExceptionsKt
 *  kotlinx.serialization.internal.SerializationConstructorMarker
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.net;

import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ObjectSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import observable.net.C2SPacket$InitTPSProfile$;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0006"}, d2={"Lobservable/net/C2SPacket;", "", "<init>", "()V", "InitTPSProfile", "RequestAvailability", "observable"})
public final class C2SPacket {

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 !2\u00020\u0001:\u0002 !B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007B+\b\u0010\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0004\b\u0006\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0014\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017H\u00d6\u0081\u0004J%\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0001\u00a2\u0006\u0002\b\u001fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\""}, d2={"Lobservable/net/C2SPacket$InitTPSProfile;", "", "duration", "", "sample", "", "<init>", "(IZ)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getDuration", "()I", "getSample", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    public static final class InitTPSProfile {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int duration;
        private final boolean sample;

        public InitTPSProfile(int duration, boolean sample) {
            this.duration = duration;
            this.sample = sample;
        }

        public final int getDuration() {
            return this.duration;
        }

        public final boolean getSample() {
            return this.sample;
        }

        public final int component1() {
            return this.duration;
        }

        public final boolean component2() {
            return this.sample;
        }

        @NotNull
        public final InitTPSProfile copy(int duration, boolean sample) {
            return new InitTPSProfile(duration, sample);
        }

        public static /* synthetic */ InitTPSProfile copy$default(InitTPSProfile initTPSProfile, int n, boolean bl, int n2, Object object) {
            if ((n2 & 1) != 0) {
                n = initTPSProfile.duration;
            }
            if ((n2 & 2) != 0) {
                bl = initTPSProfile.sample;
            }
            return initTPSProfile.copy(n, bl);
        }

        @NotNull
        public String toString() {
            return "InitTPSProfile(duration=" + this.duration + ", sample=" + this.sample + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.duration);
            result = result * 31 + Boolean.hashCode(this.sample);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InitTPSProfile)) {
                return false;
            }
            InitTPSProfile initTPSProfile = (InitTPSProfile)other;
            if (this.duration != initTPSProfile.duration) {
                return false;
            }
            return this.sample == initTPSProfile.sample;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(InitTPSProfile self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.duration);
            output.encodeBooleanElement(serialDesc, 1, self.sample);
        }

        public /* synthetic */ InitTPSProfile(int seen0, int duration, boolean sample, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (3 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)3, (SerialDescriptor)InitTPSProfile$$serializer.INSTANCE.getDescriptor());
            }
            this.duration = duration;
            this.sample = sample;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/C2SPacket$InitTPSProfile$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/net/C2SPacket$InitTPSProfile;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<InitTPSProfile> serializer() {
                return (KSerializer)InitTPSProfile$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u00a8\u0006\u0006"}, d2={"Lobservable/net/C2SPacket$RequestAvailability;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "observable"})
    public static final class RequestAvailability {
        @NotNull
        public static final RequestAvailability INSTANCE = new RequestAvailability();
        private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate;

        private RequestAvailability() {
        }

        @NotNull
        public final KSerializer<RequestAvailability> serializer() {
            return this.get$cachedSerializer();
        }

        private final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer)$cachedSerializer$delegate.getValue();
        }

        static {
            $cachedSerializer$delegate = LazyKt.lazy((LazyThreadSafetyMode)LazyThreadSafetyMode.PUBLICATION, () -> (KSerializer)new ObjectSerializer("observable.net.C2SPacket.RequestAvailability", (Object)INSTANCE, new Annotation[0]));
        }
    }
}

