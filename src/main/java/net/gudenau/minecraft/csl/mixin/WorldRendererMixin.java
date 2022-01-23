package net.gudenau.minecraft.csl.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.CustomShaderLib;
import net.gudenau.minecraft.csl.api.v0.event.ShaderEvents;
import net.gudenau.minecraft.csl.impl.CustomRenderLayersImpl;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    @Shadow protected abstract void renderLayer(RenderLayer renderLayer, MatrixStack matrices, double d, double e, double f, Matrix4f positionMatrix);
    
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/WorldRenderer;renderLayer(Lnet/minecraft/client/render/RenderLayer;Lnet/minecraft/client/util/math/MatrixStack;DDDLnet/minecraft/util/math/Matrix4f;)V",
            shift = At.Shift.AFTER,
            ordinal = 2
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void render(
        MatrixStack matrices, float tickDelta, long limitTime, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f,
        CallbackInfo ci,
        Profiler profiler, boolean hasNoUpdaters, Vec3d camPos, double camX, double camY, double camZ
    ){
        // Sodium doesn't support custom render layers, if we try to render ours Sodium crashes.
        if(CustomShaderLib.SODIUM_PRESENT){
            return;
        }
        
        ShaderEvents.PRE_RENDER_BLOCKS.invoker().invoke(tickDelta);
        for(RenderLayer layer : CustomRenderLayersImpl.INSTANCE.getCustomRenderLayers()){
            renderLayer(layer, matrices, camX, camY, camZ, matrix4f);
        }
    }
}
