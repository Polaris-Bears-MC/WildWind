package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;
import org.polaris2023.wild_wind.common.init.items.ModNonFunctionItems;
import org.polaris2023.wild_wind.common.init.items.entity.ModSpawnEggs;

import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.TABS;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = WildWindMod.MOD_ID)
public enum ModCreativeTabs implements Supplier<CreativeModeTab> {
    @I18n(en_us = "Wild wind: Building block", zh_cn = "原野之风：建筑方块", zh_tw = "原野之風：建築方塊")
    BUILDING_BLOCK(ModBlocks.BRITTLE_ICE::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.BRITTLE_ICE_ITEM);
        output.accept(ModBlocks.SALT_BLOCK_ITEM);
    }),
    @I18n(en_us = "Wild wind: Colored block", zh_cn = "原野之风：染色方块", zh_tw = "原野之風：染色方塊")
    COLORED_BLOCKS(ModBlocks.CATTAILS_ITEM::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.CATTAILS_ITEM);
        output.accept(ModBlocks.WOOL_ITEM);
        output.accept(ModBlocks.CONCRETE_ITEM);
        output.accept(ModBlocks.GLAZED_TERRACOTTA_ITEM);
    }),
    @I18n(en_us = "Wild wind: Natural block", zh_cn = "原野之风：自然方块", zh_tw = "原野之風：自然方塊")
    NATURAL_BLOCKS(ModBlocks.SALT_BLOCK_ITEM::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.BRITTLE_ICE_ITEM);
        output.accept(ModBlocks.SALT_BLOCK_ITEM);
        output.accept(ModBlocks.DUCKWEED_ITEM);
        output.accept(ModBlocks.SCULK_JAW_ITEM);
        output.accept(ModBlocks.SALT_ORE_ITEM);
        output.accept(ModBlocks.DEEPSLATE_SALT_ORE_ITEM);
    }),
    @I18n(en_us = "Wild wind: Tools and Utilities", zh_cn = "原野之风：工具与实用物品", zh_tw = "原野之風：工具與實用物品")
    TOOLS_AND_UTILITIES(ModItems.MAGIC_FLUTE::toStack, () -> (__, output) -> {
        output.accept(ModItems.MAGIC_FLUTE);
        output.accept(Items.BUCKET);
        output.accept(ModMobBuckets.PIRANHA_BUCKET.get());
        output.accept(ModMobBuckets.TROUT_BUCKET.get());

    }),
    @I18n(en_us = "Wild wind: Food & drink", zh_cn = "原野之风：食物与饮品", zh_tw = "原野之風：食物與飲品")
    FOOD_AND_DRINK(ModItems.PUMPKIN_SLICE::toStack,
            () -> (__, output) -> {
                List<Item> ignoresFood =
                        List.of(ModItems.NETHER_MUSHROOM_STEW.get(), ModItems.FISH_CHOWDER.get());// ignore some food
                for (DeferredHolder<Item, ? extends Item> item : ModInitializer.items()) {
                    Item it = item.get();
                    if (it.components().has(DataComponents.FOOD) && !ignoresFood.contains(it)) {
                        output.accept(it);
                    }
                }
            }),
    @I18n(en_us = "Wild wind: Ingredients", zh_cn = "原野之风：原材料", zh_tw = "原野之風：原材料")
    INGREDIENTS(ModBlocks.GLOW_MUCUS_ITEM::toStack, () -> (__, output) -> {
        output.accept(ModBlocks.GLOW_MUCUS_ITEM);
        output.accept(ModNonFunctionItems.GLOW_POWDER.get());
        output.accept(ModBlocks.GLAREFLOWER_ITEM);
        output.accept(ModBlocks.GLAREFLOWER_SEEDS_ITEM);
        output.accept(ModBlocks.REEDS_ITEM);
        output.accept(ModBlocks.CATTAILS_ITEM);
    }),
    @I18n(en_us = "Wild wind: Spawn Eggs", zh_cn = "原野之风：刷怪蛋", zh_tw = "原野之風：生怪蛋")
    SPAWN_EGGS(ModSpawnEggs.FIREFLY_SPAWN_EGG.entry::toStack, () -> (__, output) -> {
        output.accept(ModSpawnEggs.FIREFLY_SPAWN_EGG.get());
        output.accept(ModSpawnEggs.GLARE_SPAWN_EGG.get());
        output.accept(ModSpawnEggs.PIRANHA_SPAWN_EGG.get());
        output.accept(ModSpawnEggs.TROUT_SPAWN_EGG.get());
    }),
    @I18n(en_us = "Wild wind: Misc", zh_cn = "原野之风：杂项", zh_tw = "原野之風：雜項")
    WILD_WIND(ModBlocks.COOKING_POT_ITEM::toStack,
            () -> (__, output) -> {
                for (DeferredHolder<Item, ? extends Item> item : ModInitializer.items()) {
                    if (checkOr(item,
                            BUILDING_BLOCK,
                            COLORED_BLOCKS,
                            NATURAL_BLOCKS,
                            TOOLS_AND_UTILITIES,
                            FOOD_AND_DRINK,
                            INGREDIENTS,
                            SPAWN_EGGS
                    )) {
                        output.accept(item.get());
                    }
                }
                //肉食
                ItemStack stack = new ItemStack(Items.SLIME_BALL);
                stack.set(ModComponents.SLIME_COLOR, 100);
                output.accept(stack);
            }),

    ;

    @SafeVarargs
    private static <T extends Item> boolean checkOr(DeferredHolder<Item, T> item, Supplier<CreativeModeTab>... suppliers) {
        for (Supplier<CreativeModeTab> supplier : suppliers) {
            if (!check(item, supplier)) {
                return false;
            }
        }
        return true;
    }

    private static <T extends Item> boolean check(DeferredHolder<Item, T> item, Supplier<CreativeModeTab> supplier) {
        return supplier.get().getDisplayItems().stream().filter(stack -> stack.is(item)).findFirst().isEmpty();
    }

    private final DeferredHolder<CreativeModeTab, CreativeModeTab> tabs;
    ModCreativeTabs(Supplier<ItemStack> icon,
                    Supplier<CreativeModeTab.DisplayItemsGenerator> parameters) {
        tabs = TABS.register(name().toLowerCase(Locale.ROOT), () -> CreativeModeTab
                .builder()
                .icon(icon)
                .title(Component.translatable("tabs.%s.%s.title"
                        .formatted(
                                WildWindMod.MOD_ID,
                                name().toLowerCase(Locale.ROOT))))
                .displayItems(parameters.get())
                .build());
    }


    @SubscribeEvent
    public static void buildGroup(BuildCreativeModeTabContentsEvent event) {

    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public CreativeModeTab get() {
        return tabs.get();
    }
}
