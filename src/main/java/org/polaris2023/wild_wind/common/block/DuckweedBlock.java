package org.polaris2023.wild_wind.common.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.common.block.entity.DuckweedBlockEntity;
import org.polaris2023.wild_wind.common.init.ModBlocks;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 15:29:50}
 */
public class DuckweedBlock extends BaseEntityBlock {
    public static final MapCodec<DuckweedBlock> CODEC = simpleCodec(DuckweedBlock::new);


    public DuckweedBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return ModBlocks.DUCKWEED_TILE.get().create(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : createTickerHelper(type, ModBlocks.DUCKWEED_TILE.get(),
                DuckweedBlockEntity::serverTick);
    }
}
