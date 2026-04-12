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
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.Identifier
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.level.Level
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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003:\u0001\u0019B\u001d\u0012\u0014\u0010\u0005\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0004\u00a2\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0096\u0080\u0004R\u001f\u0010\u0005\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010\r\u001a\u00020\u000eX\u0096\u0084\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001a"}, d2={"Lobservable/net/ResourceKeySerializer;", "T", "", "Lkotlinx/serialization/KSerializer;", "Lnet/minecraft/resources/ResourceKey;", "registryKey", "Lnet/minecraft/core/Registry;", "<init>", "(Lnet/minecraft/resources/ResourceKey;)V", "getRegistryKey", "()Lnet/minecraft/resources/ResourceKey;", "delegate", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "Dimension", "observable"})
public class ResourceKeySerializer<T>
implements KSerializer<ResourceKey<T>> {
    @NotNull
    private final ResourceKey<? extends Registry<T>> registryKey;
    @NotNull
    private final KSerializer<String> delegate;
    @NotNull
    private final SerialDescriptor descriptor;

    public ResourceKeySerializer(@NotNull ResourceKey<? extends Registry<T>> registryKey) {
        Intrinsics.checkNotNullParameter(registryKey, (String)"registryKey");
        this.registryKey = registryKey;
        this.delegate = BuiltinSerializersKt.serializer((StringCompanionObject)StringCompanionObject.INSTANCE);
        this.descriptor = this.delegate.getDescriptor();
    }

    @NotNull
    public final ResourceKey<? extends Registry<T>> getRegistryKey() {
        return this.registryKey;
    }

    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @NotNull
    public ResourceKey<T> deserialize(@NotNull Decoder decoder) {
        Intrinsics.checkNotNullParameter((Object)decoder, (String)"decoder");
        ResourceKey resourceKey = ResourceKey.create(this.registryKey, (Identifier)Identifier.parse((String)((String)this.delegate.deserialize(decoder))));
        Intrinsics.checkNotNullExpressionValue((Object)resourceKey, (String)"create(...)");
        return resourceKey;
    }

    public void serialize(@NotNull Encoder encoder, @NotNull ResourceKey<T> value) {
        Intrinsics.checkNotNullParameter((Object)encoder, (String)"encoder");
        Intrinsics.checkNotNullParameter(value, (String)"value");
        String string = value.identifier().toString();
        Intrinsics.checkNotNullExpressionValue((Object)string, (String)"toString(...)");
        this.delegate.serialize(encoder, (Object)string);
    }

    @Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2={"Lobservable/net/ResourceKeySerializer$Dimension;", "Lobservable/net/ResourceKeySerializer;", "Lnet/minecraft/world/level/Level;", "<init>", "()V", "observable"})
    public static final class Dimension
    extends ResourceKeySerializer<Level> {
        public Dimension() {
            ResourceKey resourceKey = Registries.DIMENSION;
            Intrinsics.checkNotNullExpressionValue((Object)resourceKey, (String)"DIMENSION");
            super(resourceKey);
        }
    }
}

