package net.gudenau.minecraft.csl.api.v0.event;

import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.Shader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * A collection of useful shader events.
 */
@Environment(EnvType.CLIENT)
public interface ShaderEvents {
    /**
     * Used to register custom shaders.
     */
    Event<Create> CREATE = EventFactory.createArrayBacked(ShaderEvents.Create.class, (listeners)->(manager)->{
        List<Pair<Shader, Consumer<Shader>>> generators = new ArrayList<>();
        for (var listener : listeners){
            generators.addAll(listener.invoke(manager));
        }
        return generators;
    });
    
    /**
     * Called after a new shader has been created. Can be used to receive uniforms from shaders from other mods or
     * vanilla.
     */
    Event<New> NEW = EventFactory.createArrayBacked(ShaderEvents.New.class, (listeners)->(identifier, shader)->{
        for (var listener : listeners) {
            listener.invoke(identifier, shader);
        }
    });
    
    /**
     * Called before the custom block render layers are rendered, use this to update your shader data.
     */
    Event<PreRender> PRE_RENDER_BLOCKS = EventFactory.createArrayBacked(ShaderEvents.PreRender.class, (listeners)->(tickDelta)->{
        for(var listener : listeners){
            listener.invoke(tickDelta);
        }
    });
    
    /**
     * Used to register custom shaders.
     */
    @FunctionalInterface
    interface Create {
        /**
         * Used to register custom shaders. The shaders should be a created shader and the consumers should be used to
         * set your shader in a container class. This is how vanilla works and is to prevent wierd problems from partial
         * initialization if a shader fails. It is also a useful place to get custom shader uniforms for your custom
         * shaders.
         *
         * The consumer will be called every time shaders reload.
         *
         * For example:
         * <pre>
         * {@code
         * public final class MyShaders {
         *     public static Shader myShader;
         *     public static GlUniform myShaderUniform;
         *
         *     public static void init() {
         *         ShaderEvents.CREATE.register((manager)->{
         *             try {
         *                 return List.of(
         *                     Pair.of(new Shader(manager, "mod_id:my_shader", VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL), (shader)->{
         *                         myShader = shader;
         *                         ShaderDuck duck = (ShaderDuck)shader;
         *                         myShaderUniform = duck.getCustomUniform("MyUniform");
         *                     })
         *                 );
         *             } catch (IOException e) {
         *                 throw new RuntimeException("Failed to load shaders for My Mod", e);
         *             }
         *         ));
         *     }
         * }
         * }
         * </pre>
         *
         * @param manager
         * @return Custom shader pairs
         */
        List<Pair<Shader, Consumer<Shader>>> invoke(ResourceManager manager);
    }
    
    /**
     * Called when a new shader is created.
     */
    @FunctionalInterface
    interface New {
        /**
         * Called when a new shader is created.
         *
         * @param identifier The id of the shader
         * @param shader The shader itself
         */
        void invoke(Identifier identifier, Shader shader);
    }
    
    /**
     * Called before a group of shaders are rendered.
     */
    @FunctionalInterface
    interface PreRender {
        /**
         * Called before a group of shaders are rendered.
         *
         * @param tickDelta The fractions of ticks since the last frame
         */
        void invoke(float tickDelta);
    }
}
