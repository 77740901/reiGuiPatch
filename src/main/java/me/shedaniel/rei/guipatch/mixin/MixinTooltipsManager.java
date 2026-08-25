package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import org.anti_ad.mc.common.gui.NativeContext;
import org.anti_ad.mc.common.gui.TooltipsManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = TooltipsManager.class, remap = false)
public abstract class MixinTooltipsManager {

    @Inject(method = "renderAll", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelIPNTooltipRender(NativeContext context, CallbackInfo ci) {
        if (REIHelper.isMenuOpen()) {
            TooltipsManager.INSTANCE.getTooltips().clear();
            ci.cancel();
        }
    }
}
