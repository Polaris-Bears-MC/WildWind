package org.polaris2023.wild_wind.datagen.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.ModNonFunctionItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModNonFunctionFoods;
import org.polaris2023.wild_wind.common.init.tags.ModItemTags;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class ModItemTagsProvider extends ItemTagsProvider {


    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, WildWindMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        IntrinsicTagAppender<Item> firefly_food = tag(ModItemTags.FIREFLY_FOOD);
        firefly_food.add(Items.GLOW_BERRIES, Items.GLOW_LICHEN);
        IntrinsicTagAppender<Item> meat_food = tag(ModItemTags.MEAT_FOOD);
        meat_food.add(
                Items.BEEF, Items.COOKED_BEEF,
                Items.PORKCHOP, Items.COOKED_PORKCHOP,
                Items.MUTTON, Items.COOKED_MUTTON,
                Items.RABBIT, Items.COOKED_RABBIT,
                Items.CHICKEN, Items.COOKED_CHICKEN
        );
        IntrinsicTagAppender<Item> vegetable_food = tag(ModItemTags.VEGETABLE_FOOD);
        add(vegetable_food,
                Items.CARROT, ModNonFunctionFoods.BAKED_CARROT.get(), Items.GOLDEN_CARROT,
                Items.BEETROOT, ModNonFunctionFoods.BAKED_BEETROOT.get(),
                Items.POTATO, Items.BAKED_POTATO,
                ModItems.PUMPKIN_SLICE, ModItems.BAKED_PUMPKIN_SLICE,
                Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, ModItems.BAKED_MUSHROOM,
                Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS,
                ModItems.LIVING_TUBER, ModNonFunctionFoods.BAKED_LIVING_TUBER.get()
        );
        IntrinsicTagAppender<Item> fruit_food = tag(ModItemTags.FRUIT_FOOD);
        add(fruit_food,
                Items.APPLE, ModItems.BAKED_APPLE, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE,
                Items.CHORUS_FRUIT, Items.POPPED_CHORUS_FRUIT,
                Items.MELON_SLICE, ModItems.BAKED_MELON_SLICE, Items.GLISTERING_MELON_SLICE,
                Items.SWEET_BERRIES, Items.GLOW_BERRIES,ModItems.BAKED_BERRIES,
                Items.SUGAR_CANE
        );
        IntrinsicTagAppender<Item> protein_food = tag(ModItemTags.PROTEIN_FOOD);
        add(protein_food,
                Items.EGG,
                Items.TURTLE_EGG,
                Items.SNIFFER_EGG,
                Items.DRAGON_EGG,
                ModItems.COOKED_EGG
        );
        IntrinsicTagAppender<Item> fish_food = tag(ModItemTags.FISH_FOOD);
        add(fish_food,
                Items.COD, Items.COOKED_COD,
                Items.SALMON, Items.COOKED_SALMON,
                ModNonFunctionFoods.RAW_TROUT.get(), ModNonFunctionFoods.COOKED_TROUT.get(),
                Items.KELP, Items.DRIED_KELP
        );
        IntrinsicTagAppender<Item> monster_food = tag(ModItemTags.MONSTER_FOOD);
        add(monster_food,
                Items.RABBIT_FOOT,
                Items.SPIDER_EYE, Items.FERMENTED_SPIDER_EYE,
                Items.TROPICAL_FISH, Items.PUFFERFISH,
                Items.ROTTEN_FLESH
        );


    }

    public static void add(IntrinsicTagAppender<Item> appender, ItemLike... likes) {
        appender.add(Arrays.stream(likes).map(ItemLike::asItem).toArray(Item[]::new));
    }


    protected IntrinsicTagAppender<Item> tag(Supplier<TagKey<Item>> tag) {
        return super.tag(tag.get());
    }
}
