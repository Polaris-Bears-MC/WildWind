package org.polaris2023.wild_wind.common.init;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.polaris2023.annotation.language.I18n;
import org.polaris2023.wild_wind.util.Helpers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

import static org.polaris2023.wild_wind.common.init.ModInitializer.SOUNDS;

public enum ModSounds implements Supplier<SoundEvent> {
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_1,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_2,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_3,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_4,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_5,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_6,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_7,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_8,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_9,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_10,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_11,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_12,
    @I18n(en_us = "glare ambient", zh_cn = "怒目怪叫声", zh_tw = "怒目靈叫聲")
    GLARE_AMBIENT_13,
    @I18n(en_us = "glare death", zh_cn = "怒目怪死亡", zh_tw = "怒目靈死亡")
    GLARE_DEATH_1,
    @I18n(en_us = "glare death", zh_cn = "怒目怪死亡", zh_tw = "怒目靈死亡")
    GLARE_DEATH_2,
    @I18n(en_us = "glare death", zh_cn = "怒目怪死亡", zh_tw = "怒目靈死亡")
    GLARE_DEATH_3,
    @I18n(en_us = "magic flute", zh_cn = "魔笛音", zh_tw = "魔笛音")
    MAGIC_FLUTE
    ;

    public static final Map<Integer, ModSounds> AMBIENT_S = new HashMap<>();
    public static final Map<Integer, ModSounds> DEATH_S = new HashMap<>();

    static {
        AMBIENT_S.put(1, GLARE_AMBIENT_1);
        AMBIENT_S.put(2, GLARE_AMBIENT_2);
        AMBIENT_S.put(3, GLARE_AMBIENT_3);
        AMBIENT_S.put(4, GLARE_AMBIENT_4);
        AMBIENT_S.put(5, GLARE_AMBIENT_5);
        AMBIENT_S.put(6, GLARE_AMBIENT_6);
        AMBIENT_S.put(7, GLARE_AMBIENT_7);
        AMBIENT_S.put(8, GLARE_AMBIENT_8);
        AMBIENT_S.put(9, GLARE_AMBIENT_9);
        AMBIENT_S.put(10, GLARE_AMBIENT_10);
        AMBIENT_S.put(11, GLARE_AMBIENT_11);
        AMBIENT_S.put(12, GLARE_AMBIENT_12);
        AMBIENT_S.put(13, GLARE_AMBIENT_13);
        DEATH_S.put(1, GLARE_DEATH_1);
        DEATH_S.put(2, GLARE_DEATH_2);
        DEATH_S.put(3, GLARE_DEATH_3);
    }

    private final DeferredHolder<SoundEvent, SoundEvent> holder;

    ModSounds(float range) {
        this.holder = registerFixedRange(name().toLowerCase(Locale.ROOT), range);
    }

    ModSounds() {
        this.holder = registerVariableRange(name().toLowerCase(Locale.ROOT));
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerVariableRange(String name) {
        return SOUNDS.register(name,() -> SoundEvent.createVariableRangeEvent(Helpers.location(name)));
    }


    private static DeferredHolder<SoundEvent,SoundEvent> register(String name, Supplier<SoundEvent> supplier){
        return SOUNDS.register(name, supplier);
    }

    private static DeferredHolder<SoundEvent, SoundEvent> registerFixedRange(String name, float range) {
        return SOUNDS.register(name, () -> SoundEvent.createFixedRangeEvent(Helpers.location(name), range));
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public SoundEvent get() {
        return holder.get();
    }

    public Holder<SoundEvent> getHolder() {
        return this.holder;
    }
}
