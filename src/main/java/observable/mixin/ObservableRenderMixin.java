package observable.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import observable.client.ProfilerBridge;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelRenderer.class)
public abstract class ObservableRenderMixin {
    @Inject(
        method = "renderLevel",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/LevelRenderer;addLateDebugPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/state/level/CameraRenderState;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lorg/joml/Matrix4fc;)V",
            shift = At.Shift.AFTER
        )
    )
    private void observable$onRenderLevel(
        GraphicsResourceAllocator resourceAllocator,
        DeltaTracker deltaTracker,
        boolean renderOutline,
        CameraRenderState cameraState,
        Matrix4fc modelViewMatrix,
        GpuBufferSlice terrainFog,
        Vector4f fogColor,
        boolean shouldRenderSky,
        ChunkSectionsToRender chunkSectionsToRender,
        CallbackInfo ci,
        @com.llamalad7.mixinextras.sugar.Local(name = "frame") com.mojang.blaze3d.framegraph.FrameGraphBuilder frame
    ) {
        if (ProfilerBridge.getBridge() == null) return;

        com.mojang.blaze3d.framegraph.FramePass pass = frame.addPass("observable_render_pass");
        
        var targets = ((ObservableLevelRendererAccessor) this).getTargets();
        targets.main = pass.readsAndWrites(targets.main);

        pass.executes(() -> {
            var bufferSource = ((ObservableLevelRendererAccessor) this).getRenderBuffers().bufferSource();
            
            org.joml.Matrix4fStack poseStack = new org.joml.Matrix4fStack(16);
            poseStack.identity();

            // Use cameraState.pos for translation
            var collector = ((ObservableLevelRendererAccessor) this).getSubmitNodeStorage();
            ProfilerBridge.render(cameraState.projectionMatrix, modelViewMatrix, cameraState.pos, poseStack, deltaTracker, collector, cameraState);
            
            if (bufferSource instanceof net.minecraft.client.renderer.MultiBufferSource.BufferSource) {
                ((net.minecraft.client.renderer.MultiBufferSource.BufferSource) bufferSource).endBatch();
            }
        });
    }
}
