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
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.CompositeEncoder
 *  kotlinx.serialization.encoding.Decoder
 *  kotlinx.serialization.encoding.Encoder
 *  kotlinx.serialization.internal.PluginExceptionsKt
 *  kotlinx.serialization.internal.SerializationConstructorMarker
 *  net.minecraft.core.BlockPos
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.net;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import net.minecraft.core.BlockPos;
import observable.net.BlockPosSerializer$SerializableBlockPos$;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0096\u0080\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2={"Lobservable/net/BlockPosSerializer;", "Lkotlinx/serialization/KSerializer;", "Lnet/minecraft/core/BlockPos;", "<init>", "()V", "delegate", "Lobservable/net/BlockPosSerializer$SerializableBlockPos;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "SerializableBlockPos", "observable"})
public final class BlockPosSerializer
implements KSerializer<BlockPos> {
    @NotNull
    private final KSerializer<SerializableBlockPos> delegate = SerializableBlockPos.Companion.serializer();
    @NotNull
    private final SerialDescriptor descriptor = this.delegate.getDescriptor();

    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public BlockPos deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter((Object)decoder, (String)"decoder");
        return ((SerializableBlockPos)this.delegate.deserialize(decoder)).getPos();
    }

    public void serialize(@NotNull Encoder encoder, @NotNull BlockPos value) {
        Intrinsics.checkNotNullParameter((Object)encoder, (String)"encoder");
        Intrinsics.checkNotNullParameter((Object)value, (String)"value");
        this.delegate.serialize(encoder, (Object)new SerializableBlockPos(value));
    }

    @Serializable
    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000 (2\u00020\u0001:\u0002'(B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\u0006\u0010\nB3\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0004\b\u0006\u0010\u000eJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J'\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0003H\u00d6\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eH\u00d6\u0081\u0004J%\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0001\u00a2\u0006\u0002\b&R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\b\u001a\u00020\t8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006)"}, d2={"Lobservable/net/BlockPosSerializer$SerializableBlockPos;", "", "x", "", "y", "z", "<init>", "(III)V", "pos", "Lnet/minecraft/core/BlockPos;", "(Lnet/minecraft/core/BlockPos;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IIIILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getX", "()I", "getY", "getZ", "getPos", "()Lnet/minecraft/core/BlockPos;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$observable", "$serializer", "Companion", "observable"})
    private static final class SerializableBlockPos {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final int x;
        private final int y;
        private final int z;

        public SerializableBlockPos(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getZ() {
            return this.z;
        }

        public SerializableBlockPos(@NotNull BlockPos pos) {
            Intrinsics.checkNotNullParameter((Object)pos, (String)"pos");
            this(pos.getX(), pos.getY(), pos.getZ());
        }

        @NotNull
        public final BlockPos getPos() {
            return new BlockPos(this.x, this.y, this.z);
        }

        public final int component1() {
            return this.x;
        }

        public final int component2() {
            return this.y;
        }

        public final int component3() {
            return this.z;
        }

        @NotNull
        public final SerializableBlockPos copy(int x, int y, int z) {
            return new SerializableBlockPos(x, y, z);
        }

        public static /* synthetic */ SerializableBlockPos copy$default(SerializableBlockPos serializableBlockPos, int n, int n2, int n3, int n4, Object object) {
            if ((n4 & 1) != 0) {
                n = serializableBlockPos.x;
            }
            if ((n4 & 2) != 0) {
                n2 = serializableBlockPos.y;
            }
            if ((n4 & 4) != 0) {
                n3 = serializableBlockPos.z;
            }
            return serializableBlockPos.copy(n, n2, n3);
        }

        @NotNull
        public String toString() {
            return "SerializableBlockPos(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.x);
            result = result * 31 + Integer.hashCode(this.y);
            result = result * 31 + Integer.hashCode(this.z);
            return result;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SerializableBlockPos)) {
                return false;
            }
            SerializableBlockPos serializableBlockPos = (SerializableBlockPos)other;
            if (this.x != serializableBlockPos.x) {
                return false;
            }
            if (this.y != serializableBlockPos.y) {
                return false;
            }
            return this.z == serializableBlockPos.z;
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$observable(SerializableBlockPos self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeIntElement(serialDesc, 0, self.x);
            output.encodeIntElement(serialDesc, 1, self.y);
            output.encodeIntElement(serialDesc, 2, self.z);
        }

        public /* synthetic */ SerializableBlockPos(int seen0, int x, int y, int z, SerializationConstructorMarker serializationConstructorMarker) {
            if (7 != (7 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException((int)seen0, (int)7, (SerialDescriptor)SerializableBlockPos$$serializer.INSTANCE.getDescriptor());
            }
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u0007"}, d2={"Lobservable/net/BlockPosSerializer$SerializableBlockPos$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lobservable/net/BlockPosSerializer$SerializableBlockPos;", "observable"})
        public static final class Companion {
            private Companion() {
            }

            @NotNull
            public final KSerializer<SerializableBlockPos> serializer() {
                return (KSerializer)SerializableBlockPos$$serializer.INSTANCE;
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }
        }
    }
}

