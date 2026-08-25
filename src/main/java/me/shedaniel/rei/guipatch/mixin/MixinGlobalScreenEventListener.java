package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import org.anti_ad.mc.common.gui.NativeInputContextBase;
import org.anti_ad.mc.common.input.GlobalScreenEventListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = GlobalScreenEventListener.class, remap = false)
public abstract class MixinGlobalScreenEventListener {

    @Inject(method = "onMouseClicked", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseClicked(NativeInputContextBase<?> context, double mouseX, double mouseY, int button, boolean pre, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onMouseReleased", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseReleased(NativeInputContextBase<?> context, double mouseX, double mouseY, int button, boolean pre, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onMouseDragged", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseDragged(NativeInputContextBase<?> context, double mouseX, double mouseY, int button, double deltaX, double deltaY, boolean pre, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onMouseScrolled", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseScrolled(NativeInputContextBase<?> context, double mouseX, double mouseY, double scrollX, double scrollY, boolean pre, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "onMouseCursorPos", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseCursorPos(NativeInputContextBase<?> context, double mouseX, double mouseY, int cursorShape, double deltaX, double deltaY, boolean pre, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(false);
        }
    }
}
