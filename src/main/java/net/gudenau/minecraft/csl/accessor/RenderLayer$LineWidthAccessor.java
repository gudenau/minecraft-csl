package net.gudenau.minecraft.csl.accessor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.gudenau.minecraft.csl.util.MiscUtil;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.OptionalDouble;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "unused"})
@Environment(EnvType.CLIENT)
@Mixin(RenderPhase.LineWidth.class)
public interface RenderLayer$LineWidthAccessor {
    @Invoker("<init>") static RenderPhase.LineWidth init(OptionalDouble width) { return MiscUtil.dummyObject(); }
}
