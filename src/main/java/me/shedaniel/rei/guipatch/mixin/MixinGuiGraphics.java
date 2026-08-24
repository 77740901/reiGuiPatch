package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.api.client.gui.compat.GuiGraphics;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiGraphics.class)
public abstract class MixinGuiGraphics {

    @Redirect(
        method = "withFreshScissorStack",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/navigation/ScreenRectangle;empty()Lnet/minecraft/client/gui/navigation/ScreenRectangle;"
        )
    )
    private ScreenRectangle reiGuiPatch$fixEmptyScissor(Runnable runnable) {
        return new ScreenRectangle(0, 0, Short.MAX_VALUE, Short.MAX_VALUE);
    }
}
