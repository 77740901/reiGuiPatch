package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.input.MouseButtonEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreativeModeInventoryScreen.class)
public abstract class MixinCreativeModeInventoryScreen {

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseClicked(MouseButtonEvent event, boolean flag, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "mouseReleased", at = @At("HEAD"), cancellable = true)
    private void reiGuiPatch$cancelMouseReleased(MouseButtonEvent event, CallbackInfoReturnable<Boolean> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(true);
        }
    }
}
