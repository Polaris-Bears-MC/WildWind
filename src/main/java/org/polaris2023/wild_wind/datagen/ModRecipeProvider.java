package org.polaris2023.wild_wind.datagen;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.items.ModNonFunctionItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModNonFunctionFoods;
import org.polaris2023.wild_wind.datagen.custom.recipe.CookingPotRecipeBuilder;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;


public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public final Map<ResourceLocation, RecipeBuilder> list = new HashMap<>();


    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        addSmeltingRecipes();
        addShapedRecipe();
        addShapelessRecipe();
        addCookingPotRecipes();
        list.forEach((s, b) -> {
            b.save(recipeOutput, s);
        });
    }

    protected void addCookingPotRecipes() {

    }

    public void simpleCookingPot(RecipeCategory category, ItemLike result, FluidStack stack, Consumer<CookingPotRecipeBuilder> consumer) {
        CookingPotRecipeBuilder cooking = CookingPotRecipeBuilder
                .cooking(category, result);
        consumer.accept(cooking);
        add(
                "cooking_pot/",
                cooking
                        .stack(stack));
    }

    protected void addSmeltingRecipes() {
        smeltingSmokingAndCampfire(ModNonFunctionFoods.RAW_TROUT.get(), RecipeCategory.FOOD, ModNonFunctionFoods.COOKED_TROUT.get(), 0.35F);
        smeltingSmokingAndCampfire(ModItems.LIVING_TUBER, RecipeCategory.FOOD, ModNonFunctionFoods.BAKED_LIVING_TUBER.get(), 0.35F);
        smeltingSmokingAndCampfire(ModItems.DOUGH, RecipeCategory.FOOD, Items.BREAD, 0.35F);// input category result exp
        smeltingSmokingAndCampfire(Items.CARROT, RecipeCategory.FOOD, ModNonFunctionFoods.BAKED_CARROT.get(), 0.35F);
        smeltingSmokingAndCampfire(Items.BEETROOT, RecipeCategory.FOOD, ModNonFunctionFoods.BAKED_BEETROOT.get(), 0.35F);
        smeltingSmokingAndCampfire(Ingredient.of(Items.EGG, Items.TURTLE_EGG), RecipeCategory.FOOD, ModItems.COOKED_EGG, 0.35F);

        SimpleCookingRecipeBuilder smelting = smelting(Items.TERRACOTTA, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GLAZED_TERRACOTTA.get(),0.35F);
        add(smelting);


    }

    public static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike... likes) {
        return inventoryTrigger(ItemPredicate.Builder
                .item()
                .of(likes).build());
    }

    protected void addShapedRecipe() {
        add(shaped(RecipeCategory.BUILDING_BLOCKS, Items.ICE, 1, builder -> {
            unlockedBy(builder, ModBlocks.BRITTLE_ICE_ITEM);
            builder
                    .pattern("III")
                    .pattern("III")
                    .pattern("III")
                    .group("ice")
                    .define('I', ModBlocks.BRITTLE_ICE_ITEM);
        }), Helpers.location("ice_from_brittle_ice"));
        add(shaped(RecipeCategory.MISC, ModItems.MAGIC_FLUTE, 1, builder -> {
            unlockedBy(builder, Items.BONE);
            unlockedBy(builder, ModItems.LIVING_TUBER);
            builder
                    .pattern("BRB")
                    .group("magic_flute")
                    .define('B', Items.BONE)
                    .define('R', ModItems.LIVING_TUBER);
        }));
        add(shaped(RecipeCategory.MISC, ModBlocks.COOKING_POT_ITEM.get(), 1,
                builder -> {
            unlockedBy(builder, Items.IRON_INGOT);
            unlockedBy(builder, ItemTags.LOGS);
            unlockedBy(builder, ItemTags.COALS);
            builder
                    .pattern("I I")
                    .pattern("III")
                    .pattern("PCP")
                    .group("cooking_pot")
                    .define('I', Items.IRON_INGOT)
                    .define('P', ItemTags.LOGS)
                    .define('C', ItemTags.COALS);
        }));

        add(shaped(RecipeCategory.MISC, ModBlocks.WOOD.get(), 1,
                builder -> {
            unlockedBy(builder, Items.STRING);
            builder
                    .pattern("SS ")
                    .pattern("SS ")
                    .pattern("   ")
                    .group("wood")
                    .define('S', Items.STRING);
                }));
        add(shaped(RecipeCategory.MISC, ModBlocks.CARPET.get(), 1,
                builder -> {
            unlockedBy(builder, ModBlocks.WOOD.get());
            builder
                    .pattern("SS ")
                    .pattern("   ")
                    .pattern("   ")
                    .group("carpet")
                    .define('S', ModBlocks.WOOD.get());
                }));
    }

    protected static <T extends RecipeBuilder> void unlockedBy(T t, ItemLike... likes) {
        StringBuilder sb = new StringBuilder("has");
        switch (likes.length) {
            case 0 -> {
            }
            case 1 -> {
                ItemLike like = likes[0];
                t.unlockedBy(sb.append("_").append(BuiltInRegistries.ITEM.getKey(like.asItem())).toString().toLowerCase(Locale.ROOT), has(like));
            }
            default -> {
                for (ItemLike like : likes) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(like.asItem());
                    sb.append("_").append(key);
                }
                t.unlockedBy(sb.toString().toLowerCase(Locale.ROOT), has(likes));
            }
        }

    }

    protected static <T extends RecipeBuilder> void unlockedBy(T t, TagKey<Item> tag) {
        t.unlockedBy(("has" + "_" + tag.location()).toLowerCase(Locale.ROOT), has(tag));
    }



    protected void addShapelessRecipe() {

        add(shapeless(RecipeCategory.FOOD, ModItems.FISH_CHOWDER, 1, fish_chowder -> {
            unlockedBy(fish_chowder, ModNonFunctionFoods.RAW_TROUT.get(), Items.COD, Items.SALMON);
            unlockedBy(fish_chowder, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM);
            unlockedBy(fish_chowder, Items.KELP);
            unlockedBy(fish_chowder, Items.BOWL);
            fish_chowder
                    .requires(Ingredient.of(Items.COD, Items.SALMON, ModNonFunctionFoods.RAW_TROUT.get()))
                    .requires(Items.KELP)
                    .requires(Ingredient.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                    .requires(Items.BOWL);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.CHEESE, 1, cheese -> {
            unlockedBy(cheese,Items.BROWN_MUSHROOM);
            unlockedBy(cheese,Items.SUGAR);
            unlockedBy(cheese,Items.MILK_BUCKET);
            cheese
                    .requires(Items.BROWN_MUSHROOM)
                    .requires(Items.SUGAR)
                    .requires(Items.MILK_BUCKET);
        }));
        add(shapeless(RecipeCategory.FOOD, ModItems.RUSSIAN_SOUP, 1, russian_soup -> {
            unlockedBy(russian_soup, ModItems.CHEESE);
            unlockedBy(russian_soup, Items.BEETROOT);
            unlockedBy(russian_soup, Items.POTATO);
            unlockedBy(russian_soup, Items.BEEF);
            unlockedBy(russian_soup, Items.BOWL);
            russian_soup
                    .requires(Items.BEETROOT)
                    .requires(Items.POTATO)
                    .requires(Items.BEEF)
                    .requires(ModItems.CHEESE)
                    .requires(Items.BOWL);
        }));
        add(shapeless(RecipeCategory.FOOD, ModNonFunctionItems.CHEESE_PUMPKIN_SOUP.get(), 1, cheese_pumpkin_soup -> {
            unlockedBy(cheese_pumpkin_soup, ModItems.CHEESE);
            unlockedBy(cheese_pumpkin_soup, Items.PUMPKIN);
            unlockedBy(cheese_pumpkin_soup, Items.BOWL);
            cheese_pumpkin_soup
                    .requires(Items.PUMPKIN)
                    .requires(Items.BOWL)
                    .requires(ModItems.CHEESE);
        }));
        add(shapeless(RecipeCategory.MISC, ModItems.FLOUR, 1, flour -> {
            unlockedBy(flour, Items.WHEAT);
            flour
                    .requires(Items.WHEAT);
        }));
        add(shapeless(RecipeCategory.MISC, ModItems.DOUGH, 1, dough -> {
            unlockedBy(dough, ModItems.FLOUR);
            unlockedBy(dough, Items.WATER_BUCKET);
            dough
                    .requires(ModItems.FLOUR, 3)
                    .requires(Items.WATER_BUCKET);
        }));
        add(shapeless(RecipeCategory.FOOD, ModNonFunctionItems.CANDY.get(), 1, candy -> {
            unlockedBy(candy, Items.SUGAR);
            unlockedBy(candy, Items.HONEY_BOTTLE);
            unlockedBy(candy, Items.GLOW_BERRIES, Items.APPLE, Items.SWEET_BERRIES);
            candy
                    .requires(Items.HONEY_BOTTLE)
                    .requires(Items.SUGAR)
                    .requires(Ingredient
                            .of(
                                    new ItemStack(Items.GLOW_BERRIES),
                                    new ItemStack(Items.APPLE),
                                    new ItemStack(Items.SWEET_BERRIES, 2)));
        }));
        add(shapeless(RecipeCategory.FOOD, ModNonFunctionItems.BERRY_CAKE.get(), 1, berry_cake -> {
            unlockedBy(berry_cake, Items.SWEET_BERRIES);
            unlockedBy(berry_cake, Items.GLOW_BERRIES);
            unlockedBy(berry_cake, Items.SUGAR);
            unlockedBy(berry_cake, Items.EGG);
            berry_cake
                    .requires(Items.SWEET_BERRIES, 2)
                    .requires(Items.GLOW_BERRIES)
                    .requires(Items.SUGAR)
                    .requires(Items.EGG);
        }));
        add(shapeless(RecipeCategory.FOOD, ModNonFunctionItems.APPLE_CAKE.get(), 1, apple_cake -> {
            unlockedBy(apple_cake, Items.APPLE);
            unlockedBy(apple_cake, Items.SUGAR);
            unlockedBy(apple_cake, Items.EGG);
            apple_cake
                    .requires(Items.APPLE)
                    .requires(Items.SUGAR)
                    .requires(Items.EGG);
        }));
        add(shapeless(RecipeCategory.MISC, Items.STRING, 1, wool ->{
            unlockedBy(wool, ItemTags.WOOL);
            wool
                    .requires(ItemTags.WOOL);
        }));
        add(shapeless(RecipeCategory.MISC, ModBlocks.SALT_BLOCK_ITEM, 1, salt_block -> {
            unlockedBy(salt_block, ModItems.SALT);
            salt_block.requires(ModItems.SALT, 9);
        }));
        add(shapeless(RecipeCategory.MISC, ModItems.SALT, 9, salt -> {
            unlockedBy(salt, ModBlocks.SALT_BLOCK_ITEM);
            salt.requires(ModBlocks.SALT_BLOCK_ITEM);
        }));
    }

    public static ShapedRecipeBuilder shaped(
            RecipeCategory category, ItemLike result, int count, Consumer<ShapedRecipeBuilder> consumer
    ) {
        ShapedRecipeBuilder shaped = ShapedRecipeBuilder.shaped(category, result, count);
        consumer.accept(shaped);
        return shaped;
    }

    public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemStack result, Consumer<ShapelessRecipeBuilder> consumer) {
        ShapelessRecipeBuilder shapeless = ShapelessRecipeBuilder.shapeless(category, result);
        consumer.accept(shapeless);
        return shapeless;
    }

    public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count, Consumer<ShapelessRecipeBuilder> consumer) {
        ShapelessRecipeBuilder shapeless = ShapelessRecipeBuilder.shapeless(category, result, count);
        consumer.accept(shapeless);
        return shapeless;
    }

    public static SimpleCookingRecipeBuilder smelting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.smelting(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder smoking(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.smoking(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder campfire(
            Ingredient input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        ItemStack[] items = input.getItems();
        Item item = items[0].getItem();
        return SimpleCookingRecipeBuilder.campfireCooking(input, category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(item).toString(), has(item));
    }

    public static SimpleCookingRecipeBuilder campfire(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return  campfire(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder campfire(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        return SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public static SimpleCookingRecipeBuilder campfire(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return campfire(input, category, result, exp,200);
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime
    ) {
        return SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public static SimpleCookingRecipeBuilder smoking(
            ItemLike input, RecipeCategory category, ItemLike result, float exp, int cookingTime) {
        return SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), category, result, exp, cookingTime)
                .unlockedBy(BuiltInRegistries.ITEM.getKey(input.asItem()).toString(), has(input));
    }

    public void smeltingAndSmoking(Ingredient input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
    }

    public void smeltingSmokingAndCampfire(Ingredient input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
        add(campfire(input, category, result, exp), "campfire/");
    }



    public static SimpleCookingRecipeBuilder smelting(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return  smelting(input, category, result, exp, 200);
    }

    public static SimpleCookingRecipeBuilder smoking(
            Ingredient input, RecipeCategory category, ItemLike result, float exp
    ) {
        return  smoking(input, category, result, exp, 200);
    }

    public void smeltingAndSmoking(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
    }

    public void smeltingSmokingAndCampfire(ItemLike input, RecipeCategory category, ItemLike result, float exp) {
        add(smelting(input, category, result, exp));
        add(smoking(input, category, result, exp), "smoking/");
        add(campfire(input, category, result, exp), "campfire/");
    }

    public static SimpleCookingRecipeBuilder smelting(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smelting(input, category, result, exp,200);
    }

    public static SimpleCookingRecipeBuilder smoking(
            ItemLike input, RecipeCategory category, ItemLike result, float exp
    ) {
        return smoking(input, category, result, exp, 200);
    }

    public void add(RecipeBuilder builder) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()), builder);
    }
    public void add(RecipeBuilder builder, ResourceLocation name) {
        list.put(name, builder);
    }

    public void add(RecipeBuilder builder, String prePath) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()).withPrefix(prePath), builder);
    }

    public void add(RecipeBuilder builder, String prePath, String sufPath) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()).withPrefix(prePath).withSuffix(sufPath), builder);
    }

    public void add(String prePath,RecipeBuilder builder) {
        list.put(BuiltInRegistries.ITEM.getKey(builder.getResult()).withPrefix(prePath), builder);
    }
}