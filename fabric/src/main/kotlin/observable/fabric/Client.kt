package observable.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import observable.Observable
import observable.client.FabricClientListener
import observable.client.FabricHudListener
import observable.client.FabricRenderingListener

class Client : ClientModInitializer {
    override fun onInitializeClient() {
        KeyMappingHelper.registerKeyMapping(Observable.KEY_OPEN_SETTINGS)
        KeyMappingHelper.registerKeyMapping(Observable.KEY_TOGGLE_OVERLAY)
        
        Observable.clientInit()

        FabricRenderingListener.register()
        FabricHudListener.register()
        FabricClientListener.register()
    }
}
