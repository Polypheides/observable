/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.TickingBlockEntity
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 */
package observable.server;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class NativeProfiler {
    private static BiConsumer<TickingBlockEntity, Level> blockEntityTicker = (be, lvl) -> be.tick();
    private static EntityTicker entityTicker = (e, method) -> method.accept(e);
    private static BlockTicker blockTicker = BlockBehaviour.BlockStateBase::tick;
    private static FluidTicker fluidTicker = FluidState::tick;

    public static void setBlockEntityTicker(BiConsumer<TickingBlockEntity, Level> ticker) {
        blockEntityTicker = ticker;
    }

    public static void setEntityTicker(EntityTicker ticker) {
        entityTicker = ticker;
    }

    public static void setBlockTicker(BlockTicker ticker) {
        blockTicker = ticker;
    }

    public static void setFluidTicker(FluidTicker ticker) {
        fluidTicker = ticker;
    }

    public static void tickBlockEntity(TickingBlockEntity be, Level level) {
        blockEntityTicker.accept(be, level);
    }

    public static void tickEntity(Entity entity, Consumer<Entity> tickMethod) {
        entityTicker.tick(entity, tickMethod);
    }

    public static void tickBlock(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        blockTicker.tick(state, level, pos, random);
    }

    public static void tickFluid(FluidState state, ServerLevel level, BlockPos pos, BlockState blockState) {
        fluidTicker.tick(state, level, pos, blockState);
    }

    public static interface EntityTicker {
        public void tick(Entity var1, Consumer<Entity> var2);
    }

    public static interface BlockTicker {
        public void tick(BlockState var1, ServerLevel var2, BlockPos var3, RandomSource var4);
    }

    public static interface FluidTicker {
        public void tick(FluidState var1, ServerLevel var2, BlockPos var3, BlockState var4);
    }
}

