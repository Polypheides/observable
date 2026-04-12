/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.TickingBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package observable.mixin;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import observable.Props;
import observable.server.NativeProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Level.class})
public abstract class ObservableTileMixin {
    @Redirect(method={"tickBlockEntities"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/TickingBlockEntity;tick()V"))
    public final void observable$redirectTick(TickingBlockEntity ticker) {
        if (!Props.notProcessing.get() && !((Level)this).isClientSide()) {
            NativeProfiler.tickBlockEntity(ticker, (Level)this);
        } else {
            ticker.tick();
        }
    }
}

