package observable.fabric

import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.Minecraft
import observable.Observable
import observable.client.Overlay
import observable.client.ProfileScreen

class Client : ClientModInitializer {
    override fun onInitializeClient() {
        println("Observable: ClientModInitializer.onInitializeClient() called")
        Observable.clientInit()

        // Register Fabric API listeners
        observable.client.FabricRenderingListener.register()
        observable.client.FabricHudListener.register()
        observable.client.FabricClientListener.register()
    }
}
