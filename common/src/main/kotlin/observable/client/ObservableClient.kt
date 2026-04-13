package observable.client

import net.minecraft.client.KeyMapping
import net.minecraft.client.Minecraft
import observable.Observable
import observable.net.*

object ObservableClient {
    val PROFILE_SCREEN by lazy { ProfileScreen() }
    
    val KEY_OPEN_SETTINGS by lazy { KeyMapping("key.observable.settings", 85, ProfilerBridge.CATEGORY) }
    val KEY_TOGGLE_OVERLAY by lazy { KeyMapping("key.observable.overlay", 82, ProfilerBridge.CATEGORY) }
    val KEY_CYCLE_RENDER_MODE by lazy { KeyMapping("key.observable.cycle_render_mode", 91, ProfilerBridge.CATEGORY) }

    var isOverlayEnabled: Boolean
        get() = ClientConfig.data.isOverlayEnabled
        set(v) {
            ClientConfig.data.isOverlayEnabled = v
            ClientConfig.save()
        }

    fun clientInit() {
        Observable.LOGGER.info("Starting Observable Client-side initialization...")
        ClientConfig.load()
        Overlay.init()
        ProfilerBridge.setBridge(Overlay)
        ProfilerBridge.setWorldRenderer(Overlay)
        ProfilerBridge.setHudRenderer(Overlay::renderHud)
        
        ProfilerBridge.setScreenOpener {
            Minecraft.getInstance().setScreen(PROFILE_SCREEN)
        }

        Observable.CHANNEL.register { t: S2CPacket.ProfilingStarted, _ ->
            PROFILE_SCREEN.action = ProfileScreen.Action.TPSProfilerRunning(t.endMillis)
        }

        Observable.CHANNEL.register { _: S2CPacket.ProfilingCompleted, _ ->
            PROFILE_SCREEN.action = ProfileScreen.Action.TPSProfilerCompleted
        }

        Observable.CHANNEL.register { t: S2CPacket.ProfilingResult, _ ->
            Observable.RESULTS = t.data
            PROFILE_SCREEN.action = ProfileScreen.Action.NewProfile(ClientConfig.data.profileDuration)
            Overlay.loadSync()

            val mc = Minecraft.getInstance()
            
            if (t.link != null) {
                // 1. Post online link
                val linkComp = net.minecraft.network.chat.Component.literal(t.link)
                    .withStyle { it.withColor(net.minecraft.ChatFormatting.AQUA).withUnderlined(true).withClickEvent(observable.util.ClickEventUtils.createOpenUrl(t.link)) }
                
                val msg = net.minecraft.network.chat.Component.translatable("text.observable.profile_uploaded", linkComp)
                mc.player?.sendSystemMessage(msg)
            } else {
                // 2. Fallback: export locally and post local link
                val localLink = ProfileExporter.export(t.data)
                val msg = net.minecraft.network.chat.Component.translatable("text.observable.profile_saved", localLink)
                mc.player?.sendSystemMessage(msg)
            }
        }

        Observable.CHANNEL.register { t: S2CPacket.Availability, _ ->
            PROFILE_SCREEN.action = when (t) {
                S2CPacket.Availability.Available -> ProfileScreen.Action.NewProfile(ClientConfig.data.profileDuration)
                S2CPacket.Availability.NoPermissions -> ProfileScreen.Action.NO_PERMISSIONS
            }
        }

        Observable.LOGGER.info("Observable Client-side initialization complete.")
    }
}
