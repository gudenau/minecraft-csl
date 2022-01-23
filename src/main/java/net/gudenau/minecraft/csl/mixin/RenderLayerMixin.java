package net.gudenau.minecraft.csl.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.impl.CustomRenderLayersImpl;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(RenderLayer.class)
public abstract class RenderLayerMixin {
    @Inject(
        method = "<clinit>",
        at = @At("TAIL")
    )
    private static void staticInit(CallbackInfo ci){
        CustomRenderLayersImpl.INSTANCE.registerVanillaBlockRenderLayers(
            RenderLayer.getSolid(), RenderLayer.getCutoutMipped(), RenderLayer.getCutout(), RenderLayer.getTranslucent(), RenderLayer.getTripwire()
        );
    }
    
    @Inject(
        method = "getBlockLayers",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void getBlockLayers(CallbackInfoReturnable<List<RenderLayer>> cir){
        cir.setReturnValue(CustomRenderLayersImpl.INSTANCE.getBlockRenderLayers());
    }
}
