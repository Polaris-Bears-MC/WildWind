package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModEntityDataAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Goat.class)
public abstract class GoatMixin extends Animal {


    protected GoatMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
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
