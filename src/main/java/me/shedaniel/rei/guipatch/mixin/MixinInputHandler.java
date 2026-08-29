package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import org.anti_ad.mc.common.gui.NativeInputContextBase;
import org.anti_ad.mc.ipnext.input.InputHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = InputHandler.class, remap = false)
public abstract class MixinInputHandler {

    @Inject(method = "onInput", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelOnInput(NativeInputContextBase<?> context, int key, int action, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(true);
        }
    }
}