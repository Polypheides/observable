package observable.mixin;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LevelRenderer.class)
public interface ObservableLevelRendererMixin {
    @Accessor("submitNodeStorage")
    SubmitNodeStorage getSubmitNodeStorage();

    @Accessor("levelRenderState")
    LevelRenderState getLevelRenderState();

    @Accessor("targets")
    net.minecraft.client.renderer.LevelTargetBundle getTargets();
}
