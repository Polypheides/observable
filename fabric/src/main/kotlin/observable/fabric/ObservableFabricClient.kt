package observable.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import observable.Observable
import observable.fabric.client.FabricClientListener
import observable.fabric.client.FabricHudListener
import observable.fabric.client.FabricRenderingListener

class ObservableFabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeyMappingHelper.registerKeyMapping(Observable.KEY_OPEN_SETTINGS)
        KeyMappingHelper.registerKeyMapping(Observable.KEY_TOGGLE_OVERLAY)
        
        Observable.clientInit()

        FabricRenderingListener.register()
        FabricHudListener.register()
        FabricClientListener.register()
    }
}
