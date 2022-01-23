package net.gudenau.minecraft.csl.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.util.MiscUtil;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.Shader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
@Mixin(RenderPhase.Shader.class)
public interface RenderPhase$ShaderAccessor {
    @Invoker("<init>") public static RenderPhase.Shader init(Supplier<Shader> shader) { return MiscUtil.dummyObject(); }
}
