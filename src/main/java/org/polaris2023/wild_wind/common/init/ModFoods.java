package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.mojang.datafixers.util.Pair.of;

public enum ModFoods implements Supplier<FoodProperties> {
    KELP(1, 0.1F),
    CHEESE(3, 1.2F),
    FISH_CHOWDER(8, 0.6F),
    EGG(2, 0.3F,
            of(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)),
    COOKED_EGG(5, 0.6F),
    BAKED_BEETROOT(6, 0.6F),
    BAKED_CARROT(7, 0.6F),
    BAKED_APPLE(5, 0.3F),
    BAKED_MELON_SLICE(4, 0.3F),
    RAW_TROUT(2, 0.1F,
            of(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)),
    COOKED_TROUT(6, 0.8F),
    BROWN_MUSHROOM(1, 0.6F,
            of(() -> new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), 0.5F)),
    RED_MUSHROOM(1, 0.6F,
            of(() -> new MobEffectInstance(MobEffects.POISON, 160, 0), 0.5F)),
    CRIMSON_FUNGUS(1, 0.6F,
            of(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200, 0), 1.0F)),
    WARPED_FUNGUS(1, 0.8F,
            of(() -> new MobEffectInstance(MobEffects.WITHER, 240, 0), 1.0F)),
    SEEDS(1, 0.1F),
    POISON_SEEDS(1, 0.1F,
            of(() -> new MobEffectInstance(MobEffects.POISON, 80, 0), 0.5F)),
    BAKED_SEEDS(4, 0.1F),
    PUMPKIN_SLICE(2, 0.3F),
    BAKED_PUMPKIN_SLICE(4, 0.3F),
    SUGAR_CANE(2, 0.3F),
    SUGAR(1, 1.2F,
            of(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60, 0), 0.3F)),
    FERMENTED_SPIDER_EYE(3, 0.8F,
            of(() -> new MobEffectInstance(MobEffects.BLINDNESS, 200, 0), 0.5F)),
    GLISTERING_MELON_SLICE(4, 0.6F,
            of(() -> new MobEffectInstance(MobEffects.HEAL, 1, 0), 0.3F)),
    BAKED_MUSHROOM(4, 0.6F),
    NETHER_MUSHROOM_STEW(6, 0.6F),
    LIVING_TUBER(4, 0.1F,
            of(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 6), 1F),
            of(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 3), 1F),
            of(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1F),
            of(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 1F)),
    COOKED_LIVING_TUBER(8, 0.6F,
            of(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 6), 1F),
            of(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 3), 1F),
            of(() -> new MobEffectInstance(MobEffects.WEAKNESS, 600, 0), 1F),
            of(() -> new MobEffectInstance(MobEffects.BLINDNESS, 600, 0), 1F)),

    MILK(2, 0.1F),
    RAW_FROG_LEG(1, 0.9F,
            of(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)),
    COOKED_FROG_LEG(2, 3),
    BAKED_BERRIES(3, 0.8F), SNIFFER_EGG(2, 1.3F,
            of(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)),
    DRAGON_EGG(5, 6,
            of(() -> new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F));

    private final FoodProperties properties;

    @SafeVarargs
    ModFoods(int nutrition, float saturationModifier, Consumer<FoodProperties.Builder> consumer, Pair<Supplier<MobEffectInstance>, Float>... pairs) {
        FoodProperties.Builder p = new FoodProperties.Builder();
        p.nutrition(nutrition);
        p.saturationModifier(saturationModifier);
        consumer.accept(p);
        for (var pair : pairs) {
            p.effect(pair.getFirst(), pair.getSecond());
        }
        this.properties = p.build();
    }

    @SafeVarargs
    ModFoods(int nutrition, float saturationModifier, Pair<Supplier<MobEffectInstance>, Float>... pairs) {
        this(nutrition, saturationModifier, p -> {}, pairs);
    }

    @Override
    public FoodProperties get() {
        return properties;
    }
}
