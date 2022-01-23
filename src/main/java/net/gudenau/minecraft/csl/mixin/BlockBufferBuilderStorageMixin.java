package net.gudenau.minecraft.csl.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.chunk.BlockBufferBuilderStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(BlockBufferBuilderStorage.class)
public abstract class BlockBufferBuilderStorageMixin {
    @Shadow @Final private Map<RenderLayer, BufferBuilder> builders;
    
    @Inject(
        method = "get",
        at = @At("HEAD"),
        cancellable = true
    )
    private void get(RenderLayer layer, CallbackInfoReturnable<BufferBuilder> cir){
        cir.setReturnValue(builders.computeIfAbsent(layer, (renderLayer)->new BufferBuilder(renderLayer.getExpectedBufferSize())));
    }
}
