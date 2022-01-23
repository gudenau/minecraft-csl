package net.gudenau.minecraft.csl.api.v0;

import net.gudenau.minecraft.csl.impl.RenderLayerBuilderImpl;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * A builder for {@link RenderLayer}s. Allows for easier creation of multiple {@link RenderLayer}s without using
 * accessors.
 *
 * The following attributes have to be set:
 *  - vertexFormat
 *  - drawMode
 *  - expectedBufferSize
 *  - hasCrumbling
 *  - translucent
 *  - outlineMode
 */
public interface RenderLayerBuilder {
    /**
     * Creates a new builder.
     *
     * @return The new builder
     */
    static @NotNull RenderLayerBuilder createBuilder(){
        return new RenderLayerBuilderImpl();
    }
    
    /**
     * Set the vertex format for this builder.
     *
     * @param vertexFormat The new vertex format
     * @return The current builder
     */
    @NotNull RenderLayerBuilder vertexFormat(@NotNull VertexFormat vertexFormat);
    
    /**
     * Set the current draw mode for this builder
     *
     * @param drawMode The new draw mode
     * @return The current builder
     */
    @NotNull RenderLayerBuilder drawMode(@NotNull VertexFormat.DrawMode drawMode);
    
    /**
     * Set the current expected buffer size for this builder.
     *
     * @param expectedBufferSize The new expected buffer size
     * @return The current builder
     */
    @NotNull RenderLayerBuilder expectedBufferSize(int expectedBufferSize);
    
    /**
     * Sets the crumbling flag for this builder. This is used for block breaking progress.
     *
     * @param hasCrumbling Does this layer have crumbling
     * @return The current builder
     */
    @NotNull RenderLayerBuilder hasCrumbling(boolean hasCrumbling);
    
    /**
     * Sets the translucent flag for this builder.
     *
     * @param isTranslucent Does this layer have translucency
     * @return The current builder
     */
    @NotNull RenderLayerBuilder translucent(boolean isTranslucent);
    
    /**
     * Sets the current outline mode for this builder. This has to do with the spectral arrows.
     *
     * @param outlineMode The new outline mode
     * @return The current builder
     */
    @NotNull RenderLayerBuilder outlineMode(@NotNull OutlineMode outlineMode);
    
    /**
     * Sets the current texture for this builder. This uses a type that is not normally accessible, requires an access
     * widener to use.
     *
     * @param texture The new texture
     * @return The current builder
     */
    @NotNull RenderLayerBuilder texture(@NotNull RenderPhase.TextureBase texture);
    
    /**
     * Sets the current texture for this builder.
     *
     * @param texture The identifier for the texture
     * @param blur Should this texture use bi-linear filtering
     * @param mipmap Should this texture use mipmaps
     * @return The current builder
     */
    @NotNull RenderLayerBuilder texture(@Nullable Identifier texture, boolean blur, boolean mipmap);
    
    /**
     * Sets the shader for this builder to a vanilla shader.
     *
     * @param shader A vanilla shader to use
     * @return The current builder
     */
    @NotNull RenderLayerBuilder shader(@NotNull VanillaShader shader);
    
    /**
     * Sets the shader for this builder.
     *
     * It is important that the passed {@link Supplier} always uses the most recent shader instance.
     *
     * @param shader The shader to use
     * @return The current builder
     */
    @NotNull RenderLayerBuilder shader(@Nullable Supplier<Shader> shader);
    
    /**
     * Sets the transparency mode for this builder.
     *
     * @param transparency The transparency mode
     * @return The current builder
     */
    @NotNull RenderLayerBuilder transparency(@NotNull Transparency transparency);
    
    /**
     * Sets the depth test mode for this builder.
     *
     * @param depthTest The depth test mode for this builder
     * @return The current builder
     */
    @NotNull RenderLayerBuilder depthTest(@NotNull DepthTest depthTest);
    
    /**
     * Enables or disables culling for this builder.
     *
     * @param enable Should polygons be culled
     * @return The current builder
     */
    @NotNull RenderLayerBuilder culling(boolean enable);
    
    /**
     * Enables or disables lightmaps for this builder. This has to do with the lighting engine.
     *
     * @param enable Should lightmaps be used
     * @return The current builder
     */
    @NotNull RenderLayerBuilder lightmap(boolean enable);
    
    /**
     * Enables or disables overlay colors for this builder.
     *
     * @param enable Should overlay colors be used
     * @return The current builder
     */
    @NotNull RenderLayerBuilder overlayColor(boolean enable);
    
    /**
     * Sets the layering mode for this builder.
     *
     * @param layering The layering mode to use
     * @return The current builder
     */
    @NotNull RenderLayerBuilder layering(@NotNull Layering layering);
    
    /**
     * Sets render target for this builder.
     *
     * @param target The target to use
     * @return The current builder
     */
    @NotNull RenderLayerBuilder target(@NotNull Target target);
    
    /**
     * Sets the texturing mode for this builder.
     *
     * @param texturing The texutring mode to use
     * @return The current builder
     */
    @NotNull RenderLayerBuilder texturing(@NotNull Texturing texturing);
    
    /**
     * Sets the write mask for this builder.
     *
     * @param writeMask The write mask to use
     * @return The current builder
     */
    @NotNull RenderLayerBuilder writeMask(@NotNull WriteMask writeMask);
    
    /**
     * Sets the line width of this builder.
     *
     * @param enabled Should lines be used
     * @param width The width of lines to render
     * @return The current builder
     */
    @NotNull RenderLayerBuilder lineWidth(boolean enabled, double width);
    
    /**
     * Creates a new {@link RenderLayer} from this builder.
     *
     * @param identifier The name of the new {@link RenderLayer}
     * @return The new {@link RenderLayer}
     */
    @NotNull RenderLayer build(@NotNull Identifier identifier);
    
    /**
     * The outline modes that vanilla uses.
     */
    enum OutlineMode{
        NONE,
        IS_OUTLINE,
        AFFECTS_OUTLINE,
    }
    
    /**
     * The vanilla shaders.
     */
    enum VanillaShader {
        BLOCK,
        NEW_ENTITY,
        POSITION_COLOR_LIGHTMAP,
        POSITION,
        POSITION_COLOR_TEXTURE,
        POSITION_TEXTURE,
        POSITION_COLOR_TEXTURE_LIGHTMAP,
        COLOR,
        SOLID,
        CUTOUT_MIPPED,
        CUTOUT,
        TRANSLUCENT,
        TRANSLUCENT_MOVING_BLOCK,
        TRANSLUCENT_NO_CRUMBLING,
        ARMOR_CUTOUT_NO_CULL,
        ENTITY_SOLID,
        ENTITY_CUTOUT,
        ENTITY_CUTOUT_NONULL,
        ENTITY_CUTOUT_NONULL_OFFSET_Z,
        ITEM_ENTITY_TRANSLUCENT_CULL,
        ENTITY_TRANSLUCENT_CULL,
        ENTITY_TRANSLUCENT,
        ENTITY_SMOOTH_CUTOUT,
        BEACON_BEAM,
        ENTITY_DECAL,
        ENTITY_NO_OUTLINE,
        ENTITY_SHADOW,
        ENTITY_ALPHA,
        EYES,
        ENERGY_SWIRL,
        LEASH,
        WATER_MASK,
        OUTLINE,
        ARMOR_GLINT,
        ARMOR_ENTITY_GLINT,
        TRANSLUCENT_GLINT,
        GLINT,
        DIRECT_GLINT,
        ENTITY_GLINT,
        DIRECT_ENTITY_GLINT,
        CRUMBLING,
        TEXT,
        TEXT_INTENSITY,
        TRANSPARENT_TEXT,
        TRANSPARENT_TEXT_INTENSITY,
        LIGHTNING,
        TRIPWIRE,
        END_PORTAL,
        END_GATEWAY,
        LINES,
    }
    
    /**
     * Transparency modes that vanilla uses.
     */
    enum Transparency {
        NONE,
        ADDITIVE,
        LIGHTNING,
        GLINT,
        CRUMBLING,
        TRANSLUCENT,
    }
    
    /**
     * Depth test modes that vanilla uses.
     */
    enum DepthTest {
        ALWAYS,
        EQUAL,
        LESS_OR_EQUAL,
    }
    
    /**
     * Layering modes that vanilla uses.
     */
    enum Layering {
        NONE,
        POLYGON_OFFSET,
        VIEW_OFFSET_Z,
    }
    
    /**
     * Rendering targets that vanilla uses.
     */
    enum Target {
        MAIN,
        OUTLINE,
        TRANSLUCENT,
        PARTICLES,
        WEATHER,
        CLOUDS,
        ITEM,
    }
    
    /**
     * The texturing modes that vanilla uses.
     */
    enum Texturing {
        DEFAULT,
        GLINT,
        ENTITY_GLINT,
    }
    
    /**
     * The write masks that vanilla uses.
     */
    enum WriteMask {
        ALL,
        COLOR,
        DEPTH,
    }
}
