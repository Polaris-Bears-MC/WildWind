package org.polaris2023.wild_wind;

import org.polaris2023.annotation.AutoConfig;
import org.polaris2023.annotation.config.*;

@AutoConfig(modid = WildWindMod.MOD_ID)
public class WildWindConfig {

    @DefineIntRange(value = "firefly.age", defaultValue = 24000, min = 2000, max = 24000)
    public static int firefly_age;

//    public enum TestEnum implements StringRepresentable {
//        A,B,C,D,E;
//
//        @Override
//        public @NotNull String getSerializedName() {
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