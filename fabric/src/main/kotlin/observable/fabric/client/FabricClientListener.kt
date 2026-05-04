package observable.fabric.client

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents
import net.minecraft.client.Minecraft
import net.minecraft.network.chat.Component
import observable.client.ObservableClient
import observable.client.ProfilerBridge

object FabricClientListener {
    fun register() {
        ClientTickEvents.END_CLIENT_TICK.register { mc ->
            handleInput(mc)
            observable.client.KeyBindDeepLink.tick()
        }

        ClientPlayConnectionEvents.DISCONNECT.register { _, _ ->
            ProfilerBridge.clear()
        }

        ClientPlayConnectionEvents.JOIN.register { _, _, _ ->
            ObservableClient.showJoinMessage()
        }
    }

    private fun handleInput(mc: Minecraft) {
        if (ObservableClient.KEY_OPEN_SETTINGS.consumeClick()) {
            if (mc.player != null) {
                ProfilerBridge.openProfileScreen()
            }
        }

        while (ObservableClient.KEY_TOGGLE_OVERLAY.consumeClick()) {
            ObservableClient.isOverlayEnabled = !ObservableClient.isOverlayEnabled
            mc.player?.let { player ->
                player.sendSystemMessage(
                    Component.literal("§7[§6Observable§7] §fOverlay " + 
                        (if (ObservableClient.isOverlayEnabled) "§aEnabled" else "§cDisabled"))
                )
            }
        }
    }
}
