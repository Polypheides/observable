/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.DeltaTracker
 *  net.minecraft.client.KeyMapping$Category
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiGraphicsExtractor
 *  net.minecraft.client.renderer.MultiBufferSource
 *  net.minecraft.client.renderer.MultiBufferSource$BufferSource
 *  net.minecraft.client.renderer.rendertype.RenderType
 *  net.minecraft.client.renderer.rendertype.RenderTypes
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.Identifier
 *  net.minecraft.world.phys.Vec3
 *  org.joml.Matrix4fStack
 *  org.joml.Matrix4fc
 */
package observable.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import observable.Observable;
import observable.client.ClientBridge;
import org.joml.Matrix4fStack;
import org.joml.Matrix4fc;

public class ProfilerBridge {
    public static boolean isOverlayEnabled = true;
    private static int renderDepth = 0;
    private static Consumer<DeltaTracker> screenOpener;
    private static BiConsumer<GuiGraphicsExtractor, DeltaTracker> hudRenderer;
    private static WorldRenderer worldRenderer;
    private static ClientBridge bridge;
    public static final KeyMapping.Category CATEGORY;

    public static void setScreenOpener(Consumer<DeltaTracker> opener) {
        screenOpener = opener;
    }

    public static void setHudRenderer(BiConsumer<GuiGraphicsExtractor, DeltaTracker> renderer) {
        hudRenderer = renderer;
    }

    public static void setWorldRenderer(WorldRenderer renderer) {
        worldRenderer = renderer;
    }

    public static void setBridge(ClientBridge b) {
        bridge = b;
    }

    public static ClientBridge getBridge() {
        return bridge;
    }

    public static void clear() {
        if (bridge != null) {
            bridge.clear();
        }
        Observable.INSTANCE.clearResults();
    }

    public static void openProfileScreen() {
        if (screenOpener != null) {
            screenOpener.accept(DeltaTracker.ZERO);
        }
    }

    public static void openProfileScreen(DeltaTracker delta) {
        if (screenOpener != null) {
            screenOpener.accept(delta);
        }
    }

    public static boolean isSettingsKeyClicked() {
        return bridge != null && bridge.getSettingsKey().consumeClick();
    }

    public static boolean isOverlayKeyClicked() {
        return bridge != null && bridge.getOverlayKey().consumeClick();
    }

    public static void renderHud(GuiGraphicsExtractor graphics, DeltaTracker delta) {
        if (hudRenderer != null) {
            hudRenderer.accept(graphics, delta);
        }
    }

    public static RenderType getTranslucent() {
        return RenderTypes.debugFilledBox();
    }

    public static RenderType getLines() {
        return RenderTypes.LINES;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void render(Matrix4fc projection, Matrix4fc modelView, Vec3 cameraPos, Matrix4fStack stack) {
        if (renderDepth > 0) {
            return;
        }
        ++renderDepth;
        try {
            if (worldRenderer != null) {
                MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
                worldRenderer.render(stack, (MultiBufferSource)bufferSource, cameraPos, modelView);
            }
        }
        finally {
            --renderDepth;
        }
    }

    public static void drawWorldPass(Matrix4fStack stack, MultiBufferSource bufferSource, Vec3 camPos, List<BlockEntry> entries) {
        if (entries.isEmpty()) {
            return;
        }
        VertexConsumer faceBuffer = bufferSource.getBuffer(ProfilerBridge.getTranslucent());
        for (BlockEntry entry : entries) {
            stack.pushMatrix();
            double x = (double)entry.pos.getX() - camPos.x;
            double y = (double)entry.pos.getY() - camPos.y;
            double z = (double)entry.pos.getZ() - camPos.z;
            stack.translate((float)x, (float)y, (float)z);
            int c = entry.color;
            int r = c >> 16 & 0xFF;
            int g = c >> 8 & 0xFF;
            int b = c & 0xFF;
            int a = entry.alpha;
            float x0 = 0.0f;
            float y0 = 0.0f;
            float z0 = 0.0f;
            float x1 = 1.0f;
            float y1 = 1.0f;
            float z1 = 1.0f;
            ProfilerBridge.v(faceBuffer, stack, x0, y0, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y0, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y1, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y1, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y0, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y0, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y1, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y1, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y0, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y1, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y1, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y0, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y0, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y0, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y1, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y1, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y1, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y1, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y1, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y1, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y0, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y0, z0, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x1, y0, z1, r, g, b, a);
            ProfilerBridge.v(faceBuffer, stack, x0, y0, z1, r, g, b, a);
            stack.popMatrix();
        }
        if (bufferSource instanceof MultiBufferSource.BufferSource) {
            ((MultiBufferSource.BufferSource)bufferSource).endBatch(ProfilerBridge.getTranslucent());
        }
        VertexConsumer lineBuffer = bufferSource.getBuffer(ProfilerBridge.getLines());
        for (BlockEntry entry : entries) {
            stack.pushMatrix();
            stack.translate((float)((double)entry.pos.getX() - camPos.x), (float)((double)entry.pos.getY() - camPos.y), (float)((double)entry.pos.getZ() - camPos.z));
            int c = entry.color;
            int r = c >> 16 & 0xFF;
            int g = c >> 8 & 0xFF;
            int b = c & 0xFF;
            int a = 223;
            float x0 = 0.0f;
            float y0 = 0.0f;
            float z0 = 0.0f;
            float x1 = 1.0f;
            float y1 = 1.0f;
            float z1 = 1.0f;
            ProfilerBridge.l(lineBuffer, stack, x0, y0, z0, x1, y0, z0, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x1, y0, z0, x1, y0, z1, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x1, y0, z1, x0, y0, z1, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x0, y0, z1, x0, y0, z0, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x0, y1, z0, x1, y1, z0, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x1, y1, z0, x1, y1, z1, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x1, y1, z1, x0, y1, z1, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x0, y1, z1, x0, y1, z0, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x0, y0, z0, x0, y1, z0, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x1, y0, z0, x1, y1, z0, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x1, y0, z1, x1, y1, z1, r, g, b, a);
            ProfilerBridge.l(lineBuffer, stack, x0, y0, z1, x0, y1, z1, r, g, b, a);
            stack.popMatrix();
        }
    }

    private static void v(VertexConsumer buf, Matrix4fStack stack, float x, float y, float z, int r, int g, int b, int a) {
        buf.addVertex((Matrix4fc)stack, x, y, z).setColor(r, g, b, a);
    }

    private static void l(VertexConsumer buf, Matrix4fStack stack, float x1, float y1, float z1, float x2, float y2, float z2, int r, int g, int b, int a) {
        buf.addVertex((Matrix4fc)stack, x1, y1, z1).setColor(r, g, b, a).setNormal(0.0f, 0.0f, 1.0f).setLineWidth(2.0f);
        buf.addVertex((Matrix4fc)stack, x2, y2, z2).setColor(r, g, b, a).setNormal(0.0f, 0.0f, 1.0f).setLineWidth(2.0f);
    }

    static {
        CATEGORY = KeyMapping.Category.register((Identifier)Identifier.fromNamespaceAndPath((String)"observable", (String)"main"));
    }

    public static interface WorldRenderer {
        public void render(Matrix4fStack var1, MultiBufferSource var2, Vec3 var3, Matrix4fc var4);
    }

    public static class BlockEntry {
        public final BlockPos pos;
        public final int color;
        public final int alpha;

        public BlockEntry(BlockPos pos, int color, int alpha) {
            this.pos = pos;
            this.color = color;
            this.alpha = alpha;
        }
    }
}

