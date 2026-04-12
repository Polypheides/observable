package observable.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTickList;
import observable.server.NativeProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.Consumer;

@Mixin(EntityTickList.class)
public abstract class ObservableEntityMixin {
    @Redirect(method = "forEach", at = @At(value = "INVOKE", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private void Observable$onTickEntity(Consumer<Entity> consumer, Object entity) {
        if (!observable.Props.notProcessing.get() && entity instanceof Entity && !((Entity) entity).level().isClientSide()) {
            NativeProfiler.tickEntity((Entity) entity, consumer);
        } else {
            consumer.accept((Entity) entity);
        }
    }
}
