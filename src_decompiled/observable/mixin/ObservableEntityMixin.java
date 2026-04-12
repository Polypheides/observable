/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.entity.EntityTickList
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package observable.mixin;

import java.util.function.Consumer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityTickList;
import observable.Props;
import observable.server.NativeProfiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={EntityTickList.class})
public abstract class ObservableEntityMixin {
    @Redirect(method={"forEach"}, at=@At(value="INVOKE", target="Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V"))
    private void observable$onTickEntity(Consumer<Entity> consumer, Object entity) {
        if (!Props.notProcessing.get() && entity instanceof Entity && !((Entity)entity).level().isClientSide()) {
            NativeProfiler.tickEntity((Entity)entity, consumer);
        } else {
            consumer.accept((Entity)entity);
        }
    }
}

