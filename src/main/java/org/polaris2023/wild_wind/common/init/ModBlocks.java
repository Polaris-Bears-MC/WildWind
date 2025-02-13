package org.polaris2023.wild_wind.common.init;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.types.Type;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;

import org.polaris2023.annotation.modelgen.item.BasicBlockItem;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.block.*;

import org.polaris2023.wild_wind.common.block.entity.CookingPotBlockEntity;
import org.polaris2023.wild_wind.common.block.entity.DuckweedBlockEntity;

import java.util.Arrays;

import static org.polaris2023.wild_wind.common.init.ModInitializer.TILES;
import static org.polaris2023.wild_wind.common.init.ModInitializer.register;


public class ModBlocks {


    @I18n(en_us = "Glow Mucus", zh_cn = "萤光黏液", zh_tw = "螢光黏液")
    public static final DeferredBlock<GlowMucusBlock> GLOW_MUCUS = register("glow_mucus", GlowMucusBlock::new, BlockBehaviour.Properties.of());
    @BasicItem
    public static final DeferredItem<BlockItem> GLOW_MUCUS_ITEM =
            register("glow_mucus", GLOW_MUCUS);

    @I18n(en_us = "Firefly Jar", zh_cn = "萤火虫瓶", zh_tw = "螢火蟲瓶")
    public static final DeferredBlock<Block> FIREFLY_JAR = register("firefly_jar", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Glare Flower", zh_cn = "怒目花", zh_tw = "怒目花")
    public static final DeferredBlock<Block> GLAREFLOWER = register("glareflower");
    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER_ITEM =
            register("glareflower", GLAREFLOWER);

    @I18n(en_us = "Glare Flower Seeds", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> GLAREFLOWER_SEEDS = register("glareflower_seeds");
    @BasicItem
    public static final DeferredItem<BlockItem> GLAREFLOWER_SEEDS_ITEM =
            register("glareflower_seeds", GLAREFLOWER_SEEDS, ModFoods.GLAREFLOWER_SEEDS);

    @I18n(en_us = "Spider Egg", zh_cn = "怒目花种子", zh_tw = "怒目花種子")
    public static final DeferredBlock<Block> SPIDER_EGG = register("spider_egg", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Spider Attachments", zh_cn = "蛛丝附层", zh_tw = "蛛絲附層")
    public static final DeferredBlock<Block> SPIDER_ATTACHMENTS = register("spider_attachments", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Spider Mucosa", zh_cn = "蛛丝壁膜", zh_tw = "蛛絲壁膜")
    public static final DeferredBlock<Block> SPIDER_MUCOSA = register("spider_mucosa", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Reeds", zh_cn = "芦苇", zh_tw = "蘆葦")
    public static final DeferredBlock<Block> REEDS = register("reeds");
    @BasicItem
    public static final DeferredItem<BlockItem> REEDS_ITEM =
            register("reeds", REEDS);

    @I18n(en_us = "Cattails", zh_cn = "香蒲", zh_tw = "水燭")
    public static final DeferredBlock<Block> CATTAILS = register("cattails");
    @BasicItem
    public static final DeferredItem<BlockItem> CATTAILS_ITEM =
            register("cattails", CATTAILS);

    @I18n(en_us = "Present", zh_cn = "礼物盒", zh_tw = "禮物盒")
    public static final DeferredBlock<Block> PRESENT = register("present", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Trapped Present", zh_cn = "陷阱礼物盒", zh_tw = "陷阱禮物盒")
    public static final DeferredBlock<Block> TRAPPED_PRESENT =
            register("trapped_present", BlockBehaviour.Properties.of().noLootTable());

    @I18n(en_us = "Cooking Pot", zh_cn = "烹饪锅", zh_tw = "烹饪鍋具")
    public static final DeferredBlock<CookingPotBlock> COOKING_POT =
            register("cooking_pot", CookingPotBlock::new, BlockBehaviour.Properties.of().strength(2.0F, 6.0F));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CookingPotBlockEntity>> COOKING_POT_TILE =
            entity("cooking_pot", DSL.remainderType(), CookingPotBlockEntity::new, COOKING_POT);
    public static final DeferredItem<BlockItem> COOKING_POT_ITEM =
            register("cooking_pot", COOKING_POT);

    @I18n(en_us = "Duckweed", zh_cn = "浮萍", zh_tw = "浮萍")
    public static final DeferredBlock<DuckweedBlock> DUCKWEED =
            register("duckweed", DuckweedBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DuckweedBlockEntity>> DUCKWEED_TILE =
            entity("duckweed", DSL.remainderType(), DuckweedBlockEntity::new, DUCKWEED);
    public static final DeferredItem<BlockItem> DUCKWEED_ITEM =
            register("duckweed", DUCKWEED);

    @I18n(en_us = "Sculk Jaw", zh_cn = "幽匿厄口", zh_tw = "幽匿厄口")
    public static final DeferredBlock<SculkJawBlock> SCULK_JAW =
            register("sculk_jaw", SculkJawBlock::new, BlockBehaviour.Properties.of());
    public static final DeferredItem<BlockItem> SCULK_JAW_ITEM =
            register("sculk_jaw", SCULK_JAW);


    @I18n(en_us = "Brittle Ice", zh_cn = "脆冰", zh_tw = "脆冰")
    public static final DeferredBlock<BrittleIceBlock> BRITTLE_ICE =
            register("brittle_ice", BrittleIceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)
                    .strength(0.1F).isValidSpawn(Blocks::never).pushReaction(PushReaction.DESTROY));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> BRITTLE_ICE_ITEM =
            register("brittle_ice", BRITTLE_ICE);

    @I18n(en_us = "wood", zh_cn = "羊毛", zh_tw = "羊毛")
    public static final DeferredBlock<Block> WOOD = register("wood", BlockBehaviour.Properties.of()
            .instrument(NoteBlockInstrument.GUITAR)
            .strength(0.8F)
            .sound(SoundType.WOOL)
            .ignitedByLava());
    @BasicBlockItem
    public static final DeferredItem<BlockItem> WOOL_ITEM = register("wood", WOOD);

    @I18n(en_us = "carpet", zh_cn = "地毯", zh_tw = "地毯")
    public static final DeferredBlock<CarpetBlock> CARPET =
            register("carpet", CarpetBlock::new, BlockBehaviour.Properties.of().strength(0.1F).sound(SoundType.WOOL).ignitedByLava() );
    @BasicBlockItem
    public static final DeferredItem<BlockItem> CARPET_ITEM = register("carpet", CARPET);

    @I18n(en_us = "Concrete", zh_cn = "混凝土", zh_tw = "混凝土")
    public static final DeferredBlock<Block> CONCRETE =
            register("concrete", BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.STONE).ignitedByLava());
    @BasicBlockItem
    public static final DeferredItem<BlockItem> CONCRETE_ITEM = register("concrete", CONCRETE);
    @I18n(en_us ="Glazed Terracotta", zh_cn = "釉陶瓦", zh_tw = "釉陶瓦")
    public static final DeferredBlock<GlazedTerracottaBlock> GLAZED_TERRACOTTA =
            register("glazed_terracotta",  GlazedTerracottaBlock::new, BlockBehaviour.Properties.of().strength(1.25F).sound(SoundType.STONE).ignitedByLava());
    @BasicBlockItem
    public static final DeferredItem<BlockItem> GLAZED_TERRACOTTA_ITEM = register("glazed_terracotta", GLAZED_TERRACOTTA);

    @I18n(en_us = "Salt Block", zh_cn = "盐块", zh_tw = "鹽塊")
    public static final DeferredBlock<Block> SALT_BLOCK =
            register("salt_block", Block::new, BlockBehaviour.Properties.of()
                    .strength(3F)
                    .requiresCorrectToolForDrops()
                    .isRedstoneConductor((_0, _1, _2) -> true));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> SALT_BLOCK_ITEM =
            register("salt_block", SALT_BLOCK);
    @I18n(en_us = "Salt Ore", zh_cn = "盐矿石", zh_tw = "鹽礦石")
    public static final DeferredBlock<Block> SALT_ORE =
            register("salt_ore", p -> new DropExperienceBlock(UniformInt.of(2, 5), p), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(3));
    @BasicBlockItem
    public static final DeferredItem<BlockItem> SALT_ORE_ITEM =
            register("salt_ore", SALT_ORE);

    @I18n(en_us = "Deepslate Salt Ore", zh_cn = "深层盐矿石", zh_tw = "深層鹽礦石")
    public static final DeferredBlock<DropExperienceBlock> DEEPSLATE_SALT_ORE =
            register("deepslate_salt_ore",
                    properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties), BlockBehaviour.Properties.of()
                            .requiresCorrectToolForDrops()
                            .instrument(NoteBlockInstrument.BASEDRUM)
                            .strength(4.5F, 3)
                            .sound(SoundType.DEEPSLATE)
                            .mapColor(MapColor.DEEPSLATE));

    @BasicBlockItem
    public static final DeferredItem<BlockItem> DEEPSLATE_SALT_ORE_ITEM =
            register("deepslate_salt_ore", DEEPSLATE_SALT_ORE);





    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>>
    entity(String name,
             Type<?> type,
             BlockEntityType.BlockEntitySupplier<T> factory,
             DeferredBlock<?>... blocks) {
        return TILES.register(name, () -> BlockEntityType.Builder.of(factory, Arrays.stream(blocks).map(DeferredBlock::get).toArray(Block[]::new)).build(type));
    }

}
