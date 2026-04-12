/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.SourceDebugExtension
 *  kotlinx.serialization.KSerializer
 *  kotlinx.serialization.builtins.BuiltinSerializersKt
 *  kotlinx.serialization.descriptors.SerialDescriptor
 *  kotlinx.serialization.encoding.Decoder
 *  kotlinx.serialization.encoding.Encoder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.ClientLevel
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import observable.net.BlockPosSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u001c\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0096\u0080\u0004R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0013"}, d2={"Lobservable/net/BlockEntitySerializer;", "Lkotlinx/serialization/KSerializer;", "Lnet/minecraft/world/level/block/entity/BlockEntity;", "<init>", "()V", "delegate", "Lnet/minecraft/core/BlockPos;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "observable"})
@SourceDebugExtension(value={"SMAP\nBlockEntitySerializer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BlockEntitySerializer.kt\nobservable/net/BlockEntitySerializer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,23:1\n1#2:24\n*E\n"})
public final class BlockEntitySerializer
implements KSerializer<BlockEntity> {
    @NotNull
    private final KSerializer<BlockPos> delegate = BuiltinSerializersKt.getNullable((KSerializer)new BlockPosSerializer());
    @NotNull
    private final SerialDescriptor descriptor = this.delegate.getDescriptor();

    @NotNull
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Nullable
    public BlockEntity deserialize(@NotNull Decoder decoder) {
        Object object;
        Intrinsics.checkNotNullParameter((Object)decoder, (String)"decoder");
        BlockPos blockPos = (BlockPos)this.delegate.deserialize(decoder);
        if (blockPos != null) {
            BlockPos it = blockPos;
            boolean bl = false;
            ClientLevel clientLevel = Minecraft.getInstance().level;
            object = clientLevel != null ? clientLevel.getBlockEntity(it) : null;
        } else {
            object = null;
        }
        return object;
    }

    public void serialize(@NotNull Encoder encoder, @Nullable BlockEntity value) {
        Intrinsics.checkNotNullParameter((Object)encoder, (String)"encoder");
        BlockEntity blockEntity = value;
        this.delegate.serialize(encoder, blockEntity != null ? blockEntity.getBlockPos() : null);
    }
}

