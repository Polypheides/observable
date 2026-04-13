package observable.fabric.client

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry
import net.minecraft.client.DeltaTracker
import net.minecraft.client.gui.GuiGraphicsExtractor
import net.minecraft.resources.Identifier
import observable.client.ProfilerBridge

object FabricHudListener {
    private val ID = Identifier.fromNamespaceAndPath("observable", "overlay")

    fun register() {
        // Register after boss bar to ensure it's not covered by vanilla elements
        HudElementRegistry.addLast(ID, object : HudElement {
            override fun extractRenderState(graphics: GuiGraphicsExtractor, delta: DeltaTracker) {
                // Check if profiler is active
                if (ProfilerBridge.getBridge() != null) {
                    ProfilerBridge.renderHud(graphics, delta)
                }
            }
        })
    }
}
