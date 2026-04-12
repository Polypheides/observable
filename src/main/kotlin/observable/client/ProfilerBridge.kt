package observable.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.DeltaTracker
import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.rendertype.RenderType
import net.minecraft.client.renderer.rendertype.RenderTypes
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.world.phys.Vec3
import org.joml.Matrix4f
import org.joml.Matrix4fStack
import org.joml.Matrix4fc
import net.minecraft.client.gui.Font
import java.util.function.BiConsumer
import java.util.function.Consumer

object ProfilerBridge {
    const val MAX_DISTANCE_SQ = 4096.0
    private var renderDepth = 0
    private var screenOpener: Consumer<DeltaTracker>? = null
    private var hudRenderer: BiConsumer<GuiGraphicsExtractor, DeltaTracker>? = null
    private var worldRenderer: observable.WorldRenderer? = null
    private var bridge: ClientBridge? = null

    val CATEGORY: KeyMapping.Category = KeyMapping.Category
        .register(Identifier.fromNamespaceAndPath("observable", "main"))

    @JvmStatic
    fun setScreenOpener(opener: Consumer<DeltaTracker>) {
        screenOpener = opener
    }

    @JvmStatic
    fun setHudRenderer(renderer: BiConsumer<GuiGraphicsExtractor, DeltaTracker>) {
        hudRenderer = renderer
    }

    @JvmStatic
    fun setWorldRenderer(renderer: observable.WorldRenderer) {
        worldRenderer = renderer
    }

    @JvmStatic
    fun setBridge(b: ClientBridge) {
        bridge = b
    }

    @JvmStatic
    fun getBridge(): ClientBridge? {
        return bridge
    }

    @JvmStatic
    fun clear() {
        // Clear references
    }

    @JvmStatic
    fun openProfileScreen() {
        screenOpener?.accept(Minecraft.getInstance().deltaTracker)
    }

    @JvmStatic
    fun isSettingsKeyClicked(): Boolean {
        return bridge?.settingsKey?.consumeClick() ?: false
    }

    @JvmStatic
    fun isOverlayKeyClicked(): Boolean {
        return bridge?.overlayKey?.consumeClick() ?: false
    }

    @JvmStatic
    fun renderHud(graphics: GuiGraphicsExtractor, delta: DeltaTracker) {
        hudRenderer?.accept(graphics, delta)
    }

    @JvmStatic
    fun getTranslucent(): RenderType {
        return RenderTypes.debugFilledBox()
    }

    @JvmStatic
    fun getLines(): RenderType {
        return RenderTypes.LINES
    }

    @JvmStatic
    fun render(projection: Matrix4fc?, modelView: Matrix4fc, cameraPos: Vec3, stack: Matrix4fStack, delta: DeltaTracker, collector: SubmitNodeCollector, cameraState: CameraRenderState) {
        worldRenderer?.let {
            val bufferSource = Minecraft.getInstance().renderBuffers().bufferSource()
            it.render(stack, bufferSource, cameraPos, modelView, delta, collector, cameraState)
        }
    }

    @JvmStatic
    fun drawWorldPass(stack: Matrix4fStack, bufferSource: MultiBufferSource, camPos: Vec3, entries: List<BlockEntry>, labels: List<LabelEntry>, collector: SubmitNodeCollector, cameraState: CameraRenderState) {
        // Draw boxes using custom geometry submission to ensure correct translucent pass
        if (entries.isNotEmpty()) {
            val poseStack = PoseStack() // Use a fresh PoseStack for submission
            collector.submitCustomGeometry(poseStack, getTranslucent()) { pose, buffer ->
                for (entry in entries) {
                    val x = entry.pos.x.toDouble() - camPos.x
                    val y = entry.pos.y.toDouble() - camPos.y
                    val z = entry.pos.z.toDouble() - camPos.z
                    f(buffer, pose.pose(), x.toFloat(), y.toFloat(), z.toFloat(), (x + 1.0).toFloat(), (y + 1.0).toFloat(), (z + 1.0).toFloat(),
                            (entry.color shr 16) and 0xFF, (entry.color shr 8) and 0xFF, entry.color and 0xFF, entry.alpha)
                }
            }
        }

        // Draw lines (wireframes)
        val lineBuffer = bufferSource.getBuffer(getLines())
        for (entry in entries) {
            val x0 = (entry.pos.x.toDouble() - camPos.x).toFloat()
            val y0 = (entry.pos.y.toDouble() - camPos.y).toFloat()
            val z0 = (entry.pos.z.toDouble() - camPos.z).toFloat()
            val x1 = x0 + 1.0f; val y1 = y0 + 1.0f; val z1 = z0 + 1.0f
            val r = (entry.color shr 16) and 0xFF; val g = (entry.color shr 8) and 0xFF; val b = entry.color and 0xFF; val a = entry.alpha
            l(lineBuffer, stack, x0, y0, z0, x1, y0, z0, r, g, b, a)
            l(lineBuffer, stack, x1, y0, z0, x1, y0, z1, r, g, b, a)
            l(lineBuffer, stack, x1, y0, z1, x0, y0, z1, r, g, b, a)
            l(lineBuffer, stack, x0, y0, z1, x0, y0, z0, r, g, b, a)
            l(lineBuffer, stack, x0, y1, z0, x1, y1, z0, r, g, b, a)
            l(lineBuffer, stack, x1, y1, z0, x1, y1, z1, r, g, b, a)
            l(lineBuffer, stack, x1, y1, z1, x0, y1, z1, r, g, b, a)
            l(lineBuffer, stack, x0, y1, z1, x0, y1, z0, r, g, b, a)
            l(lineBuffer, stack, x0, y0, z0, x0, y1, z0, r, g, b, a)
            l(lineBuffer, stack, x1, y0, z0, x1, y1, z0, r, g, b, a)
            l(lineBuffer, stack, x1, y0, z1, x1, y1, z1, r, g, b, a)
            l(lineBuffer, stack, x0, y0, z1, x0, y1, z1, r, g, b, a)
        }

        // Draw labels
        val font = Minecraft.getInstance().font
        
        // DEBUG: Hardcoded label
        val finalLabels = labels.toMutableList()
        finalLabels.add(ProfilerBridge.LabelEntry(Vec3(0.0, 100.0, 0.0), "DEBUG WORLD", 0xFFFFFF))
        
        for (label in finalLabels) {
            val distSq = label.pos.distanceToSqr(camPos)
            if (distSq > ProfilerBridge.MAX_DISTANCE_SQ) continue

            stack.pushMatrix()
            stack.translate(
                (label.pos.x - camPos.x).toFloat(), 
                (label.pos.y - camPos.y + 0.5f).toFloat(), 
                (label.pos.z - camPos.z).toFloat()
            )
            
            stack.rotate(cameraState.orientation)
            stack.scale(0.05f, -0.05f, 0.05f)
            
            val text = label.text
            val width = font.width(text)
            
            font.drawInBatch(
                text,
                -width / 2.0f,
                -4.0f,
                0xFFFFFFFF.toInt(),
                false,
                stack,
                bufferSource,
                Font.DisplayMode.SEE_THROUGH,
                0, 
                15728880
            )
            stack.popMatrix()
        }
    }

    private fun f(c: VertexConsumer, m: Matrix4fc, x0: Float, y0: Float, z0: Float, x1: Float, y1: Float, z1: Float, r: Int, g: Int, b: Int, a: Int) {
        v(c, m, x0, y0, z0, r, g, b, a)
        v(c, m, x0, y1, z0, r, g, b, a)
        v(c, m, x1, y1, z0, r, g, b, a)
        v(c, m, x1, y0, z0, r, g, b, a)
        v(c, m, x0, y0, z1, r, g, b, a)
        v(c, m, x1, y0, z1, r, g, b, a)
        v(c, m, x1, y1, z1, r, g, b, a)
        v(c, m, x0, y1, z1, r, g, b, a)
        v(c, m, x0, y0, z0, r, g, b, a)
        v(c, m, x0, y0, z1, r, g, b, a)
        v(c, m, x0, y1, z1, r, g, b, a)
        v(c, m, x0, y1, z0, r, g, b, a)
        v(c, m, x1, y0, z0, r, g, b, a)
        v(c, m, x1, y1, z0, r, g, b, a)
        v(c, m, x1, y1, z1, r, g, b, a)
        v(c, m, x1, y0, z1, r, g, b, a)
        v(c, m, x0, y0, z0, r, g, b, a)
        v(c, m, x1, y0, z0, r, g, b, a)
        v(c, m, x1, y0, z1, r, g, b, a)
        v(c, m, x0, y0, z1, r, g, b, a)
        v(c, m, x0, y1, z0, r, g, b, a)
        v(c, m, x0, y1, z1, r, g, b, a)
        v(c, m, x1, y1, z1, r, g, b, a)
        v(c, m, x1, y1, z0, r, g, b, a)
    }

    private fun l(c: VertexConsumer, m: Matrix4fc, x0: Float, y0: Float, z0: Float, x1: Float, y1: Float, z1: Float, r: Int, g: Int, b: Int, a: Int) {
        c.addVertex(m, x0, y0, z0).setColor(r, g, b, a).setNormal(0.0f, 1.0f, 0.0f).setLineWidth(1.0f)
        c.addVertex(m, x1, y1, z1).setColor(r, g, b, a).setNormal(0.0f, 1.0f, 0.0f).setLineWidth(1.0f)
    }

    private fun v(c: VertexConsumer, m: Matrix4fc, x: Float, y: Float, z: Float, r: Int, g: Int, b: Int, a: Int) {
        c.addVertex(m, x, y, z).setColor(r, g, b, a)
    }

    data class BlockEntry(val pos: BlockPos, val color: Int, val alpha: Int)
    data class LabelEntry(val pos: Vec3, val text: String, val color: Int)
}
