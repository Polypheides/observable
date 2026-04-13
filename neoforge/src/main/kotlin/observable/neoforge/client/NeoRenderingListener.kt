package observable.neoforge.client

import net.minecraft.client.Minecraft
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.SubmitCustomGeometryEvent
import observable.client.ProfilerBridge
import com.mojang.blaze3d.systems.RenderSystem
import org.joml.Matrix4f
import org.joml.Matrix4fStack

object NeoRenderingListener {
    @SubscribeEvent
    fun onSubmitGeometry(event: SubmitCustomGeometryEvent) {
        val mc = Minecraft.getInstance()
        val bridge = ProfilerBridge
        val renderState = event.levelRenderState
        val cameraState = renderState.cameraRenderState
        
        val stack = event.poseStack
        // In 26.1, PoseStack provides access to the current matrix via last().pose()
        // We pass the matrix (Matrix4fc) instead of the stack since we don't push/pop in the common renderer
        bridge.render(
            null, 
            RenderSystem.getModelViewMatrix(),
            cameraState.pos,
            stack.last().pose(), 
            mc.deltaTracker,
            event.submitNodeCollector,
            cameraState
        )
    }
}
