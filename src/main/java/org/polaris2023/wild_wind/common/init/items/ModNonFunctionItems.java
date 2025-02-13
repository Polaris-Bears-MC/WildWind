package org.polaris2023.wild_wind.common.init.items;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.init.ModComponents;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.polaris2023.wild_wind.common.init.ModInitializer;
import org.polaris2023.wild_wind.common.init.items.foods.ModNonFunctionFoods;

import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 20:19:42}
 */
@BasicItem
public enum ModNonFunctionItems implements Supplier<Item> {
    @I18n(en_us = "Glow Powder", zh_cn = "萤光粉末", zh_tw = "螢光粉末")
    GLOW_POWDER,
    @I18n(en_us = "Apple Cake", zh_cn = "苹果派", zh_tw = "蘋果派")
    APPLE_CAKE,
    @I18n(en_us = "Berry Cake", zh_cn = "浆果派", zh_tw = "漿果派")
    BERRY_CAKE,
    @I18n(en_us = "Candy", zh_cn = "糖果", zh_tw = "糖果")
    CANDY(p -> p.stacksTo(16)),
    @I18n(en_us = "Cheese Pumpkin soup", zh_cn = "奶酪南瓜汤", zh_tw = "起司南瓜湯")
    CHEESE_PUMPKIN_SOUP(p -> p.stacksTo(1)),
    ;
    public final DeferredItem<Item> entry;
    ModNonFunctionItems() {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT));
    }
    ModNonFunctionItems(Consumer<Item.Properties> consumer) {
        entry = ModInitializer.simpleItem(name().toLowerCase(Locale.ROOT), consumer);
    }

    public static void init() {
        ModNonFunctionFoods.init();
    }

    @Override
    public Item get() {
        return entry.get();
    }
}
