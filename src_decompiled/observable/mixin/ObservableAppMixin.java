/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.player.LocalPlayer
 *  net.minecraft.network.chat.Component
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package observable.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import observable.client.ProfilerBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Minecraft.class})
public class ObservableAppMixin {
    @Inject(method={"tick"}, at={@At(value="HEAD")})
    private void observable$onTick(CallbackInfo ci) {
        if (ProfilerBridge.isSettingsKeyClicked() && Minecraft.getInstance().player != null) {
            ProfilerBridge.openProfileScreen();
        }
        while (ProfilerBridge.isOverlayKeyClicked()) {
            boolean bl = ProfilerBridge.isOverlayEnabled = !ProfilerBridge.isOverlayEnabled;
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) continue;
            player.sendSystemMessage((Component)Component.literal((String)("\u00a77[\u00a76Observable\u00a77] \u00a7fOverlay " + (ProfilerBridge.isOverlayEnabled ? "\u00a7aEnabled" : "\u00a7cDisabled"))));
        }
    }

    @Inject(method={"clearClientLevel"}, at={@At(value="HEAD")})
    private void observable$onClearLevel(CallbackInfo ci) {
        ProfilerBridge.clear();
    }
}

