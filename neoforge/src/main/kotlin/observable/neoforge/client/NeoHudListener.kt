package observable.neoforge.client

import net.minecraft.client.gui.GuiGraphicsExtractor
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.RenderGuiEvent
import observable.client.ProfilerBridge

object NeoHudListener {
    @SubscribeEvent
    fun onRenderGui(event: RenderGuiEvent.Post) {
        // In 26.1, GuiGraphics was renamed or replaced by GuiGraphicsExtractor in many contexts
        // RenderGuiEvent provides a GuiGraphicsExtractor
        ProfilerBridge.renderHud(event.guiGraphics, event.partialTick)
    }
}
