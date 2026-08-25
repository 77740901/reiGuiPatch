package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import org.anti_ad.mc.common.gui.NativeContext;
import org.anti_ad.mc.ipnext.gui.inject.ContainerScreenEventHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ContainerScreenEventHandler.class, remap = false)
public abstract class MixinContainerScreenEventHandler {

    @Inject(method = "onForegroundRender", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelForegroundRender(NativeContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (REIHelper.isMenuOpen()) {
            ci.cancel();
        }
    }

    @Inject(method = "postRender", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelPostRender(NativeContext context, CallbackInfo ci) {
        if (REIHelper.isMenuOpen()) {
            ci.cancel();
        }
    }
}
