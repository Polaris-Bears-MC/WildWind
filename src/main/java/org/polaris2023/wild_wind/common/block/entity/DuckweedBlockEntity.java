package org.polaris2023.wild_wind.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.polaris2023.wild_wind.common.init.ModBlocks;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 15:45:31}
 */
public class DuckweedBlockEntity extends BlockEntity {
    public DuckweedBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlocks.DUCKWEED_TILE.get(), pos, blockState);
    }

    public static void serverTick(Level level,
                                  BlockPos pos,
                                  BlockState state,
                                  DuckweedBlockEntity blockEntity) {

    }
}
