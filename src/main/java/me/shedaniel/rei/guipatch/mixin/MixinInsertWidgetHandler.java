package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import org.anti_ad.mc.common.gui.NativeInputContextBase;
import org.anti_ad.mc.ipnext.gui.inject.InsertWidgetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = InsertWidgetHandler.class, remap = false)
public abstract class MixinInsertWidgetHandler {

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelIPNMouseClicked(NativeInputContextBase<?> context, double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "mouseRelease", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelIPNMouseRelease(NativeInputContextBase<?> context, double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "mouseDragged", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelIPNMouseDragged(NativeInputContextBase<?> context, double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "mouseScrolled", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelIPNMouseScrolled(NativeInputContextBase<?> context, double mouseX, double mouseY, double scrollX, double scrollY, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }
}
