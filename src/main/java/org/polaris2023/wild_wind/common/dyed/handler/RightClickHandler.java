package org.polaris2023.wild_wind.common.dyed.handler;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.commands.SetBlockCommand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Clearable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import org.polaris2023.wild_wind.common.dyed.DyedBlockMap;

import java.util.*;

public class RightClickHandler {

    private static final Set<Block> carpetBlock= new HashSet<>() ;
    static {
        carpetBlock.add(Blocks.BLACK_CARPET);
        carpetBlock.add(Blocks.BLUE_CARPET);
        carpetBlock.add(Blocks.BROWN_CARPET);
        carpetBlock.add(Blocks.CYAN_CARPET);
        carpetBlock.add(Blocks.GRAY_CARPET);
        carpetBlock.add(Blocks.GREEN_CARPET);
        carpetBlock.add(Blocks.LIGHT_BLUE_CARPET);
        carpetBlock.add(Blocks.LIGHT_GRAY_CARPET);
        carpetBlock.add(Blocks.LIME_CARPET);
        carpetBlock.add(Blocks.MAGENTA_CARPET);
        carpetBlock.add(Blocks.ORANGE_CARPET);
        carpetBlock.add(Blocks.PINK_CARPET);
        carpetBlock.add(Blocks.PURPLE_CARPET);
        carpetBlock.add(Blocks.RED_CARPET);
        carpetBlock.add(Blocks.WHITE_CARPET);
        carpetBlock.add(Blocks.YELLOW_CARPET);
    }
    private static final Set<Block> concreteBlock= new HashSet<>();
    static {
        concreteBlock.add(Blocks.BLACK_CONCRETE);
        concreteBlock.add(Blocks.BLUE_CONCRETE);
        concreteBlock.add(Blocks.BROWN_CONCRETE);
        concreteBlock.add(Blocks.CYAN_CONCRETE);
        concreteBlock.add(Blocks.GRAY_CONCRETE);
        concreteBlock.add(Blocks.GREEN_CONCRETE);
        concreteBlock.add(Blocks.LIGHT_BLUE_CONCRETE);
        concreteBlock.add(Blocks.LIGHT_GRAY_CONCRETE);
        concreteBlock.add(Blocks.LIME_CONCRETE);
        concreteBlock.add(Blocks.MAGENTA_CONCRETE);
        concreteBlock.add(Blocks.ORANGE_CONCRETE);
        concreteBlock.add(Blocks.PINK_CONCRETE);
        concreteBlock.add(Blocks.PURPLE_CONCRETE);
        concreteBlock.add(Blocks.RED_CONCRETE);
        concreteBlock.add(Blocks.WHITE_CONCRETE);
        concreteBlock.add(Blocks.YELLOW_CONCRETE);
    }
    public static final Set<Block> concrete_powderBlock= new HashSet<>();
    static {
        concrete_powderBlock.add(Blocks.BLACK_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.BLUE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.BROWN_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.CYAN_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.GRAY_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.GREEN_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.LIME_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.MAGENTA_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.ORANGE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.PINK_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.PURPLE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.RED_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.WHITE_CONCRETE_POWDER);
        concrete_powderBlock.add(Blocks.YELLOW_CONCRETE_POWDER);
    }
    public static final Set<Block> bannerBlock= new HashSet<>();
    static {
        bannerBlock.add(Blocks.BLACK_BANNER);
        bannerBlock.add(Blocks.BLUE_BANNER);
        bannerBlock.add(Blocks.BROWN_BANNER);
        bannerBlock.add(Blocks.CYAN_BANNER);
        bannerBlock.add(Blocks.GRAY_BANNER);
        bannerBlock.add(Blocks.GREEN_BANNER);
        bannerBlock.add(Blocks.LIGHT_BLUE_BANNER);
        bannerBlock.add(Blocks.LIGHT_GRAY_BANNER);
        bannerBlock.add(Blocks.LIME_BANNER);
        bannerBlock.add(Blocks.MAGENTA_BANNER);
        bannerBlock.add(Blocks.ORANGE_BANNER);
        bannerBlock.add(Blocks.PINK_BANNER);
        bannerBlock.add(Blocks.PURPLE_BANNER);
        bannerBlock.add(Blocks.RED_BANNER);
        bannerBlock.add(Blocks.WHITE_BANNER);
        bannerBlock.add(Blocks.YELLOW_BANNER);
    }

    public static void rightClick(Player player, Level level, ItemStack itemStack, BlockPos pos, BlockState blockState, PlayerInteractEvent.RightClickBlock event) {
        DyedBlockMap dyedBlockMap = new DyedBlockMap();
        Map<DyeColor, Block> dyedBlock = switch (blockType(blockState)) {
            case "WOOL" -> dyedBlockMap.getDyedBlock("WOOL");
            case "CARPET" -> dyedBlockMap.getDyedBlock("CARPET");
            case "BED" -> dyedBlockMap.getDyedBlock("BED");
            case "TERRACOTTA" -> dyedBlockMap.getDyedBlock("TERRACOTTA");
            case "CONCRETE" -> dyedBlockMap.getDyedBlock("CONCRETE");
            case "CONCRETE_POWDER" -> dyedBlockMap.getDyedBlock("CONCRETE_POWDER");
            case "GLAZED_TERRACOTTA" -> dyedBlockMap.getDyedBlock("GLAZED_TERRACOTTA");
            case "GLASS" -> dyedBlockMap.getDyedBlock("GLASS");
            case "GLASS_PANE" -> dyedBlockMap.getDyedBlock("GLASS_PANE");
            case "SHULKER_BOX" -> dyedBlockMap.getDyedBlock("SHULKER_BOX");
            case "CANDLE" -> dyedBlockMap.getDyedBlock("CANDLE");
            case "BANNER" -> dyedBlockMap.getDyedBlock("BANNER");
            default -> null;
        };

        if(dyedBlock != null){
            DyeColor dyeColor = DyeColor.getColor(itemStack);
            Block dyedBlockInstance = dyedBlock.get(dyeColor);
            if (dyedBlockInstance != null & dyeColor != null) {
                BlockState newBlockState = dyedBlockInstance.withPropertiesOf(blockState);

                if (dyedBlock == dyedBlockMap.getDyedBlock("BED")) {

                    BedBlockEntity bedEntity = (BedBlockEntity) level.getBlockEntity(pos);
                    if (bedEntity != null && DyeColor.getColor(itemStack) != bedEntity.getColor()) {
                        Direction direction = bedEntity.getBlockState().getValue(BedBlock.FACING);
                        BedPart value = bedEntity.getBlockState().getValue(BedBlock.PART);
                        BlockPos otherPos = pos.offset(
                                value.equals(BedPart.HEAD) ? direction.getOpposite().getNormal() : direction.getNormal()
                        );
                        BlockState otherBlockState = level.getBlockState(otherPos);
                        BlockState newOtherBlockState = dyedBlockInstance.withPropertiesOf(otherBlockState);
                        BedBlockEntity bedOtherEntity = (BedBlockEntity) level.getBlockEntity(otherPos);
                        handleDyedBed(
                                level,
                                player,
                                itemStack,
                                blockState,
                                pos,
                                newBlockState,
                                bedEntity,
                                otherBlockState,
                                otherPos,
                                newOtherBlockState,
                                bedOtherEntity
                                );


                    }
//                    if (bedEntity != null && DyeColor.getColor(itemStack) != bedEntity.getColor()) {
//                        System.out.println(bedEntity.getColor());
//                        handleDyedBed(player, itemStack, blockState, level, pos, newBlockState);
//                        System.out.println(bedEntity.getColor());
//                        event.setCanceled(true);
//                        player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()), 1);
//                        level.playSound(null, pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
//                    }
                    return;

                } else if (dyedBlock == dyedBlockMap.getDyedBlock("SHULKER_BOX")) {
                    if (level.getBlockState(pos).getBlock() != dyedBlock.get(dyeColor)) {
                        if (player.isCrouching()){
                            handleDyedShulkerBox(level, pos, newBlockState);
                            if(!player.isCreative()){
                                itemStack.shrink(1);
                            }
                            event.setCanceled(true);
                            player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()),1);
                            level.playSound(null,pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
                        }
                    }
                } else {
                    if (level.getBlockState(pos).getBlock() != dyedBlock.get(dyeColor)) {
                        level.setBlockAndUpdate(pos,newBlockState);
                        if(!player.isCreative()){
                            itemStack.shrink(1);
                        }
                    }
                    event.setCanceled(true);
                    player.awardStat(Stats.ITEM_USED.get(itemStack.getItem()),1);
                    level.playSound(null,pos, SoundEvents.DYE_USE, SoundSource.NEUTRAL);
                }
            }
        }

    }

    private static void handleDyedBed(
            Level level,
            Player player,
            ItemStack itemStack,
            BlockState blockState,
            BlockPos pos,
            BlockState newState,
            BedBlockEntity bedEntity,
            BlockState otherBlockState,
            BlockPos otherPos,
            BlockState newOtherState,
            BedBlockEntity bedOtherEntity){
        Clearable.tryClear(bedEntity);
        Clearable.tryClear(bedOtherEntity);




//        newBedBlock.setPlacedBy(level, pos, newBlockStateProperties, player, Items.BLUE_BED.getDefaultInstance());
//        ItemStack newBedItem = blockState.getBlock().getCloneItemStack(level, pos, blockState);


    }

    private static void handleDyedShulkerBox(Level level, BlockPos pos, BlockState newBlockStateProperties) {
        ShulkerBoxBlockEntity shulkerBoxEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);
        List<ItemStack> items = new ArrayList<>();
        // 获取 HolderLookup.Provider
        HolderLookup.Provider provider = level.registryAccess();
        for (int i = 0; i < shulkerBoxEntity.getContainerSize(); i++) {
//            System.out.println(shulkerBoxEntity.getItem(i));
            items.add(shulkerBoxEntity.getItem(i));
//            System.out.println(items);
        }
        CompoundTag oldTag = shulkerBoxEntity.saveWithFullMetadata(provider);
//
//        System.out.println(oldTag.getString("CustomName"));
//        System.out.println(oldTag);
//
//        System.out.println("---------------");
        level.setBlockAndUpdate(pos, newBlockStateProperties);
        ShulkerBoxBlockEntity newShulkerBoxEntity = (ShulkerBoxBlockEntity) level.getBlockEntity(pos);

//        newShulkerBoxEntity.setCustomName();

        for (int i = 0; i < items.size(); i++) {
            newShulkerBoxEntity.setItem(i, items.get(i));
        }

    }


    //判断方块类型
    public static String blockType(BlockState blockState) {
        if (blockState.is(BlockTags.WOOL)){
            return "WOOL";
        }
        if (carpetBlock.contains(blockState.getBlock())){
            return "CARPET";
        }
        if (blockState.getBlock() instanceof BedBlock){
            return "BED";
        }
        if (blockState.is(BlockTags.TERRACOTTA)){
            return "TERRACOTTA";
        }
        if (concreteBlock.contains(blockState.getBlock())){
            return "CONCRETE";
        }
        if (concrete_powderBlock.contains(blockState.getBlock())){
            return "CONCRETE_POWDER";
        }
        if (blockState.is(Tags.Blocks.GLAZED_TERRACOTTAS)){
            return "GLAZED_TERRACOTTA";
        }
        if (blockState.is(Tags.Blocks.GLASS_BLOCKS)){
            return "GLASS";
        }
        if (blockState.is(Tags.Blocks.GLASS_PANES)){
            return "GLASS_PANE";
        }
        if (blockState.is(BlockTags.SHULKER_BOXES)){
            return "SHULKER_BOX";
        }
        if (blockState.is(BlockTags.CANDLES)){
            return "CANDLE";
        }
        if (bannerBlock.contains(blockState.getBlock())){
            return "BANNER";
        }



        return "pass";
    }
}