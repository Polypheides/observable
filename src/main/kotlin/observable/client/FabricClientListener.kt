package observable.client

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import observable.Observable

object FabricClientListener {
    fun register() {
        ClientTickEvents.END_CLIENT_TICK.register { mc ->
            handleInput(mc)
        }

        ClientPlayConnectionEvents.DISCONNECT.register { _, _ ->
            ProfilerBridge.clear()
        }
    }

    private fun handleInput(mc: Minecraft) {
        if (Observable.KEY_OPEN_SETTINGS.consumeClick()) {
            if (mc.player != null) {
                ProfilerBridge.openProfileScreen()
            }
        }

        while (Observable.KEY_TOGGLE_OVERLAY.consumeClick()) {
            Observable.isOverlayEnabled = !Observable.isOverlayEnabled
            mc.player?.let { player ->
                player.sendSystemMessage(
                    Component.literal("§7[§6Observable§7] §fOverlay " + 
                        (if (Observable.isOverlayEnabled) "§aEnabled" else "§cDisabled"))
                )
            }
        }
    }
}
