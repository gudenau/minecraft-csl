package net.gudenau.minecraft.csl.api.v0.duck;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.render.Shader;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Some small Shader addons.
 */
@Environment(EnvType.CLIENT)
public interface ShaderDuck {
    /**
     * Gets the {@link Identifier} for this shader. This isn't a vanilla thing, this is just a little nicer than just
     * working with raw strings.
     *
     * @return The {@link Identifier} for this shader
     */
    @NotNull Identifier getId();
    
    /**
     * Gets a {@link GlUniform} from this shader.
     *
     * This is effectively calling {@link Shader#getUniform(String)}, just without needing an Accessor.
     *
     * @param name The name of the uniform to get
     * @return The retrieved uniform, if present
     */
    @Nullable GlUniform getCustomUniform(String name);
}
