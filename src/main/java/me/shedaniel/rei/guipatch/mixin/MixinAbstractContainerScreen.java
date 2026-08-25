package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.guipatch.REIHelper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class MixinAbstractContainerScreen {

    @Inject(method = "getHoveredSlot", at = @At("RETURN"), cancellable = true)
    private void reiGuiPatch$cancelGetHoveredSlot(double mouseX, double mouseY, CallbackInfoReturnable<Slot> cir) {
        if (REIHelper.isMenuOpen()) {
            cir.setReturnValue(null);
        }
    }

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
