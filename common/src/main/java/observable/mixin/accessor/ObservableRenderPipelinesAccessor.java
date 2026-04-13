package observable.mixin.accessor;

import net.minecraft.client.renderer.RenderPipelines;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(net.minecraft.client.renderer.RenderPipelines.class)
public interface ObservableRenderPipelinesAccessor {
    @Accessor("LINES_SNIPPET")
    static RenderPipeline.Snippet getLinesSnippet() {
        throw new UnsupportedOperationException();
    }
}
