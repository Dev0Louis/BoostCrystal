package dev.louis.chainendcrystals;

import dev.louis.chainendcrystals.entity.BoostCrystalEntity;
import dev.louis.chainendcrystals.entity.BoostCrystalEntityRenderer;
import dev.louis.chainendcrystals.item.BoostCrystalItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Rarity;

public class BoostCrystal implements ModInitializer {
    public static final Item BOOST_CRYSTAL_ITEM = Items.register("boostcrystal:boost_crystal", new BoostCrystalItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));
    public static final EntityType<BoostCrystalEntity> BOOST_CRYSTAL_ENTITY = register("boostcrystal:boost_crystal", EntityType.Builder.<BoostCrystalEntity>create(BoostCrystalEntity::new, SpawnGroup.MISC).setDimensions(2.0f, 2.0f).maxTrackingRange(16).trackingTickInterval(Integer.MAX_VALUE));
    //public static final Item USED_BOOST_CRYSTAL_ITEM = Items.register("used_boost_crystal", (Item)new UsedBoostCrystalItem(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));

    @Override
    public void onInitialize() {
        //EntityRenderers.register(EntityType.END_CRYSTAL, EndCrystalEntityRenderer::new);
        EntityRendererRegistry.register(BOOST_CRYSTAL_ENTITY, BoostCrystalEntityRenderer::new);
    }

    private static <T extends BoostCrystalEntity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return Registry.register(Registries.ENTITY_TYPE, id, type.build(id));
    }


}
