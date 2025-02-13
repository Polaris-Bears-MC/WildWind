package org.polaris2023.wild_wind.common.init.items.entity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.registries.DeferredItem;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.annotation.modelgen.item.BasicItem;
import org.polaris2023.wild_wind.common.init.ModEntities;
import org.polaris2023.wild_wind.common.init.ModInitializer;

import java.util.Locale;
import java.util.function.Supplier;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 21:17:12}
 */
@BasicItem
public enum ModMobBuckets implements Supplier<MobBucketItem> {
    @I18n(en_us = "Trout Bucket", zh_cn = "鳟鱼桶", zh_tw = "鱒魚桶")
    TROUT_BUCKET(ModEntities.TROUT,  () -> Fluids.WATER),
    @I18n(en_us = "Piranha Bucket", zh_cn = "食人鲳桶", zh_tw = "食人魚桶")
    PIRANHA_BUCKET(ModEntities.PIRANHA,  () -> Fluids.WATER),
    ;

    public final DeferredItem<MobBucketItem> entry;

    <T extends EntityType<?>> ModMobBuckets(Supplier<T> type, Supplier<Fluid> content) {
        this.entry = ModInitializer.register(name().toLowerCase(Locale.ROOT), () -> new MobBucketItem(
                type.get(),
                content.get(),
                SoundEvents.BUCKET_EMPTY_FISH,
                new Item.Properties()
                        .stacksTo(1)
                        .component(DataComponents.BUCKET_ENTITY_DATA, CustomData.EMPTY)));
    }

    public static void init() {}

    @Override
    public MobBucketItem get() {
        return entry.get();
    }
}
