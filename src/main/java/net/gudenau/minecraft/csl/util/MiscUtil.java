package net.gudenau.minecraft.csl.util;

public final class MiscUtil {
    private MiscUtil(){}
    
    /**
     * Should only be used for static methods in accessors or static shadow methods in mixins. Used for null inspection
     * stuff, will almost always throw an exception if actually called.
     *
     * @param <T> The object type
     * @return A new Object
     */
    @SuppressWarnings("unchecked")
    public static <T> T dummyObject(){
        return (T)new Object();
    }
}
