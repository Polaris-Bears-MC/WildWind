package org.polaris2023.wild_wind.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * @author : baka4n
 * {@code @Date : 2025/02/12 20:53:02}
 */
public class CheeseItem extends Item {
    public CheeseItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        super.finishUsingItem(itemStack, level, entity);
        List<MobEffectInstance> activeEffects = List.copyOf(entity.getActiveEffects());
        activeEffects.stream().filter(mobEffectInstance -> !mobEffectInstance.getEffect().value().isBeneficial())
                .forEach(mobEffectInstance -> entity.removeEffect(mobEffectInstance.getEffect()));
        return itemStack;
    }
}
