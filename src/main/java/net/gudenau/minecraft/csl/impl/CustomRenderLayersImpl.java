package net.gudenau.minecraft.csl.impl;

import com.google.common.collect.ImmutableList;
import net.gudenau.minecraft.csl.CustomShaderLib;
import net.gudenau.minecraft.csl.api.v0.CustomRenderLayers;
import net.minecraft.client.render.RenderLayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class CustomRenderLayersImpl implements CustomRenderLayers {
    public static final CustomRenderLayersImpl INSTANCE = new CustomRenderLayersImpl();
    
    private final List<RenderLayer> renderLayers = new ArrayList<>();
    // We use two here because we don't remove the normal vanilla rendering.
    private final List<RenderLayer> customRenderLayers = new ArrayList<>();
    private List<RenderLayer> immutableRenderLayers = new ArrayList<>();
    private boolean dirty = false;
    
    @Override
    public void registerBlockRenderLayer(@NotNull RenderLayer layer) {
        Objects.requireNonNull(layer, "layer can't be null");
        if(CustomShaderLib.SODIUM_PRESENT){
            return;
        }
        if(!renderLayers.contains(layer)){
            renderLayers.add(layer);
            customRenderLayers.add(layer);
            dirty = true;
        }
    }
    
    public void registerVanillaBlockRenderLayers(RenderLayer... vanillaLayers) {
        renderLayers.addAll(0, Arrays.asList(vanillaLayers));
        dirty = true;
    }
    
    public List<RenderLayer> getBlockRenderLayers(){
        if(dirty){
            immutableRenderLayers = renderLayers.stream().collect(ImmutableList.toImmutableList());
            dirty = false;
        }
        return immutableRenderLayers;
    }
    
    public List<RenderLayer> getCustomRenderLayers() {
        return customRenderLayers;
    }
}
