package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.widgets.Button;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;
import me.shedaniel.rei.impl.client.gui.widget.CraftableFilterButtonWidget;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CraftableFilterButtonWidget.class, remap = false)
public abstract class MixinCraftableFilterButtonWidget {

    @Inject(method = "create", at = @At("RETURN"), cancellable = true)
    private static void reiGuiPatch$addCraftingTableIcon(ScreenOverlayImpl overlay, CallbackInfoReturnable<Widget> cir) {
        Widget original = cir.getReturnValue();
        if (original instanceof Button button) {
            Rectangle bounds = button.getBounds();
            Widget overlayWidget = Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
                graphics.renderItem(new ItemStack(Items.CRAFTING_TABLE), bounds.x + 2, bounds.y + 2);
            });
            cir.setReturnValue(Widgets.concat(original, overlayWidget));
        }
    }
}
