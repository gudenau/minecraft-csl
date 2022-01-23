package net.gudenau.minecraft.csl.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.util.MiscUtil;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
@Mixin(RenderPhase.class)
public interface RenderPhaseAccessor {
    @Accessor("NO_SHADER") static RenderPhase.Shader getNoShader(){ return MiscUtil.dummyObject(); }
    @Accessor("BLOCK_SHADER") static RenderPhase.Shader getBlockShader(){ return MiscUtil.dummyObject(); }
    @Accessor("NEW_ENTITY_SHADER") static RenderPhase.Shader getNewEntityShader(){ return MiscUtil.dummyObject(); }
    @Accessor("POSITION_COLOR_LIGHTMAP_SHADER") static RenderPhase.Shader getPositionColorLightmapShader(){ return MiscUtil.dummyObject(); }
    @Accessor("POSITION_SHADER") static RenderPhase.Shader getPositionShader(){ return MiscUtil.dummyObject(); }
    @Accessor("POSITION_COLOR_TEXTURE_SHADER") static RenderPhase.Shader getPositionColorTextureShader(){ return MiscUtil.dummyObject(); }
    @Accessor("POSITION_TEXTURE_SHADER") static RenderPhase.Shader getPositionTextureShader(){ return MiscUtil.dummyObject(); }
    @Accessor("POSITION_COLOR_TEXTURE_LIGHTMAP_SHADER") static RenderPhase.Shader getPositionColorTextureLightmapShader(){ return MiscUtil.dummyObject(); }
    @Accessor("COLOR_SHADER") static RenderPhase.Shader getColorShader(){ return MiscUtil.dummyObject(); }
    @Accessor("SOLID_SHADER") static RenderPhase.Shader getSolidShader(){ return MiscUtil.dummyObject(); }
    @Accessor("CUTOUT_MIPPED_SHADER") static RenderPhase.Shader getCutoutMippedShader(){ return MiscUtil.dummyObject(); }
    @Accessor("CUTOUT_SHADER") static RenderPhase.Shader getCutoutShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSLUCENT_SHADER") static RenderPhase.Shader getTranslucentShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSLUCENT_MOVING_BLOCK_SHADER") static RenderPhase.Shader getTranslucentMovingBlockShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSLUCENT_NO_CRUMBLING_SHADER") static RenderPhase.Shader getTranslucentNoCrumblingShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ARMOR_CUTOUT_NO_CULL_SHADER") static RenderPhase.Shader getArmorCutoutNoCullShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_SOLID_SHADER") static RenderPhase.Shader getEntitySolidShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_CUTOUT_SHADER") static RenderPhase.Shader getEntityCutoutShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_CUTOUT_NONULL_SHADER") static RenderPhase.Shader getEntityCutoutNonullShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_CUTOUT_NONULL_OFFSET_Z_SHADER") static RenderPhase.Shader getEntityCutoutNonullOffsetZShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ITEM_ENTITY_TRANSLUCENT_CULL_SHADER") static RenderPhase.Shader getItemEntityTranslucentCullShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_TRANSLUCENT_CULL_SHADER") static RenderPhase.Shader getEntityTranslucentCullShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_TRANSLUCENT_SHADER") static RenderPhase.Shader getEntityTranslucentShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_SMOOTH_CUTOUT_SHADER") static RenderPhase.Shader getEntitySmoothCutoutShader(){ return MiscUtil.dummyObject(); }
    @Accessor("BEACON_BEAM_SHADER") static RenderPhase.Shader getBeaconBeamShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_DECAL_SHADER") static RenderPhase.Shader getEntityDecalShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_NO_OUTLINE_SHADER") static RenderPhase.Shader getEntityNoOutlineShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_SHADOW_SHADER") static RenderPhase.Shader getEntityShadowShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_ALPHA_SHADER") static RenderPhase.Shader getEntityAlphaShader(){ return MiscUtil.dummyObject(); }
    @Accessor("EYES_SHADER") static RenderPhase.Shader getEyesShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENERGY_SWIRL_SHADER") static RenderPhase.Shader getEnergySwirlShader(){ return MiscUtil.dummyObject(); }
    @Accessor("LEASH_SHADER") static RenderPhase.Shader getLeashShader(){ return MiscUtil.dummyObject(); }
    @Accessor("WATER_MASK_SHADER") static RenderPhase.Shader getWaterMaskShader(){ return MiscUtil.dummyObject(); }
    @Accessor("OUTLINE_SHADER") static RenderPhase.Shader getOutlineShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ARMOR_GLINT_SHADER") static RenderPhase.Shader getArmorGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ARMOR_ENTITY_GLINT_SHADER") static RenderPhase.Shader getArmorEntityGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSLUCENT_GLINT_SHADER") static RenderPhase.Shader getTranslucentGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("GLINT_SHADER") static RenderPhase.Shader getGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("DIRECT_GLINT_SHADER") static RenderPhase.Shader getDirectGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_GLINT_SHADER") static RenderPhase.Shader getEntityGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("DIRECT_ENTITY_GLINT_SHADER") static RenderPhase.Shader getDirectEntityGlintShader(){ return MiscUtil.dummyObject(); }
    @Accessor("CRUMBLING_SHADER") static RenderPhase.Shader getCrumblingShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TEXT_SHADER") static RenderPhase.Shader getTextShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TEXT_INTENSITY_SHADER") static RenderPhase.Shader getTextIntensityShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSPARENT_TEXT_SHADER") static RenderPhase.Shader getTransparentTextShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSPARENT_TEXT_INTENSITY_SHADER") static RenderPhase.Shader getTransparentTextIntensityShader(){ return MiscUtil.dummyObject(); }
    @Accessor("LIGHTNING_SHADER") static RenderPhase.Shader getLightningShader(){ return MiscUtil.dummyObject(); }
    @Accessor("TRIPWIRE_SHADER") static RenderPhase.Shader getTripwireShader(){ return MiscUtil.dummyObject(); }
    @Accessor("END_PORTAL_SHADER") static RenderPhase.Shader getEndPortalShader(){ return MiscUtil.dummyObject(); }
    @Accessor("END_GATEWAY_SHADER") static RenderPhase.Shader getEndGatewayShader(){ return MiscUtil.dummyObject(); }
    @Accessor("LINES_SHADER") static RenderPhase.Shader getLinesShader(){ return MiscUtil.dummyObject(); }
    @Accessor("MIPMAP_BLOCK_ATLAS_TEXTURE") static RenderPhase.Texture getMipmapBlockAtlasTexture(){ return MiscUtil.dummyObject(); }
    @Accessor("BLOCK_ATLAS_TEXTURE") static RenderPhase.Texture getBlockAtlasTexture(){ return MiscUtil.dummyObject(); }
    @Accessor("NO_TEXTURE") static RenderPhase.TextureBase getNoTexture(){ return MiscUtil.dummyObject(); }
    @Accessor("DEFAULT_TEXTURING") static RenderPhase.Texturing getDefaultTexturing(){ return MiscUtil.dummyObject(); }
    @Accessor("GLINT_TEXTURING") static RenderPhase.Texturing getGlintTexturing(){ return MiscUtil.dummyObject(); }
    @Accessor("ENTITY_GLINT_TEXTURING") static RenderPhase.Texturing getEntityGlintTexturing(){ return MiscUtil.dummyObject(); }
    @Accessor("ENABLE_LIGHTMAP") static RenderPhase.Lightmap getEnableLightmap(){ return MiscUtil.dummyObject(); }
    @Accessor("DISABLE_LIGHTMAP") static RenderPhase.Lightmap getDisableLightmap(){ return MiscUtil.dummyObject(); }
    @Accessor("ENABLE_OVERLAY_COLOR") static RenderPhase.Overlay getEnableOverlayColor(){ return MiscUtil.dummyObject(); }
    @Accessor("DISABLE_OVERLAY_COLOR") static RenderPhase.Overlay getDisableOverlayColor(){ return MiscUtil.dummyObject(); }
    @Accessor("ENABLE_CULLING") static RenderPhase.Cull getEnableCulling(){ return MiscUtil.dummyObject(); }
    @Accessor("DISABLE_CULLING") static RenderPhase.Cull getDisableCulling(){ return MiscUtil.dummyObject(); }
    @Accessor("ALWAYS_DEPTH_TEST") static RenderPhase.DepthTest getAlwaysDepthTest(){ return MiscUtil.dummyObject(); }
    @Accessor("EQUAL_DEPTH_TEST") static RenderPhase.DepthTest getEqualDepthTest(){ return MiscUtil.dummyObject(); }
    @Accessor("LEQUAL_DEPTH_TEST") static RenderPhase.DepthTest getLequalDepthTest(){ return MiscUtil.dummyObject(); }
    @Accessor("ALL_MASK") static RenderPhase.WriteMaskState getAllMask(){ return MiscUtil.dummyObject(); }
    @Accessor("COLOR_MASK") static RenderPhase.WriteMaskState getColorMask(){ return MiscUtil.dummyObject(); }
    @Accessor("DEPTH_MASK") static RenderPhase.WriteMaskState getDepthMask(){ return MiscUtil.dummyObject(); }
    @Accessor("NO_LAYERING") static RenderPhase.Layering getNoLayering(){ return MiscUtil.dummyObject(); }
    @Accessor("POLYGON_OFFSET_LAYERING") static RenderPhase.Layering getPolygonOffsetLayering(){ return MiscUtil.dummyObject(); }
    @Accessor("NO_TRANSPARENCY") static RenderPhase.Transparency getNoTransparency(){ return MiscUtil.dummyObject(); }
    @Accessor("ADDITIVE_TRANSPARENCY") static RenderPhase.Transparency getAdditiveTransparency(){ return MiscUtil.dummyObject(); }
    @Accessor("LIGHTNING_TRANSPARENCY") static RenderPhase.Transparency getLightningTransparency(){ return MiscUtil.dummyObject(); }
    @Accessor("GLINT_TRANSPARENCY") static RenderPhase.Transparency getGlintTransparency(){ return MiscUtil.dummyObject(); }
    @Accessor("CRUMBLING_TRANSPARENCY") static RenderPhase.Transparency getCrumblingTransparency(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSLUCENT_TRANSPARENCY") static RenderPhase.Transparency getTranslucentTransparency(){ return MiscUtil.dummyObject(); }
    @Accessor("VIEW_OFFSET_Z_LAYERING") static RenderPhase.Layering getViewOffsetZLayering(){ return MiscUtil.dummyObject(); }
    @Accessor("MAIN_TARGET") static RenderPhase.Target getMainTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("OUTLINE_TARGET") static RenderPhase.Target getOutlineTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("TRANSLUCENT_TARGET") static RenderPhase.Target getTranslucentTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("PARTICLES_TARGET") static RenderPhase.Target getParticlesTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("WEATHER_TARGET") static RenderPhase.Target getWeatherTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("CLOUDS_TARGET") static RenderPhase.Target getCloudsTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("ITEM_TARGET") static RenderPhase.Target getItemTarget(){ return MiscUtil.dummyObject(); }
    @Accessor("FULL_LINE_WIDTH") static RenderPhase.LineWidth getFullLinewidth(){ return MiscUtil.dummyObject(); }
}
