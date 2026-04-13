package observable.client;

import observable.mixin.ObservableShaderPipelineMixin;
import observable.mixin.RenderTypeAccessor;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.Optional;

public class ObservableRenderTypes {
    private static RenderType GLOW_LINES;

    public static RenderType getGlowLines() {
        if (GLOW_LINES == null) {
            GLOW_LINES = RenderTypeAccessor.callCreate(
                "observable_glow_lines",
                RenderSetup.builder(
                    com.mojang.blaze3d.pipeline.RenderPipeline.builder(ObservableShaderPipelineMixin.getLinesSnippet())
                .withDepthStencilState(Optional.empty()) // THE X-RAY PART! (Disables depth test/write)
                .withColorTargetState(new com.mojang.blaze3d.pipeline.ColorTargetState(
                    Optional.of(com.mojang.blaze3d.pipeline.BlendFunction.TRANSLUCENT),
                    com.mojang.blaze3d.pipeline.ColorTargetState.WRITE_ALL
                ))
                .withLocation(net.minecraft.resources.Identifier.fromNamespaceAndPath("observable", "glow_lines"))
                .build()
                ).createRenderSetup()
            );
        }
        return GLOW_LINES;
    }
}
