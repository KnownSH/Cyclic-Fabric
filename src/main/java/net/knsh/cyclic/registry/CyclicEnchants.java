package net.knsh.cyclic.registry;

import net.knsh.cyclic.Cyclic;
import net.knsh.cyclic.enchant.AutoSmeltEnchant;
import net.knsh.cyclic.enchant.ReachEnchant;
import net.knsh.cyclic.enchant.TravellerEnchant;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class CyclicEnchants {
    public static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET };

    public static Enchantment TRAVELLER = registerEnchant("traveler", new TravellerEnchant());
    public static Enchantment AUTOSMELT = registerEnchant("auto_smelt", new AutoSmeltEnchant());
    public static Enchantment REACH = registerEnchant("reach", new ReachEnchant());

    public static void register() {}

    private static Enchantment registerEnchant(String id, Enchantment enchant) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT, new ResourceLocation(Cyclic.MOD_ID, id), enchant);
    }
}