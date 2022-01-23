package net.gudenau.minecraft.csl.impl;

import net.gudenau.minecraft.csl.accessor.*;
import net.gudenau.minecraft.csl.api.v0.RenderLayerBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.OptionalDouble;
import java.util.function.Supplier;

public final class RenderLayerBuilderImpl implements RenderLayerBuilder {
    // All of these need to be set
    private VertexFormat vertexFormat;
    private VertexFormat.DrawMode drawMode;
    private int expectedBufferSize = -1;
    private Boolean hasCrumbling;
    private Boolean isTranslucent;
    private RenderLayer.OutlineMode outlineMode;
    
    // All of these have defaults from Mojang themselves!
    private RenderPhase.TextureBase texture = RenderPhaseAccessor.getNoTexture();
    private RenderPhase.Shader shader = RenderPhaseAccessor.getNoShader();
    private RenderPhase.Transparency transparency = RenderPhaseAccessor.getNoTransparency();
    private RenderPhase.DepthTest depthTest = RenderPhaseAccessor.getLequalDepthTest();
    private RenderPhase.Cull cull = RenderPhaseAccessor.getEnableCulling();
    private RenderPhase.Lightmap lightmap = RenderPhaseAccessor.getDisableLightmap();
    private RenderPhase.Overlay overlay = RenderPhaseAccessor.getDisableOverlayColor();
    private RenderPhase.Layering layering = RenderPhaseAccessor.getNoLayering();
    private RenderPhase.Target target = RenderPhaseAccessor.getMainTarget();
    private RenderPhase.Texturing texturing = RenderPhaseAccessor.getDefaultTexturing();
    private RenderPhase.WriteMaskState writeMaskState = RenderPhaseAccessor.getAllMask();
    private RenderPhase.LineWidth lineWidth = RenderPhaseAccessor.getFullLinewidth();
    
    public RenderLayerBuilderImpl() {}
    
    @Override
    public @NotNull RenderLayerBuilder vertexFormat(@NotNull VertexFormat vertexFormat) {
        Objects.requireNonNull(vertexFormat, "vertexFormat can't be null");
        this.vertexFormat = vertexFormat;
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder drawMode(VertexFormat.@NotNull DrawMode drawMode) {
        Objects.requireNonNull(drawMode, "drawMode can't be null");
        this.drawMode = drawMode;
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder expectedBufferSize(int expectedBufferSize) {
        if(expectedBufferSize <= 0){
            throw new IllegalArgumentException("expectedBufferSize must be greater than 0");
        }
        this.expectedBufferSize = expectedBufferSize;
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder hasCrumbling(boolean hasCrumbling) {
        this.hasCrumbling = hasCrumbling;
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder translucent(boolean isTranslucent) {
        this.isTranslucent = isTranslucent;
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder outlineMode(@NotNull OutlineMode outlineMode){
        Objects.requireNonNull(outlineMode, "outlineMode can not be null");
        this.outlineMode = switch(outlineMode){
            case NONE -> RenderLayer.OutlineMode.NONE;
            case IS_OUTLINE -> RenderLayer.OutlineMode.IS_OUTLINE;
            case AFFECTS_OUTLINE  -> RenderLayer.OutlineMode.AFFECTS_OUTLINE;
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder texture(@NotNull RenderPhase.TextureBase texture) {
        Objects.requireNonNull(texture, "texture can't be null");
        this.texture = texture;
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder texture(Identifier texture, boolean blur, boolean mipmap) {
       if(texture == null){
            this.texture = RenderPhaseAccessor.getNoTexture();
        }else if(!blur && texture.equals(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)){
            this.texture = mipmap ? RenderPhaseAccessor.getMipmapBlockAtlasTexture() : RenderPhaseAccessor.getBlockAtlasTexture();
        }else{
            this.texture = RenderPhase$TextureAccessor.init(texture, blur, mipmap);
        }
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder shader(@NotNull VanillaShader shader) {
        this.shader = switch (shader){
            case BLOCK -> RenderPhaseAccessor.getBlockShader();
            case NEW_ENTITY -> RenderPhaseAccessor.getNewEntityShader();
            case POSITION_COLOR_LIGHTMAP -> RenderPhaseAccessor.getPositionColorLightmapShader();
            case POSITION -> RenderPhaseAccessor.getPositionShader();
            case POSITION_COLOR_TEXTURE -> RenderPhaseAccessor.getPositionColorTextureShader();
            case POSITION_TEXTURE -> RenderPhaseAccessor.getPositionTextureShader();
            case POSITION_COLOR_TEXTURE_LIGHTMAP -> RenderPhaseAccessor.getPositionColorTextureLightmapShader();
            case COLOR -> RenderPhaseAccessor.getColorShader();
            case SOLID -> RenderPhaseAccessor.getSolidShader();
            case CUTOUT_MIPPED -> RenderPhaseAccessor.getCutoutMippedShader();
            case CUTOUT -> RenderPhaseAccessor.getCutoutShader();
            case TRANSLUCENT -> RenderPhaseAccessor.getTranslucentShader();
            case TRANSLUCENT_MOVING_BLOCK -> RenderPhaseAccessor.getTranslucentMovingBlockShader();
            case TRANSLUCENT_NO_CRUMBLING -> RenderPhaseAccessor.getTranslucentNoCrumblingShader();
            case ARMOR_CUTOUT_NO_CULL -> RenderPhaseAccessor.getArmorCutoutNoCullShader();
            case ENTITY_SOLID -> RenderPhaseAccessor.getEntitySolidShader();
            case ENTITY_CUTOUT -> RenderPhaseAccessor.getEntityCutoutShader();
            case ENTITY_CUTOUT_NONULL -> RenderPhaseAccessor.getEntityCutoutNonullShader();
            case ENTITY_CUTOUT_NONULL_OFFSET_Z -> RenderPhaseAccessor.getEntityCutoutNonullOffsetZShader();
            case ITEM_ENTITY_TRANSLUCENT_CULL -> RenderPhaseAccessor.getItemEntityTranslucentCullShader();
            case ENTITY_TRANSLUCENT_CULL -> RenderPhaseAccessor.getEntityTranslucentCullShader();
            case ENTITY_TRANSLUCENT -> RenderPhaseAccessor.getEntityTranslucentShader();
            case ENTITY_SMOOTH_CUTOUT -> RenderPhaseAccessor.getEntitySmoothCutoutShader();
            case BEACON_BEAM -> RenderPhaseAccessor.getBeaconBeamShader();
            case ENTITY_DECAL -> RenderPhaseAccessor.getEntityDecalShader();
            case ENTITY_NO_OUTLINE -> RenderPhaseAccessor.getEntityNoOutlineShader();
            case ENTITY_SHADOW -> RenderPhaseAccessor.getEntityShadowShader();
            case ENTITY_ALPHA -> RenderPhaseAccessor.getEntityAlphaShader();
            case EYES -> RenderPhaseAccessor.getEyesShader();
            case ENERGY_SWIRL -> RenderPhaseAccessor.getEnergySwirlShader();
            case LEASH -> RenderPhaseAccessor.getLeashShader();
            case WATER_MASK -> RenderPhaseAccessor.getWaterMaskShader();
            case OUTLINE -> RenderPhaseAccessor.getOutlineShader();
            case ARMOR_GLINT -> RenderPhaseAccessor.getArmorGlintShader();
            case ARMOR_ENTITY_GLINT -> RenderPhaseAccessor.getArmorEntityGlintShader();
            case TRANSLUCENT_GLINT -> RenderPhaseAccessor.getTranslucentGlintShader();
            case GLINT -> RenderPhaseAccessor.getGlintShader();
            case DIRECT_GLINT -> RenderPhaseAccessor.getDirectGlintShader();
            case ENTITY_GLINT -> RenderPhaseAccessor.getEntityGlintShader();
            case DIRECT_ENTITY_GLINT -> RenderPhaseAccessor.getDirectEntityGlintShader();
            case CRUMBLING -> RenderPhaseAccessor.getCrumblingShader();
            case TEXT -> RenderPhaseAccessor.getTextShader();
            case TEXT_INTENSITY -> RenderPhaseAccessor.getTextIntensityShader();
            case TRANSPARENT_TEXT -> RenderPhaseAccessor.getTransparentTextShader();
            case TRANSPARENT_TEXT_INTENSITY -> RenderPhaseAccessor.getTransparentTextIntensityShader();
            case LIGHTNING -> RenderPhaseAccessor.getLightningShader();
            case TRIPWIRE -> RenderPhaseAccessor.getTripwireShader();
            case END_PORTAL -> RenderPhaseAccessor.getEndPortalShader();
            case END_GATEWAY -> RenderPhaseAccessor.getEndGatewayShader();
            case LINES -> RenderPhaseAccessor.getLinesShader();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder shader(Supplier<Shader> shader) {
        if(shader == null) {
            this.shader = RenderPhaseAccessor.getNoShader();
        }else{
            this.shader = RenderPhase$ShaderAccessor.init(shader);
        }
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder transparency(@NotNull Transparency transparency) {
        Objects.requireNonNull(transparency, "transparency can't be null");
        this.transparency = switch (transparency){
            case NONE -> RenderPhaseAccessor.getNoTransparency();
            case ADDITIVE -> RenderPhaseAccessor.getAdditiveTransparency();
            case LIGHTNING -> RenderPhaseAccessor.getLightningTransparency();
            case GLINT -> RenderPhaseAccessor.getGlintTransparency();
            case CRUMBLING -> RenderPhaseAccessor.getCrumblingTransparency();
            case TRANSLUCENT -> RenderPhaseAccessor.getTranslucentTransparency();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder depthTest(@NotNull DepthTest depthTest) {
        Objects.requireNonNull(depthTest, "depthTest can't be null");
        this.depthTest = switch (depthTest){
            case ALWAYS -> RenderPhaseAccessor.getAlwaysDepthTest();
            case EQUAL -> RenderPhaseAccessor.getEqualDepthTest();
            case LESS_OR_EQUAL -> RenderPhaseAccessor.getLequalDepthTest();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder culling(boolean enable) {
        this.cull = enable ? RenderPhaseAccessor.getEnableCulling() : RenderPhaseAccessor.getDisableCulling();
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder lightmap(boolean enable) {
        this.lightmap = enable ? RenderPhaseAccessor.getEnableLightmap() : RenderPhaseAccessor.getDisableLightmap();
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder overlayColor(boolean enable) {
        this.overlay = enable ? RenderPhaseAccessor.getEnableOverlayColor() : RenderPhaseAccessor.getDisableOverlayColor();
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder layering(@NotNull Layering layering) {
        Objects.requireNonNull(layering, "layering can't be null");
        this.layering = switch(layering){
            case NONE -> RenderPhaseAccessor.getNoLayering();
            case POLYGON_OFFSET -> RenderPhaseAccessor.getPolygonOffsetLayering();
            case VIEW_OFFSET_Z -> RenderPhaseAccessor.getViewOffsetZLayering();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder target(@NotNull Target target) {
        Objects.requireNonNull(target, "target can't be null");
        this.target = switch (target){
            case MAIN -> RenderPhaseAccessor.getMainTarget();
            case OUTLINE -> RenderPhaseAccessor.getOutlineTarget();
            case TRANSLUCENT -> RenderPhaseAccessor.getTranslucentTarget();
            case PARTICLES -> RenderPhaseAccessor.getParticlesTarget();
            case WEATHER -> RenderPhaseAccessor.getWeatherTarget();
            case CLOUDS -> RenderPhaseAccessor.getCloudsTarget();
            case ITEM -> RenderPhaseAccessor.getItemTarget();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder texturing(@NotNull Texturing texturing) {
        Objects.requireNonNull(texturing, "texturing can't be null");
        this.texturing = switch (texturing){
            case DEFAULT -> RenderPhaseAccessor.getDefaultTexturing();
            case GLINT -> RenderPhaseAccessor.getGlintTexturing();
            case ENTITY_GLINT -> RenderPhaseAccessor.getEntityGlintTexturing();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder writeMask(@NotNull WriteMask writeMask) {
        Objects.requireNonNull(writeMask, "writeMask can't be null");
        this.writeMaskState = switch(writeMask){
            case ALL -> RenderPhaseAccessor.getAllMask();
            case COLOR -> RenderPhaseAccessor.getColorMask();
            case DEPTH -> RenderPhaseAccessor.getDepthMask();
        };
        return this;
    }
    
    @Override
    public @NotNull RenderLayerBuilder lineWidth(boolean enabled, double width) {
        if(enabled){
            this.lineWidth = width == 1 ? RenderPhaseAccessor.getFullLinewidth() : RenderLayer$LineWidthAccessor.init(OptionalDouble.of(width));
        }else{
            this.lineWidth = RenderLayer$LineWidthAccessor.init(OptionalDouble.empty());
        }
        return this;
    }
    
    @Override
    public @NotNull RenderLayer build(@NotNull Identifier identifier) {
        Objects.requireNonNull(identifier, "identifier can't be null");
        if(vertexFormat == null){
            throw new IllegalStateException("vertexFormat was not set");
        }
        if(drawMode == null){
            throw new IllegalStateException("drawMode was not set");
        }
        if(expectedBufferSize == -1){
            throw new IllegalStateException("expectedBufferSize was not set");
        }
        if(hasCrumbling == null){
            throw new IllegalStateException("hasCrumbling was not set");
        }
        if(isTranslucent == null){
            throw new IllegalStateException("hasCrumbling was not set");
        }
        if(outlineMode == null){
            throw new IllegalStateException("outlineMode was not set");
        }
        
        return RenderLayerAccessor.invokeOf(
            identifier.toString(),
            vertexFormat,
            drawMode,
            expectedBufferSize,
            hasCrumbling,
            isTranslucent,
            RenderLayer.MultiPhaseParameters.builder()
                .texture(texture)
                .shader(shader)
                .transparency(transparency)
                .depthTest(depthTest)
                .cull(cull)
                .lightmap(lightmap)
                .overlay(overlay)
                .layering(layering)
                .target(target)
                .texturing(texturing)
                .writeMaskState(writeMaskState)
                .lineWidth(lineWidth)
                .build(outlineMode)
        );
    }
}
