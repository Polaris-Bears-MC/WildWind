package org.polaris2023.wild_wind.common.init.items.foods;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.common.init.ModInitializer;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 20:43:50}
 */
@BasicItem
public enum ModNonFunctionFoods implements Supplier<Item> {
    @I18n(en_us = "Baked Living Tuber", zh_cn = "烤活根", zh_tw = "烤活根")
    BAKED_LIVING_TUBER(
            p -> p
                    .stacksTo(16)
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F),
            ModFoods.COOKED_LIVING_TUBER),
    @I18n(en_us = "Raw Trout", zh_cn = "生鳟鱼", zh_tw = "生鱒魚")
    RAW_TROUT(
            p -> p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F),
            ModFoods.RAW_TROUT),
    @I18n(en_us = "Cooked Trout", zh_cn = "烤鳟鱼", zh_tw = "烤鱒魚")
    COOKED_TROUT(p ->
            p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F),
            ModFoods.COOKED_TROUT),
    @I18n(en_us = "Raw Piranha", zh_cn = "生食人鲳", zh_tw = "生食人魚")
    RAW_PIRANHA(
            p -> p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F),
            ModFoods.RAW_TROUT),
    @I18n(en_us = "Cooked Piranha", zh_cn = "烤食人鲳", zh_tw = "烤食人魚")
    COOKED_PIRANHA(
            p -> p
                    .component(ModComponents.MEAT_VALUE, 0.5F)
                    .component(ModComponents.FISH_VALUE, 1F),
            ModFoods.COOKED_TROUT),
    @I18n(en_us = "Baked Beetroot", zh_cn = "烤甜菜根", zh_tw = "烤甜菜根")
    BAKED_BEETROOT(p ->
            p
                    .component(ModComponents.VEGETABLE_VALUE, 1F),
            ModFoods.BAKED_BEETROOT),
    @I18n(en_us = "Baked carrot", zh_tw = "烤胡蘿蔔", zh_cn = "烤胡萝卜")
    BAKED_CARROT(
            p -> p
                    .component(ModComponents.VEGETABLE_VALUE, 1F),
            ModFoods.BAKED_CARROT),

    ;

    public final DeferredItem<Item> entry;

    ModNonFunctionFoods(Consumer<Item.Properties> consumer, Supplier<FoodProperties> food) {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT), consumer, food);
    }

    public static void init() {}

    @Override
    public Item get() {
        return entry.get();
    }
}
