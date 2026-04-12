package observable.mixin;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TickingBlockEntity;
import observable.server.NativeProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Level.class)
public abstract class ObservableTileMixin {
    @Redirect(method = "tickBlockEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/TickingBlockEntity;tick()V"))
    public final void Observable$redirectTick(TickingBlockEntity ticker) {
        if (!observable.Props.notProcessing.get() && !((Level) (Object) this).isClientSide()) {
            NativeProfiler.tickBlockEntity(ticker, (Level) (Object) this);
        } else {
            ticker.tick();
        }
    }
}
