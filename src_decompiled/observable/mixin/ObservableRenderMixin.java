/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.buffers.GpuBufferSlice
 *  com.mojang.blaze3d.resource.GraphicsResourceAllocator
 *  net.minecraft.client.Camera
 *  net.minecraft.client.DeltaTracker
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.LevelRenderer
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.chunk.ChunkSectionsToRender
 *  net.minecraft.client.renderer.state.level.CameraRenderState
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fStack
 *  org.joml.Matrix4fc
 *  org.joml.Vector4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package observable.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import observable.client.ProfilerBridge;
import observable.mixin.ObservableCameraMixin;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LevelRenderer.class})
public abstract class ObservableRenderMixin {
    @Unique
    private Matrix4fc lastProjection;
    @Unique
    private Matrix4fc lastModelView;
    @Unique
    private Vec3 lastCameraPos;
    @Unique
    private Matrix4fStack capturedStack;

    @Inject(method={"renderLevel"}, at={@At(value="HEAD")})
    private void observable$onRenderLevelHead(GraphicsResourceAllocator resourceAllocator, DeltaTracker deltaTracker, boolean renderOutline, CameraRenderState cameraState, Matrix4fc modelViewMatrix, GpuBufferSlice terrainFog, Vector4f fogColor, boolean shouldRenderSky, ChunkSectionsToRender chunkSectionsToRender, CallbackInfo ci) {
        this.lastProjection = cameraState.projectionMatrix;
        this.lastCameraPos = cameraState.pos;
        Camera camera = ((ObservableCameraMixin)Minecraft.getInstance().gameRenderer).getMainCamera();
        Matrix4f mvp = camera.getViewRotationProjectionMatrix(new Matrix4f());
        Matrix4f view = new Matrix4f((Matrix4fc)cameraState.projectionMatrix).invert().mul((Matrix4fc)mvp);
        this.lastModelView = view;
        Matrix4f rotOnly = new Matrix4f((Matrix4fc)view);
        rotOnly.m30(0.0f);
        rotOnly.m31(0.0f);
        rotOnly.m32(0.0f);
        this.capturedStack = new Matrix4fStack(100);
        this.capturedStack.set((Matrix4fc)rotOnly);
    }

    @Inject(method={"renderLevel"}, at={@At(value="RETURN")})
    private void observable$onRenderLevelReturn(CallbackInfo ci) {
        if (this.capturedStack != null && ProfilerBridge.getBridge() != null) {
            ProfilerBridge.render(this.lastProjection, this.lastModelView, this.lastCameraPos, this.capturedStack);
            MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
            bufferSource.endBatch(ProfilerBridge.getTranslucent());
            bufferSource.endBatch(ProfilerBridge.getLines());
        }
    }
}

