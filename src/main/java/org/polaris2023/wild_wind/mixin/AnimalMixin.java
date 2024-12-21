package org.polaris2023.wild_wind.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModEntityDataAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AgeableMob.class)
public abstract class AnimalMixin extends PathfinderMob {
    protected AnimalMixin(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private AgeableMob wild_wind$self() {
        return (AgeableMob) (Object) this;
    }

    @Inject(method = "defineSynchedData", at = @At("RETURN"))
    private void add(SynchedEntityData.Builder builder, CallbackInfo ci) {
        AgeableMob self = wild_wind$self();
        if (self instanceof Goat || self instanceof Cow) {
            builder.define(ModEntityDataAccess.MILKING_INTERVALS, 0);
        }


    }

    @Override
    public void tick() {
        super.tick();
        AgeableMob self = wild_wind$self();
        if (self instanceof Goat || self instanceof Cow) {
            int i = entityData.get(ModEntityDataAccess.MILKING_INTERVALS);
            if (i > 0) {
                entityData.set(ModEntityDataAccess.MILKING_INTERVALS, i - 1);
            }
        }

    }

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    private void put(CompoundTag compound, CallbackInfo ci) {
        AgeableMob self = wild_wind$self();
        if (self instanceof Goat || self instanceof Cow) {
            compound.putInt("milking_intervals", this.entityData.get(ModEntityDataAccess.MILKING_INTERVALS));
        }

    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    private void get(CompoundTag compound, CallbackInfo ci) {
        AgeableMob self = wild_wind$self();
        if (self instanceof Goat || self instanceof Cow) {
            this.entityData.set(ModEntityDataAccess.MILKING_INTERVALS, compound.getInt("milking_intervals"));
        }
    }
}
