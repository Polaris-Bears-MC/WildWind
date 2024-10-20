package org.polaris_bear.wild_wind.common.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.polaris_bear.wild_wind.util.Helpers;

public class ModBlockTags {

    public static final TagKey<Block> FIREFLY_ROOST_BLOCK = register("firefly_roost_block");

    private static TagKey<Block> register(String name) {
        return BlockTags.create(Helpers.location(name));
    }
}
