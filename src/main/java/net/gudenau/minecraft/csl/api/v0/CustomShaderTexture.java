package net.gudenau.minecraft.csl.api.v0;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * An interface for custom handling of shaders that use custom texture formats. Such as cube maps.
 */
@Environment(EnvType.CLIENT)
public interface CustomShaderTexture {
    /**
     * Bind the texture, this is called instead of the vanilla bind.
     *
     * @param texture The texture ID to bind
     */
    void bindTexture(int texture);
}
