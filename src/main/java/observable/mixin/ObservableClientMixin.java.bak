package observable.mixin;

import net.minecraft.client.Minecraft;
import observable.Observable;
import observable.client.Overlay;
import observable.client.ProfilerBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class ObservableClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void observable$onTick(CallbackInfo ci) {
        if (observable.Observable.KEY_OPEN_SETTINGS.consumeClick()) {
            if (net.minecraft.client.Minecraft.getInstance().player != null) {
                observable.client.ProfilerBridge.openProfileScreen();
            }
        }

        while (observable.Observable.KEY_TOGGLE_OVERLAY.consumeClick()) {
            observable.Observable.INSTANCE.setOverlayEnabled(!observable.Observable.INSTANCE.isOverlayEnabled());
            var player = net.minecraft.client.Minecraft.getInstance().player;
            if (player != null) {
                player.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal("§7[§6Observable§7] §fOverlay "
                                + (observable.Observable.INSTANCE.isOverlayEnabled() ? "§aEnabled" : "§cDisabled")));
            }
        }
    }

    @Inject(method = "clearClientLevel", at = @At("HEAD"))
    private void observable$onClearLevel(CallbackInfo ci) {
        observable.client.ProfilerBridge.clear();
    }
}
