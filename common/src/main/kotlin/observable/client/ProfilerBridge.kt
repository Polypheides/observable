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
        bridge?.clear()
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
    fun render(projection: Matrix4fc?, modelView: Matrix4fc, cameraPos: Vec3, stack: Matrix4fc, delta: DeltaTracker, collector: SubmitNodeCollector, cameraState: CameraRenderState) {
        if (renderDepth > 0) {
            return
        }
        renderDepth++
        try {
            worldRenderer?.let {
                val bufferSource = Minecraft.getInstance().renderBuffers().bufferSource()
                it.render(stack, bufferSource, cameraPos, modelView, delta, collector, cameraState)
            }
        } finally {
            renderDepth--
        }
    }

    @JvmStatic
    fun drawWorldPass(stack: Matrix4fc, bufferSource: MultiBufferSource, camPos: Vec3, entries: List<BlockEntry>, labels: List<LabelEntry>, collector: SubmitNodeCollector, cameraState: CameraRenderState) {
        if (entries.isEmpty()) return
        
        val mode = ClientSettings.renderMode

        // 1. Draw X-Ray boxes
        if (mode == RenderMode.CUBES) {
            val buffer = bufferSource.getBuffer(observable.client.ObservableRenderTypes.getXRayBoxes())
            for (entry in entries) {
                val x = entry.pos.x.toDouble() - camPos.x
                val y = entry.pos.y.toDouble() - camPos.y
                val z = entry.pos.z.toDouble() - camPos.z
                f(buffer, stack, x.toFloat(), y.toFloat(), z.toFloat(), (x + 1.0).toFloat(), (y + 1.0).toFloat(), (z + 1.0).toFloat(),
                        (entry.color shr 16) and 0xFF, (entry.color shr 8) and 0xFF, entry.color and 0xFF, entry.alpha)
            }
        }

        // 2. Draw X-Ray wireframes
        if (mode == RenderMode.WIREFRAME) {
            val lineBuffer = bufferSource.getBuffer(observable.client.ObservableRenderTypes.getXRayLines())
            
            for (entry in entries) {
                val x = entry.pos.x.toDouble() - camPos.x
                val y = entry.pos.y.toDouble() - camPos.y
                val z = entry.pos.z.toDouble() - camPos.z
                
                // Inflate wireframe slightly to overlap properly
                val i = 0.02f
                val x0 = x.toFloat() - i; val y0 = y.toFloat() - i; val z0 = z.toFloat() - i
                val x1 = (x + 1.0).toFloat() + i; val y1 = (y + 1.0).toFloat() + i; val z1 = (z + 1.0).toFloat() + i

                val r = (entry.color shr 16) and 0xFF
                val g = (entry.color shr 8) and 0xFF
                val b = entry.color and 0xFF
                val a = 255
                
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
        }
        
        // Draw labels
        val font = Minecraft.getInstance().font
        for (label in labels) {
            val distSq = label.pos.distanceToSqr(camPos)
            if (distSq > ProfilerBridge.MAX_DISTANCE_SQ) continue

            val labelMatrix = Matrix4f()
            labelMatrix.translate(
                (label.pos.x - camPos.x).toFloat(), 
                (label.pos.y - camPos.y + 0.5).toFloat(), 
                (label.pos.z - camPos.z).toFloat()
            )
            labelMatrix.rotate(cameraState.orientation)
            labelMatrix.scale(0.025f, -0.025f, 0.025f)
            
            val text = label.text
            val width = font.width(text)
            
            font.drawInBatch(
                text,
                -width / 2.0f,
                0.0f,
                label.color or (0xFF shl 24),
                false,
                labelMatrix,
                bufferSource,
                Font.DisplayMode.SEE_THROUGH,
                0, 
                0xF000F0 
            )
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
        c.addVertex(m, x0, y0, z0).setColor(r, g, b, a).setNormal(0.0f, 1.0f, 0.0f).setLineWidth(4.5f)
        c.addVertex(m, x1, y1, z1).setColor(r, g, b, a).setNormal(0.0f, 1.0f, 0.0f).setLineWidth(4.5f)
    }

    private fun v(c: VertexConsumer, m: Matrix4fc, x: Float, y: Float, z: Float, r: Int, g: Int, b: Int, a: Int) {
        c.addVertex(m, x, y, z).setColor(r, g, b, a)
    }

    data class BlockEntry(val pos: BlockPos, val color: Int, val alpha: Int)
    data class LabelEntry(val pos: Vec3, val text: String, val color: Int)
}
