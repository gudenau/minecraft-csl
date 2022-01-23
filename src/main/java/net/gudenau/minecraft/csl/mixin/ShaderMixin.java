package net.gudenau.minecraft.csl.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.api.v0.CustomShaderTexture;
import net.gudenau.minecraft.csl.api.v0.duck.ShaderDuck;
import net.gudenau.minecraft.csl.api.v0.event.ShaderEvents;
import net.minecraft.client.gl.GlShader;
import net.minecraft.client.gl.GlUniform;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Environment(EnvType.CLIENT)
@Mixin(Shader.class)
public abstract class ShaderMixin implements GlShader, AutoCloseable, ShaderDuck {
    @Shadow public abstract @Nullable GlUniform getUniform(String name);
    
    @Unique private Identifier gud_csl$shaderId;
    @Unique private Object gud_csl$cachedTextureObject;
    
    @Inject(
        method = "<init>",
        at = @At("TAIL")
    )
    private void init(ResourceFactory factory, String name, VertexFormat format, CallbackInfo ci){
        gud_csl$shaderId = new Identifier(name);
        ShaderEvents.NEW.invoker().invoke(gud_csl$shaderId, (Shader)(Object)this);
    }
    
    //FIXME Find a better way
    @Inject(
        method = "bind",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;bindTexture(I)V"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void bind$cacheTexture(
        CallbackInfo ci,
        int activeTexture, int samplerIndex, String samplerName, int uniformLocation, Object textureObject
    ){
        gud_csl$cachedTextureObject = textureObject;
    }
    
    @Redirect(
        method = "bind",
        at = @At(
            value = "INVOKE",
            target = "Lcom/mojang/blaze3d/systems/RenderSystem;bindTexture(I)V"
        )
    )
    private void bind$bindTexture(int texture){
        if(gud_csl$cachedTextureObject instanceof CustomShaderTexture cst){
            cst.bindTexture(texture);
        }else{
            RenderSystem.bindTexture(texture);
        }
        gud_csl$cachedTextureObject = null;
    }
    
    @Override
    public @NotNull Identifier getId() {
        return gud_csl$shaderId;
    }
    
    @Override
    public @Nullable GlUniform getCustomUniform(String name) {
        return getUniform(name);
    }
}
