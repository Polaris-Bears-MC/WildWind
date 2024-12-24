package org.polaris2023.wild_wind.config;

import net.minecraft.util.StringRepresentable;
import org.polaris2023.annotation.AutoConfig;
import org.polaris2023.annotation.config.*;
import org.polaris2023.wild_wind.WildWindMod;

import java.util.Locale;

@AutoConfig(modid = WildWindMod.MOD_ID)
@Push("wild-wind-common")
@Note({
        "Wild wind common ->",
        "\tfirefly age?"
})
public class WildWindCommonConfig {

    @Note("firefly age")
    @DefineIntRange(defaultValue = 24000, min = 2000, max = 24000)
    public static int FIREFLY_AGE;

    @SubConfig
    @Push("biome-generated")
    public static class BiomeConfig {
        @DefineIntRange(defaultValue = 80, min = 0, max = 65536)
        public static int OVERWORLD_BIOMES_WEIGHT;
        @DefineIntRange(defaultValue = 80, min = 0, max = 65536)
        public static int NETHER_BIOMES_WEIGHT;
    }

    @SubConfig
    @Push("sounds-config")
    public static class SoundsConfig {
        @Note("living tube sounds in inventory")
        @Define(defaultValue = true)
        public static boolean LIVING_TUBE_IN_INVENTORY;
    }


//    public enum TestEnum implements StringRepresentable {
//        A,B,C,D,E;
//
//        @Override
//        public String getSerializedName() {
//            return name().toLowerCase(Locale.ROOT);
//        }
//    }
//
//    @DefineIntRange(value = "test", defaultValue = 0, min = 0, max = 3)
//    public static int test;
//
//    @DefineLongRange(value = "test.long", defaultValue = 0L, min = 0L, max = 3L)
//    public static long testLong;
//
//    @DefineDoubleRange(value = "test.double", defaultValue = 0.0, min = 0.0, max = 3.0)
//    public static double testDouble;
//
//    @Define(value = "test.boolean", defaultValue = false)
//    public static boolean testb;
//
//    @DefineEnum(value = "test.enum", defaultValue = "A")
//    public static TestEnum testEnum;
}
