package observable.neoforge.client

import net.minecraft.client.Minecraft
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RenderLevelStageEvent
import observable.client.ProfilerBridge
import com.mojang.blaze3d.systems.RenderSystem
import org.joml.Matrix4f
import org.joml.Matrix4fStack

object NeoRenderingListener {
    @SubscribeEvent
    fun onRenderLevelStage(event: RenderLevelStageEvent.AfterLevel) {
        val mc = Minecraft.getInstance()
        val bridge = ProfilerBridge
        val renderState = event.levelRenderState
        val cameraState = renderState.cameraRenderState
        // In AfterLevel, we use the AUTHORITATIVE modelViewMatrix from the event.
        // Because our common code (ProfilerBridge) subtracts cameraPos from every vertex,
        // we pass a ZERO camera position here so the common code does (pos - 0) = pos.
        // Then, the authoritative modelViewMatrix handles the world -> camera translation correctly.
        val zeroPos = net.minecraft.world.phys.Vec3(0.0, 0.0, 0.0)
        
        bridge.render(
            null, 
            event.modelViewMatrix,
            zeroPos,
            event.modelViewMatrix, 
            mc.deltaTracker,
            null, 
            cameraState,
            true // FLUSH labels over water and particles
        )
    }
}
