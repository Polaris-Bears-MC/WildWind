package org.polaris2023.wild_wind.mixin;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.FoodOnAStickItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.common.init.ModFoods;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 21:33:57}
 */
@Mixin(EggItem.class)
@Debug(export = true)
public abstract class EggMixin {

    @Shadow public abstract InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand);

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void use(Level level,
                     Player player,
                     InteractionHand hand,
                     CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack itemstack = player.getItemInHand(hand);
        FoodProperties foodproperties = itemstack.getFoodProperties(player);
        if (foodproperties != null) {
            if (player.canEat(foodproperties.canAlwaysEat())) {
                player.startUsingItem(hand);
                cir.setReturnValue(InteractionResultHolder.consume(itemstack));
            } else {
                cir.setReturnValue(InteractionResultHolder.fail(itemstack));
            }
        } else {
            cir.setReturnValue(InteractionResultHolder.pass(player.getItemInHand(hand)));
        }
        cir.cancel();
    }
}
