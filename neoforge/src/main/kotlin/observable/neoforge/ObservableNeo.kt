package observable.neoforge

import net.minecraft.resources.Identifier
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent
import net.neoforged.neoforge.common.NeoForge
import observable.client.ObservableClient
import observable.client.ProfilerBridge
import net.neoforged.neoforge.event.RegisterCommandsEvent
import net.neoforged.neoforge.event.server.ServerStartedEvent
import net.neoforged.neoforge.event.server.ServerStoppingEvent
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import observable.Observable
import observable.net.Channel
import observable.neoforge.client.NeoHudListener
import observable.neoforge.client.NeoRenderingListener
import observable.neoforge.net.NeoChannel
import observable.server.ObservableCommands
import observable.util.Platform

@Mod(Observable.MOD_ID)
class ObservableNeo(bus: IEventBus) {
    companion object {
        lateinit var BUS: IEventBus
    }

    init {
        BUS = bus
        Platform.INSTANCE = object : Platform {
            override fun createChannel(id: Identifier): Channel = NeoChannel(id)
            override fun getConfigDir() = net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get()
            override fun isDevelopment() = !net.neoforged.fml.loading.FMLEnvironment.isProduction()
            
            override fun getModVersion(modId: String): String = 
                ModList.get().getModContainerById(modId).map { it.modInfo.version.toString() }.orElse("unknown")
            
            override fun getModList(): List<Pair<String, String>> = 
                ModList.get().mods.map { it.displayName to it.version.toString() }
            
            override fun getLoaderName(): String = "NEOFORGE"
        }

        bus.addListener(::onCommonSetup)
        bus.addListener(::onClientSetup)
        bus.addListener(::onRegisterKeyMappings)

        NeoForge.EVENT_BUS.addListener(::onServerStarted)
        NeoForge.EVENT_BUS.addListener(::onServerStopping)
        NeoForge.EVENT_BUS.addListener(::onRegisterCommands)
        NeoForge.EVENT_BUS.addListener(::onLoggingOut)
        bus.addListener(::onRegisterPayloads)

        // Force channel creation to register the network listener
        Observable.CHANNEL
    }

    private fun onCommonSetup(event: FMLCommonSetupEvent) {
        Observable.onInitialize()
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        ObservableClient.clientInit()
        
        NeoForge.EVENT_BUS.register(NeoRenderingListener)
        NeoForge.EVENT_BUS.register(NeoHudListener)
    }

    private fun onServerStarted(event: ServerStartedEvent) {
        Observable.SERVER_INSTANCE = event.server
    }

    private fun onServerStopping(event: ServerStoppingEvent) {
        Observable.SERVER_INSTANCE = null
    }

    private fun onRegisterKeyMappings(event: RegisterKeyMappingsEvent) {
        event.register(ObservableClient.KEY_OPEN_SETTINGS)
        event.register(ObservableClient.KEY_TOGGLE_OVERLAY)
        event.register(ObservableClient.KEY_CYCLE_RENDER_MODE)
    }

    private fun onRegisterCommands(event: RegisterCommandsEvent) {
        ObservableCommands.register(event.dispatcher)
    }

    private fun onRegisterPayloads(event: RegisterPayloadHandlersEvent) {
        (Observable.CHANNEL as? NeoChannel)?.onRegisterPayloads(event)
    }

    private fun onLoggingOut(event: ClientPlayerNetworkEvent.LoggingOut) {
        ProfilerBridge.clear()
    }
}
