package observable.client

import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.level.LevelRenderEvents
import net.minecraft.client.Minecraft
import org.joml.Matrix4fStack

object FabricRenderingListener {
    fun register() {
        LevelRenderEvents.COLLECT_SUBMITS.register { context ->
            render(context)
        }
    }

    private fun render(context: LevelRenderContext) {
        if (ProfilerBridge.getBridge() == null) return

        val cameraState = context.levelState().cameraRenderState
        
        // Use Identity stack as 26.1 handles rotation in shaders via modelViewMatrix
        val capturedStack = Matrix4fStack(16)
        capturedStack.identity()
        
        // Fabric API's COLLECT_SUBMITS is called at a point where the modelViewMatrix 
        // from the LevelRenderer.renderLevel is not directly exposed in the context.
        // However, we can use the identity stack in Camera Space.
        
        val mc = Minecraft.getInstance()
        val deltaTracker = mc.deltaTracker
        val collector = context.submitNodeCollector()
        
        // Note: We use identity as modelView because we are in Camera Space
        // and the shader will apply the correct authoritative modelView matrix 
        // to our submitted vertices.
        val modelViewIdentity = capturedStack // This is identity

        ProfilerBridge.render(
            cameraState.projectionMatrix,
            modelViewIdentity,
            cameraState.pos,
            capturedStack,
            deltaTracker,
            collector,
            cameraState
        )
    }
}
