package net.gudenau.minecraft.csl.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.util.MiscUtil;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
@Mixin(RenderPhase.Texture.class)
public interface RenderPhase$TextureAccessor {
    @Invoker("<init>") static RenderPhase.Texture init(Identifier identifier, boolean blur, boolean mipmap) { return MiscUtil.dummyObject(); }
}
