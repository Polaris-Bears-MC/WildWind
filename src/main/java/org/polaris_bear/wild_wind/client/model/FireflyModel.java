package org.polaris_bear.wild_wind.client.model;

import net.minecraft.resources.ResourceLocation;
import org.polaris_bear.wild_wind.common.entity.Firefly;
import org.polaris_bear.wild_wind.util.Helpers;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.loading.object.BakedAnimations;
import software.bernie.geckolib.model.GeoModel;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class FireflyModel extends GeoModel<Firefly> {
    /**
     * Returns the resource path for the {@link BakedGeoModel} (model json file) to render based on the provided animatable
     *
     * @param animatable entity
     */
    @Override
    public ResourceLocation getModelResource(Firefly animatable) {
        return Helpers.location("geo/entity/firefly.geo.json");
    }

    /**
     * Returns the resource path for the texture file to render based on the provided animatable
     *
     * @param animatable entiry
     */
    @Override
    public ResourceLocation getTextureResource(Firefly animatable) {
        return Helpers.location("textures/entity/firefly.png");
    }

    /**
     * Returns the resourcepath for the {@link BakedAnimations} (animation json file) to use for animations based on the provided animatable
     *
     * @param animatable entity
     */
    @Override
    public ResourceLocation getAnimationResource(Firefly animatable) {
        return Helpers.location("animations/entity/firefly.animation.json");
    }
}
