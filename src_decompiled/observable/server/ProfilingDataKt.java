/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  kotlinx.serialization.UseSerializers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package observable.server;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.UseSerializers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import observable.net.BlockEntitySerializer;
import observable.net.BlockPosSerializer;
import observable.net.EntitySerializer;
import observable.net.ResourceLocationSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 3, 0}, k=2, xi=48, d1={"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a8\u0006\u0004"}, d2={"getPosition", "Lnet/minecraft/core/BlockPos;", "obj", "", "observable"})
@UseSerializers(serializerClasses={EntitySerializer.class, ResourceLocationSerializer.class, BlockEntitySerializer.class, BlockPosSerializer.class})
public final class ProfilingDataKt {
    @NotNull
    public static final BlockPos getPosition(@Nullable Object obj) {
        BlockPos blockPos;
        Object object = obj;
        if (object instanceof Entity) {
            BlockPos blockPos2 = ((Entity)obj).blockPosition();
            blockPos = blockPos2;
            Intrinsics.checkNotNullExpressionValue((Object)blockPos2, (String)"blockPosition(...)");
        } else if (object instanceof BlockPos) {
            blockPos = (BlockPos)obj;
        } else {
            BlockPos blockPos3 = BlockPos.ZERO;
            blockPos = blockPos3;
            Intrinsics.checkNotNullExpressionValue((Object)blockPos3, (String)"ZERO");
        }
        return blockPos;
    }
}

