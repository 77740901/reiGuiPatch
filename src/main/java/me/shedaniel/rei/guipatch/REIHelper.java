package me.shedaniel.rei.guipatch;

import me.shedaniel.rei.api.client.REIRuntime;
import me.shedaniel.rei.impl.client.gui.ScreenOverlayImpl;

public final class REIHelper {
    private REIHelper() {}

    public static boolean isMenuOpen() {
        return REIRuntime.getInstance().isOverlayVisible()
                && ScreenOverlayImpl.getInstance().menuAccess().isAnyOpened();
    }
}
