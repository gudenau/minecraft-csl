package net.gudenau.minecraft.csl.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.GlShader;
import net.minecraft.client.gl.Program;
import net.minecraft.client.render.Shader;
import net.minecraft.resource.ResourceFactory;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Environment(EnvType.CLIENT)
@Mixin(Shader.class)
public abstract class ShaderIdFixMixin implements GlShader, AutoCloseable {
    @Shadow @Final private static String CORE_DIRECTORY;
    
    @Redirect(
        method = "<init>",
        at = @At(
            value = "NEW",
            target = "net/minecraft/util/Identifier"
        )
    )
    private Identifier init$fixId(String name) {
        if (name.contains(":")) {
            var base = new Identifier(name.substring(CORE_DIRECTORY.length(), name.length() - 5));
            return new Identifier(base.getNamespace(), CORE_DIRECTORY + base.getPath() + ".json");
        } else {
            return new Identifier(name);
        }
    }
    
    @Redirect(
        method = "loadProgram",
        at = @At(
            value = "NEW",
            target = "net/minecraft/util/Identifier"
        )
    )
    private static Identifier loadProgram$fixId(
        String identifier,
        ResourceFactory factory, Program.Type type, String name
    ) {
        if (name.contains(":")) {
            var base = new Identifier(name);
            return new Identifier(base.getNamespace(), CORE_DIRECTORY + base.getPath() + type.getFileExtension());
        } else {
            return new Identifier(identifier);
        }
    }
}
