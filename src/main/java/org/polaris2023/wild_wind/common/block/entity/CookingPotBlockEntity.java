package org.polaris2023.wild_wind.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.polaris2023.wild_wind.client.ModTranslateKey;
import org.polaris2023.wild_wind.common.init.ModBlocks;

public class CookingPotBlockEntity extends BlockEntity implements MenuProvider {
    int i = 0;


    public CookingPotBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlocks.COOKING_POT_TILE.get(), pos, blockState);

    }

    @Override
    public Component getDisplayName() {
        return ModTranslateKey.COOKIN_POT.translatable();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return null;
    }

    public static void serverTick(Level level,
                                  BlockPos pos,
                                  BlockState blockState,
                                  CookingPotBlockEntity tile) {

    }
}
