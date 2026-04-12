/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.StringCompanionObject
 *  kotlinx.serialization.KSerializer
 *  kotlinx.serialization.builtins.BuiltinSerializersKt
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.Decoder
 *  kotlinx.serialization.encoding.Encoder
 *  net.minecraft.resources.Identifier
 *  org.jetbrains.annotations.NotNull
 */
package observable.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0096\u0080\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2={"Lobservable/net/ResourceLocationSerializer;", "Lkotlinx/serialization/KSerializer;", "Lnet/minecraft/resources/Identifier;", "<init>", "()V", "delegate", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "observable"})
public final class ResourceLocationSerializer
implements KSerializer<Identifier> {
    @NotNull
    private final KSerializer<String> delegate = BuiltinSerializersKt.serializer((StringCompanionObject)StringCompanionObject.INSTANCE);
    @NotNull
    private final SerialDescriptor descriptor = this.delegate.getDescriptor();

    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public Identifier deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter((Object)decoder, (String)"decoder");
        Identifier identifier = Identifier.parse((String)((String)this.delegate.deserialize(decoder)));
        Intrinsics.checkNotNullExpressionValue((Object)identifier, (String)"parse(...)");
        return identifier;
    }

    public void serialize(@NotNull Encoder encoder, @NotNull Identifier value) {
        Intrinsics.checkNotNullParameter((Object)encoder, (String)"encoder");
        Intrinsics.checkNotNullParameter((Object)value, (String)"value");
        String string = value.toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        this.delegate.serialize(encoder, (Object)string);
    }
}

