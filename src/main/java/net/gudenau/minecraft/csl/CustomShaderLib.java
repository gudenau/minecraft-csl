package net.gudenau.minecraft.csl;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public final class CustomShaderLib implements ClientModInitializer {
    public static final String MOD_ID = "gud_csl";
    
    // These mods breaks things...
    public static final boolean SODIUM_PRESENT = FabricLoader.getInstance().isModLoaded("sodium");
    public static final boolean SATIN_PRESENT = FabricLoader.getInstance().isModLoaded("satin");
    
    @Override
    public void onInitializeClient() {}
}
