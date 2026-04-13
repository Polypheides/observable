package observable.client

import net.minecraft.client.KeyMapping

interface ClientBridge {
    fun clear()

    val settingsKey: KeyMapping

    val overlayKey: KeyMapping
}
