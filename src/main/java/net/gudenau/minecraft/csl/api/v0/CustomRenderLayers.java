package net.gudenau.minecraft.csl.api.v0;

import net.gudenau.minecraft.csl.impl.CustomRenderLayersImpl;
import net.minecraft.client.render.RenderLayer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * The place to register custom render layers.
 */
public interface CustomRenderLayers {
    /**
     * Gets the singleton instance of this interface.
     *
     * @return The singleton of this interface
     */
    static @NotNull CustomRenderLayers getInstance(){
        return CustomRenderLayersImpl.INSTANCE;
    }
    
    /**
     * Registers a render layer that is used for blocks. These render layers are rendered when vanilla block render
     * layers are rendered.
     *
     * @param layer The block layer to render
     */
    void registerBlockRenderLayer(@NotNull RenderLayer layer);
    
    /**
     * Registers multiple render layers that are used for blocks. These render layers are rendered when vanilla block
     * render layers are rendered.
     *
     * @param layers The layers to register
     */
    default void registerBlockRenderLayers(@NotNull RenderLayer @NotNull... layers){
        Objects.requireNonNull(layers, "layers can't be null");
        for (RenderLayer layer : layers) {
            registerBlockRenderLayer(layer);
        }
    }
    
    /**
     * Registers multiple render layers that are used for blocks. These render layers are rendered when vanilla block
     * render layers are rendered.
     *
     * @param layers The layers to register
     */
    default void registerBlockRenderLayers(@NotNull Collection<@NotNull RenderLayer> layers){
        Objects.requireNonNull(layers, "layers can't be null");
        for (RenderLayer layer : layers) {
            registerBlockRenderLayer(layer);
        }
    }
}
