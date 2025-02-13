package org.polaris2023.wild_wind.common.init;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;
import org.polaris2023.wild_wind.common.init.items.ModNonFunctionItems;
import org.polaris2023.wild_wind.common.init.items.entity.ModSpawnEggs;
import org.polaris2023.wild_wind.common.item.LivingTuberItem;
import org.polaris2023.wild_wind.common.item.food.CheeseItem;
import org.polaris2023.wild_wind.common.item.food.NetherMushroomStewItem;
import org.polaris2023.wild_wind.common.item.MagicFluteItem;

import static org.polaris2023.wild_wind.common.init.ModInitializer.*;


@BasicItem
public class ModItems {

    @I18n(en_us = "Living Tuber", zh_cn = "活根", zh_tw = "活根")
    public static final DeferredItem<LivingTuberItem> LIVING_TUBER =
            register("living_tuber", properties -> new LivingTuberItem(properties
                    .stacksTo(16)
                    .component(ModComponents.VEGETABLE_VALUE, 1F)
                    .component(ModComponents.MEAT_VALUE, 1F)
                    .component(ModComponents.MONSTER_VALUE, 1F)
                    .food(ModFoods.LIVING_TUBER.get())));

    @BasicItem(used = false)// don't run datagen by this
    @I18n(en_us = "Magic Flute", zh_cn = "魔笛", zh_tw = "魔笛")
    public static final DeferredItem<MagicFluteItem> MAGIC_FLUTE =
            register("magic_flute", MagicFluteItem::stackTo1);


    @I18n(en_us = "Cheese", zh_tw = "起司", zh_cn = "奶酪")
    public static final DeferredItem<CheeseItem> CHEESE =
            register("cheese", p -> new CheeseItem(p.stacksTo(16).food(ModFoods.CHEESE.get())));

    @I18n(en_us = "Cooked Egg", zh_cn = "煎蛋", zh_tw = "煎蛋")
    public static final DeferredItem<Item> COOKED_EGG =
            simpleItem("cooked_egg", p -> p
                            .component(ModComponents.PROTEIN_VALUE, 1F),
                    ModFoods.COOKED_EGG);

    @I18n(en_us = "Dough", zh_cn = "面团", zh_tw = "麵團")
    public static final DeferredItem<Item> DOUGH = simpleItem("dough", ModFoods.DOUGH);

    @I18n(en_us = "Fish Chowder", zh_cn = "海鲜杂烩", zh_tw = "海鮮雜燴")
    public static final DeferredItem<Item> FISH_CHOWDER =
            simpleItem("fish_chowder", p -> p
                            .stacksTo(1),
                    ModFoods.FISH_CHOWDER);

    @I18n(en_us = "Russian Soup", zh_cn = "罗宋汤", zh_tw = "羅宋湯")
    public static final DeferredItem<Item> RUSSIAN_SOUP =
            simpleItem("russian_soup", p -> p.stacksTo(1));

    @I18n(en_us = "Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    public static final DeferredItem<Item> PUMPKIN_SLICE =
            simpleItem("pumpkin_slice", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 0.5F),
                    ModFoods.PUMPKIN_SLICE);

    @I18n(en_us = "Baked Pumpkin Slice", zh_cn = "南瓜片", zh_tw = "南瓜片")
    public static final DeferredItem<Item> BAKED_PUMPKIN_SLICE =
            simpleItem("baked_pumpkin_slice", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 0.5F),
                    ModFoods.BAKED_PUMPKIN_SLICE);

    @I18n(en_us = "Baked Apple", zh_cn = "烤苹果", zh_tw = "烤蘋果")
    public static final DeferredItem<Item> BAKED_APPLE =
            simpleItem("baked_apple", p -> p
                            .component(ModComponents.FRUIT_VALUE, 1F),
                    ModFoods.BAKED_APPLE);

    @I18n(en_us = "Baked Melon Slice", zh_cn = "烤西瓜片", zh_tw = "烤西瓜片")
    public static final DeferredItem<Item> BAKED_MELON_SLICE =
            simpleItem("baked_melon_slice", p -> p
                            .component(ModComponents.FRUIT_VALUE, 0.5F),
                    ModFoods.BAKED_MELON_SLICE);

    @I18n(en_us = "Vegetable Soup", zh_cn = "蔬菜浓汤", zh_tw = "蔬菜濃湯")
    public static final DeferredItem<Item> VEGETABLE_SOUP =
            simpleItem("vegetable_soup", p -> p.stacksTo(1));

    @I18n(en_us = "Flour", zh_cn = "面粉", zh_tw = "麵粉")
    public static final DeferredItem<Item> FLOUR = simpleItem("flour", ModFoods.FLOUR);

    @I18n(en_us = "Spider Egg", zh_cn = "蜘蛛卵", zh_tw = "蜘蛛卵")
    public static final DeferredItem<Item> SPIDER_EGG =
            simpleItem("spider_egg", p -> p.stacksTo(1));

    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredItem<Item> SPIDER_MUCOSA = simpleItem("spider_mucosa");

    @I18n(en_us = "Nether Mushroom Stew", zh_cn = "下界蘑菇煲", zh_tw = "下界蘑菇煲")
    public static final DeferredItem<NetherMushroomStewItem> NETHER_MUSHROOM_STEW =
            register("nether_mushroom_stew", properties -> 
                    new NetherMushroomStewItem(properties.stacksTo(1), ModFoods.NETHER_MUSHROOM_STEW));

    @I18n(en_us = "Baked Mushroom", zh_cn = "烤蘑菇", zh_tw = "烤蘑菇")
    public static final DeferredItem<Item> BAKED_MUSHROOM =
            simpleItem("baked_mushroom", p -> p
                            .component(ModComponents.VEGETABLE_VALUE, 0.5F),
                    ModFoods.BAKED_MUSHROOM);

    @I18n(en_us = "Baked Seeds", zh_cn = "烤种子", zh_tw = "烤種子")
    public static final DeferredItem<Item> BAKED_SEEDS =
            simpleItem("baked_seeds", ModFoods.BAKED_SEEDS);

    @I18n(en_us = "Baked Berries", zh_cn = "烤浆果", zh_tw = "烤莓醬")
    public static final DeferredItem<Item> BAKED_BERRIES =
            simpleItem("baked_berries", p -> p
                    .component(ModComponents.FRUIT_VALUE, 0.5F),
            ModFoods.BAKED_BERRIES);

    static {
        ModNonFunctionItems.init();
        ModSpawnEggs.init();
        ModMobBuckets.init();
    }

    @I18n(en_us = "Raw Frog Leg", zh_cn = "生蛙腿", zh_tw = "生蛙腿")
    public static final DeferredItem<Item> RAW_FROG_LEG =
            simpleItem("raw_frog_leg", p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.MONSTER_VALUE, 1F),
                    ModFoods.RAW_FROG_LEG);

    @I18n(en_us = "Cooked Frog Leg", zh_cn = "烤蛙腿", zh_tw = "烤蛙腿")
    public static final DeferredItem<Item> COOKED_FROG_LEG =
            simpleItem("cooked_frog_leg", p -> p
                            .component(ModComponents.MEAT_VALUE, 0.5F)
                            .component(ModComponents.MONSTER_VALUE, 1F),
                    ModFoods.COOKED_FROG_LEG);

    @I18n(en_us = "salt", zh_cn = "盐", zh_tw = "鹽")
    public static final DeferredItem<Item> SALT =
            simpleItem("salt", p -> p.stacksTo(16), ModFoods.SALT);

}
