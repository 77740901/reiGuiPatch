package me.shedaniel.rei.guipatch;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class REIGuiPatchMod implements ClientModInitializer {
    public static final String MOD_ID = "rei-gui-patch";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("[REI GUI Patch] Loaded - fixing REI GUI rendering issues for MC 26.2");
    }
}
