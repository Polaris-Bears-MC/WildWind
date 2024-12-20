package org.polaris2023.utils;

public enum Codes {
    ModelProvider(
            """
            package %%package%%;
            
            import com.google.gson.Gson;
            import com.google.gson.GsonBuilder;
            import com.google.gson.JsonElement;
            
            import net.minecraft.core.registries.BuiltInRegistries;
            import net.minecraft.data.CachedOutput;
            import net.minecraft.data.DataProvider;
            import net.minecraft.data.PackOutput;
            import net.minecraft.resources.ResourceLocation;
            import net.minecraft.world.item.BlockItem;
            import net.minecraft.world.item.Item;
            import net.minecraft.world.item.SpawnEggItem;
            import net.minecraft.world.level.block.Block;
            import net.minecraft.world.level.block.SlabBlock;
            import net.minecraft.world.level.block.StairBlock;
            
            import net.neoforged.neoforge.registries.DeferredHolder;
            
            import org.polaris2023.wild_wind.common.init.ModInitializer;
            import org.polaris2023.wild_wind.util.interfaces.IModel;
            
            import java.nio.file.Path;
            import java.util.Map;
            import java.util.concurrent.CompletableFuture;
            import java.util.concurrent.ConcurrentHashMap;
            import java.util.function.Supplier;
            
            public final class %%classname%% implements DataProvider, IModel<%%classname%%> {
                private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();
                private static final Map<String, String> SPAWN_EGG = Map.of("parent", "minecraft:item/template_spawn_egg");
                private PackOutput output;
                private String modid;
                private Path assetsDir;
                private final ConcurrentHashMap<ResourceLocation, Object> MODELS =
                    new ConcurrentHashMap<>();// object is Bean or map， by gson
            
                private <T extends Item> %%classname%% basicItem(Supplier<T> item) {
                    ResourceLocation key = BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/");
                    MODELS.put(key, Map.of("parent", "minecraft:item/generated", "textures", Map.of(
                                        "layer0", key.toString()
                    )));
                    return this;
                }
            
                private <T extends SpawnEggItem> %%classname%% spawnEggItem(DeferredHolder<Item, T> item) {
                    MODELS.put(BuiltInRegistries.ITEM.getKey(item.get()).withPrefix("item/"), SPAWN_EGG);
                    return this;
                }
            
                @Override
                public void init() {
                    %%init%%;
                }
            
                @Override
                public %%classname%% setOutput(PackOutput output) {
                    this.output = output;
                    assetsDir = output.getOutputFolder(PackOutput.Target.RESOURCE_PACK);
                    return this;
                }
            
                @Override
                public CompletableFuture<?> run(CachedOutput output) {
                    init();
                    CompletableFuture<?>[] futures = new CompletableFuture[MODELS.size()];
                    int i = 0;
                    for (Map.Entry<ResourceLocation, Object> entry : MODELS.entrySet()) {
                        ResourceLocation key = entry.getKey();
                        Object object = entry.getValue();
                        Path itemModel = assetsDir.resolve(key.getNamespace()).resolve("models").resolve(key.getPath() + ".json");
                        JsonElement jsonTree = GSON.toJsonTree(object);
                        futures[i] = DataProvider.saveStable(output, jsonTree, itemModel);
                        i++;
                    }
                    return CompletableFuture.allOf(futures);
                }
            
                @Override
                public String getName() {
                    return "Model Provider by " + modid;
                }
            
                @Override
                public %%classname%% setModid(String modid) {
                    this.modid = modid;
                    return this;
                }
            }
            """.stripIndent()
    ),
    LanguageProvider("""
            package %%package%%;
            
            import com.google.gson.Gson;
            import java.lang.Object;
            import java.lang.String;
            import java.nio.file.Path;
            import java.util.concurrent.CompletableFuture;
            import java.util.concurrent.ConcurrentHashMap;
            import net.minecraft.data.CachedOutput;
            import net.minecraft.data.DataProvider;
            import net.minecraft.data.PackOutput;
            import org.polaris2023.wild_wind.util.interfaces.ILanguage;
            import net.neoforged.neoforge.registries.DeferredHolder;
            import java.util.function.Supplier;
            import net.minecraft.world.item.Item;
            import net.minecraft.world.level.block.Block;
            import net.minecraft.world.entity.EntityType;
            import net.minecraft.network.chat.contents.TranslatableContents;
            import net.minecraft.world.effect.MobEffect;
            import net.minecraft.world.item.CreativeModeTab;
            import net.minecraft.world.item.ItemStack;
            import net.minecraft.sounds.SoundEvent;
            
            public final class %%classname%% implements ILanguage<%%classname%%>, DataProvider {
                private static final Gson GSON = new com.google.gson.GsonBuilder().setLenient().setPrettyPrinting().create();
            
                private PackOutput output;
            
                private final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> languages = new ConcurrentHashMap<>();
            
                private String targetLanguage = targetLanguage = "en_us";
            
                private Path languageDir;
            
                private String modid;
            
                public %%classname%% setOutput(PackOutput output) {
                    this.output = output;
                    languageDir = output
                        .getOutputFolder(PackOutput.Target.RESOURCE_PACK)
                        .resolve(modid)
                        .resolve("lang");
                    return this;
                }
            
                public CompletableFuture<?> run(CachedOutput cachedOutput) {
                    init();
                    final CompletableFuture<?>[] futures = new CompletableFuture[languages.size()];
                    int i = 0;
                    for (var entry : languages.entrySet()) {
                        String lang = entry.getKey();
                        ConcurrentHashMap<String, String> kv = entry.getValue();
                        Path json = languageDir.resolve(lang + ".json");
                        com.google.gson.JsonElement jsonTree = GSON.toJsonTree(kv);
                        futures[i] = DataProvider.saveStable(cachedOutput, jsonTree, json);
                        i++;
                    }
                    return CompletableFuture.allOf(futures);
                }
            
                public %%classname%% setTargetLanguage(String targetLanguage) {
                    this.targetLanguage = targetLanguage;
                    return this;
                }
            
                public String getName() {
                    return "Language Setting by " + modid;
                }
            
                public %%classname%% add(Object r, String value) {
                    if (r instanceof String string) {
                        if (!languages.containsKey(targetLanguage)) languages.put(targetLanguage, new ConcurrentHashMap<>());
                        languages.get(targetLanguage).put(string, value);
                        return this;
                    }
                    return switch (r) {
                        case DeferredHolder<?, ?> holder -> add(holder.get(), value);
                        case Supplier<?> supplier -> add(supplier.get(), value);
                        case Item item -> add(item.getDescriptionId(), value);
                        case Block block -> add(block.getDescriptionId(), value);
                        case SoundEvent sound -> add("sound." + sound.getLocation().toString().replace(":", "."), value);
                        case EntityType<?> type -> add(type.getDescriptionId(), value);
                        case TranslatableContents contents -> add(contents.getKey(), value);
                        case MobEffect effect -> add(effect.getDescriptionId(), value);
                        case CreativeModeTab tab -> tab.getDisplayName().getContents() instanceof TranslatableContents contents ?
                            add(contents, value) :
                            this;
                        case ItemStack stack -> add(stack.getDescriptionId(), value);
                        default -> throw new IllegalStateException("Unexpected value: " + r);
                    };
                }
            
                public %%classname%% setModid(String modid) {
                    this.modid = modid;
                    return this;
                }
            
                public void init() {
                    %%init%%;
                }
            }
            """.stripIndent());
    private final String code;

    Codes(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
