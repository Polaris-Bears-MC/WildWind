package org.polaris2023.wild_wind.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModEntityDataAccess;
import org.polaris2023.wild_wind.util.interfaces.ISelf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Goat.class)
public abstract class GoatMixin extends Animal implements ISelf<Goat> {
    protected GoatMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "defineSynchedData", at = @At("RETURN"))
    private void data(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(ModEntityDataAccess.MILKING_INTERVALS, 0);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    private void put(CompoundTag compound, CallbackInfo ci) {
        compound.putInt("milking_intervals", entityData.get(ModEntityDataAccess.MILKING_INTERVALS));
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    private void get(CompoundTag compound, CallbackInfo ci) {
        entityData.set(ModEntityDataAccess.MILKING_INTERVALS, compound.getInt("milking_intervals"));
    }

    @Inject(method = "mobInteract",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"
            ), cancellable = true)
    private void interact(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (entityData.get(ModEntityDataAccess.MILKING_INTERVALS) > 0) {
            cir.setReturnValue(super.mobInteract(player, hand));
            cir.cancel();
        }
        entityData.set(ModEntityDataAccess.MILKING_INTERVALS, 6000);
    }
}