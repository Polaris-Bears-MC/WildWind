package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.WildWindMod;
import org.polaris2023.wild_wind.common.entity.Firefly;
import org.polaris2023.wild_wind.common.entity.Glare;
import org.polaris2023.wild_wind.common.entity.Trout;

import java.util.Collection;

public class ModEntities {

    static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, WildWindMod.MOD_ID);

    @I18n(en_us = "Firefly", zh_cn = "萤火虫", zh_tw = "螢火蟲")
    public static final DeferredHolder<EntityType<?>, EntityType<Firefly>> FIREFLY =
            register("firefly", Firefly::new, MobCategory.AMBIENT);
    @I18n(en_us = "Glare", zh_cn = "怒目怪", zh_tw = "怒目靈")
    public static final DeferredHolder<EntityType<?>, EntityType<Glare>> GLARE =
            register("glare", Glare::new, MobCategory.MONSTER);
    @I18n(en_us = "Glare", zh_cn = "鳟鱼", zh_tw = "鱒魚")
    public static final DeferredHolder<EntityType<?>, EntityType<Trout>> TROUT =
            register("trout", Trout::new, MobCategory.WATER_AMBIENT);

    private static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> register(String name, EntityType.EntityFactory<E> factory, MobCategory category) {
        return ENTITIES.register(name, resourceLocation -> EntityType.Builder.of(factory, category).build(name));
    }

    public static Collection<DeferredHolder<EntityType<?>, ? extends EntityType<?>>> entry() {
        return ENTITIES.getEntries();
    }

}
