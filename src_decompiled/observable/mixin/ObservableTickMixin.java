/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package observable.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import observable.Props;
import observable.server.NativeProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ServerLevel.class})
public abstract class ObservableTickMixin {
    @Redirect(method={"tickBlock"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/state/BlockState;tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V"))
    public final void observable$onTickBlock(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!Props.notProcessing.get()) {
            NativeProfiler.tickBlock(state, level, pos, random);
        } else {
            state.tick(level, pos, random);
        }
    }

    @Redirect(method={"tickFluid"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/material/FluidState;tick(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V"))
    public final void observable$onTickLiquid(FluidState state, ServerLevel level, BlockPos pos, BlockState blockState) {
        if (!Props.notProcessing.get()) {
            NativeProfiler.tickFluid(state, level, pos, blockState);
        } else {
            state.tick(level, pos, blockState);
        }
    }
}

