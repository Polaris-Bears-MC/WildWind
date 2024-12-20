package org.polaris2023.wild_wind.common.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.init.ModItems;
import org.polaris2023.wild_wind.common.init.ModSounds;

public class LivingTuberItem extends Item {
    public LivingTuberItem(Properties properties) {
        super(properties);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (level.getGameTime() % 200 == 0) {
            int i = entity.getRandom().nextInt(1, 13);

            ModSounds sounds = ModSounds.AMBIENT_S.getOrDefault(i, ModSounds.GLARE_AMBIENT_1);
            System.out.println(sounds.name());
            level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), sounds.get(), SoundSource.HOSTILE, 1F, 1F, true);

        }
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        RandomSource random = itemEntity.getRandom();
        int i = random.nextInt(1, 3);
        ModSounds sounds = ModSounds.DEATH_S.getOrDefault(i, ModSounds.GLARE_DEATH_1);
        itemEntity.playSound(sounds.get());
        super.onDestroyed(itemEntity, damageSource);
    }

    @Override
    public SoundEvent getEatingSound() {
        return ModSounds.GLARE_AMBIENT_9.get();
    }
}
