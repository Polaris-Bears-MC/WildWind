package org.polaris2023.wild_wind.common.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.items.entity.ModMobBuckets;

public class Piranha extends AbstractSchoolingFish {
    public final AnimationState swim = new AnimationState();
    public final AnimationState struggle = new AnimationState();
    public final AnimationState attack = new AnimationState();
    public final AnimationState attack2 = new AnimationState();
    public Piranha(EntityType<? extends AbstractSchoolingFish> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AmbientCreature.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 8F)
                .add(Attributes.ATTACK_DAMAGE, 1F);
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return ModMobBuckets.PIRANHA_BUCKET.entry.toStack();
    }
}
