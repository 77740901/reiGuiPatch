package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import me.shedaniel.rei.impl.client.gui.modules.entries.ToggleMenuEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ToggleMenuEntry.class, remap = false)
public abstract class MixinToggleMenuEntry {

    @Redirect(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lme/shedaniel/rei/impl/client/gui/ScreenOverlayImpl;renderTooltip(Lme/shedaniel/rei/api/client/gui/compat/GuiGraphics;Lme/shedaniel/rei/api/client/gui/widgets/Tooltip;)V"
        )
    )
    private void reiGuiPatch$queueTooltip(ScreenOverlayImpl instance, me.shedaniel.rei.api.client.gui.compat.GuiGraphics graphics, Tooltip tooltip) {
        instance.addTooltip(tooltip);
    }
}
