package org.polaris2023.wild_wind.common.init;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.animal.Animal;

public class ModEntityDataAccess {
    public static final EntityDataAccessor<Integer> MILKING_INTERVALS =
            SynchedEntityData.defineId(Animal.class, EntityDataSerializers.INT);

}
