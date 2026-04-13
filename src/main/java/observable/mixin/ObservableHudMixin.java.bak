package observable.mixin;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import observable.client.ProfilerBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class ObservableHudMixin {
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void observable$onExtractRenderState(GuiGraphicsExtractor graphics, DeltaTracker delta, CallbackInfo ci) {
        ProfilerBridge.renderHud(graphics, delta);
    }
}
