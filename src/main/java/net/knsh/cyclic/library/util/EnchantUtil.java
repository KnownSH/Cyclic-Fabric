package net.knsh.cyclic.library.util;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantUtil {
    public static int getCurrentLevelSlot(LivingEntity player, EquipmentSlot type, Enchantment enchant) {
        ItemStack armor = player.getItemBySlot(type);
        int level = 0;
        if (!armor.isEmpty()) {
            EnchantmentHelper.getEnchantments(armor);
            if (EnchantmentHelper.getEnchantments(armor).containsKey(enchant)) {
                level = EnchantmentHelper.getEnchantments(armor).get(enchant);
            }
        }
        return level;
    }
}
