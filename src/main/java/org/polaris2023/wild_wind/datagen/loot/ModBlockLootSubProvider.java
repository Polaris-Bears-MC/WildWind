package org.polaris2023.wild_wind.datagen.loot;

import com.google.common.collect.ImmutableSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.polaris2023.wild_wind.common.init.ModBlocks;
import org.polaris2023.wild_wind.common.init.ModInitializer;
import org.polaris2023.wild_wind.common.init.ModItems;

import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = ImmutableSet.of();

    public ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.DEFAULT_FLAGS, registries);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModInitializer.blocks().stream().map(holder -> (Block)holder.get()).toList();
    }

    @Override
    public void generate() {
        this.dropSelf(ModBlocks.GLOW_MUCUS.get());
        this.dropSelf(ModBlocks.GLAREFLOWER.get());
        this.dropSelf(ModBlocks.GLAREFLOWER_SEEDS.get());
        this.dropSelf(ModBlocks.REEDS.get());
        this.dropSelf(ModBlocks.CATTAILS.get());
        this.dropSelf(ModBlocks.COOKING_POT.get());
        this.dropWhenSilkTouch(ModBlocks.BRITTLE_ICE.get());
        this.dropSelf(ModBlocks.WOOD.get());
        this.dropSelf(ModBlocks.CARPET.get());
        this.dropSelf(ModBlocks.CONCRETE.get());
        this.dropSelf(ModBlocks.GLAZED_TERRACOTTA.get());
        this.dropSelf(ModBlocks.SALT_BLOCK.get());
        this.dropOther(ModBlocks.SALT_ORE.get(), ModItems.SALT.get());
        this.dropOther(ModBlocks.DEEPSLATE_SALT_ORE.get(), ModItems.SALT.get());
        this.dropSelf(ModBlocks.DUCKWEED.get());
        this.dropSelf(ModBlocks.SCULK_JAW.get());
    }
}
