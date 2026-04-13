package observable.fabric

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper
import observable.client.ObservableClient
import observable.fabric.client.FabricClientListener
import observable.fabric.client.FabricHudListener
import observable.fabric.client.FabricRenderingListener

class ObservableFabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        KeyMappingHelper.registerKeyMapping(ObservableClient.KEY_OPEN_SETTINGS)
        KeyMappingHelper.registerKeyMapping(ObservableClient.KEY_TOGGLE_OVERLAY)
        KeyMappingHelper.registerKeyMapping(ObservableClient.KEY_CYCLE_RENDER_MODE)
        
        ObservableClient.clientInit()

        FabricRenderingListener.register()
        FabricHudListener.register()
        FabricClientListener.register()
    }
}
