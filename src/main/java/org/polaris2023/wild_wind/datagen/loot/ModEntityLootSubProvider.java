package org.polaris2023.wild_wind.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModInitializer;
import org.polaris2023.wild_wind.common.init.items.ModNonFunctionItems;
import org.polaris2023.wild_wind.common.init.items.foods.ModNonFunctionFoods;

import java.util.stream.Stream;

public class ModEntityLootSubProvider extends EntityLootSubProvider {


    public ModEntityLootSubProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return ModInitializer.entities().stream().map(DeferredHolder::value);
    }

    @Override
    public void generate() {
        add(ModEntities.FIREFLY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(ModNonFunctionItems.GLOW_POWDER.get()))
                        .apply(SetItemCountFunction
                                .setCount(UniformGenerator.between(0.0f, 1.0f)))
                        .apply(EnchantedCountIncreaseFunction
                                .lootingMultiplier(registries, ConstantValue.exactly(1.0f))
                                .setLimit(3))
                ));
        add(ModEntities.GLARE.get(), LootTable.lootTable());
        add(ModEntities.TROUT.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ModNonFunctionFoods.RAW_TROUT.get())
                                .apply(SmeltItemFunction.smelted().when(this.shouldSmeltLoot())))));
        add(ModEntities.PIRANHA.get(), LootTable.lootTable());
    }
}
