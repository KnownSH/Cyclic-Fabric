package net.knsh.cyclic.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.item.GemstoneItem;
import net.knsh.cyclic.item.datacard.EntityDataCard;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class CyclicItems {
    public static Item GEM_AMBER = registerItem("gem_amber", new GemstoneItem(new FabricItemSettings()));
    public static Item ENTITY_DATA = registerItem("entity_data", new EntityDataCard(new FabricItemSettings()));

    private static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Cyclic.MOD_ID, id), item);
    }

    public static void register() {}
}
