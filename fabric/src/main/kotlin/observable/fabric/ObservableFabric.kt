package observable.fabric

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.resources.Identifier
import observable.Observable
import observable.fabric.net.FabricChannel
import observable.net.Channel
import observable.server.ObservableCommands
import observable.util.Platform
import java.nio.file.Path

class ObservableFabric : ModInitializer {
    override fun onInitialize() {
        Platform.INSTANCE = object : Platform {
            override fun createChannel(id: Identifier): Channel = FabricChannel(id)
            override fun getConfigDir(): Path = FabricLoader.getInstance().configDir
            override fun isDevelopment(): Boolean = FabricLoader.getInstance().isDevelopmentEnvironment
            
            override fun getModVersion(modId: String): String = 
                FabricLoader.getInstance().getModContainer(modId).map { it.metadata.version.friendlyString }.orElse("unknown")
            
            override fun getModList(): List<Pair<String, String>> = 
                FabricLoader.getInstance().allMods.map { it.metadata.name to it.metadata.version.friendlyString }
            
            override fun getLoaderName(): String = "FABRIC"
        }

        ServerLifecycleEvents.SERVER_STARTED.register { server ->
            Observable.SERVER_INSTANCE = server
        }
        ServerLifecycleEvents.SERVER_STOPPING.register {
            Observable.SERVER_INSTANCE = null
        }

        CommandRegistrationCallback.EVENT.register { dispatcher, _, _ ->
            ObservableCommands.register(dispatcher)
        }

        Observable.onInitialize()
    }
}
