package me.shedaniel.rei.guipatch.mixin;

import me.shedaniel.math.FloatingRectangle;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.compat.GuiGraphics;
import me.shedaniel.rei.api.client.favorites.FavoriteMenuEntry;
import me.shedaniel.rei.impl.client.gui.modules.Menu;
import me.shedaniel.rei.impl.client.gui.modules.entries.SubMenuEntry;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(value = SubMenuEntry.class, remap = false)
public abstract class MixinSubMenuEntry {

    @Shadow protected List<FavoriteMenuEntry> entries;
    @Shadow protected Menu parent;
    @Shadow protected Menu childMenu;
    @Shadow public abstract Menu getChildMenu();
    @Shadow protected void renderBackground(GuiGraphics graphics, int x, int y, int width, int height) {}

    @Overwrite
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        SubMenuEntry self = (SubMenuEntry) (Object) this;
        renderBackground(graphics, self.getX(), self.getY(), self.getWidth(), self.getEntryHeight());
        if (self.isSelected()) {
            if (!entries.isEmpty()) {
                Menu menu = getChildMenu();

                Rectangle menuStart = new Rectangle(parent.getBounds().x, self.getY(), parent.getBounds().width, self.getEntryHeight());

                int fullWidth = Minecraft.getInstance().gui.screen().width;
                int fullHeight = Minecraft.getInstance().gui.screen().height;
                boolean facingRight = parent.facingRight;
                int menuWidth = menu.getMaxEntryWidth() + 2 + (menu.hasScrollBar() ? 6 : 0);

                double guiScale = Minecraft.getInstance().getWindow().getGuiScale();
                if (guiScale >= 3.7) {
                    facingRight = false;
                    int testNewX = menuStart.x - (menu.getMaxEntryWidth() + 2 + (menu.scrolling.getMaxScrollHeight() > menu.getInnerHeight(menu.menuStartPoint.y) ? 6 : 0));
                    if (testNewX < 0) {
                        facingRight = true;
                    }
                } else {
                    if (facingRight && fullWidth - menuStart.getMaxX() < menuWidth + 10) {
                        facingRight = false;
                    } else if (!facingRight && menuStart.x < menuWidth + 10) {
                        facingRight = true;
                    }
                }

                boolean facingDownwards = fullHeight - menuStart.getMaxY() > menuStart.y;

                int newY = facingDownwards ? menuStart.y - 1 : menuStart.getMaxY() - (menu.scrolling.getMaxScrollHeight() + 1);
                int newX = facingRight ? menuStart.getMaxX() : menuStart.x - (menu.getMaxEntryWidth() + 2 + (menu.scrolling.getMaxScrollHeight() > menu.getInnerHeight(menu.menuStartPoint.y) ? 6 : 0));

                int maxMenuHeight = menu.scrolling.getMaxScrollHeight();
                if (newX + menuWidth > fullWidth) {
                    newX = fullWidth - menuWidth;
                }
                if (newX < 0) {
                    newX = 0;
                }
                if (newY + maxMenuHeight > fullHeight) {
                    newY = fullHeight - maxMenuHeight;
                }
                if (newY < 0) {
                    newY = 0;
                }

                if (!menu.menuStartPoint.equals(new Point(newX, newY))) {
                    menu.menuStartPoint.setLocation(newX, newY);
                    Rectangle createBounds = menu.createBounds();
                    menu.bounds.setAs(new FloatingRectangle(facingRight ? createBounds.x : createBounds.getMaxX(), facingDownwards ? createBounds.y : createBounds.getMaxY(), 0.1, 0.1));
                }

                graphics.withFreshScissorStack(() -> menu.render(graphics, mouseX, mouseY, delta));
            }
        } else {
            this.childMenu = null;
        }
        graphics.drawString(Minecraft.getInstance().font, self.text, self.getX() + 2, self.getY() + 2, self.isSelected() ? 0xFFFFFFFF : 0xFF888888, false);
    }

    @Overwrite
    public boolean containsMouse(double mouseX, double mouseY) {
        SubMenuEntry self = (SubMenuEntry) (Object) this;
        Rectangle selfBounds = new Rectangle(self.getX(), self.getY(), self.getWidth(), self.getEntryHeight());
        if (selfBounds.contains(mouseX, mouseY))
            return true;
        if (childMenu != null && !childMenu.children().isEmpty())
            return childMenu.containsMouse(mouseX, mouseY);
        return false;
    }
}
