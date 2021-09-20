package me.dkflab.bloodcoin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&',s);
    }

    public static void message(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public static ItemStack createItem(String name, Material mat, List<String> lore, boolean enchanted) {
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(color(name));
        if (lore != null) {
            List<String> list = new ArrayList<>();
            for (String s : lore) {
                list.add(color(s));
            }
            meta.setLore(list);
        }
        if (enchanted) {
            meta.addEnchant(Enchantment.LUCK, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createArmorItem(Material m, HashMap<Enchantment, Integer> enchantments) {
        ItemStack item = new ItemStack(m, 1);
        ItemMeta meta = item.getItemMeta();
        //
        for (Enchantment e : enchantments.keySet()) {
            meta.addEnchant(e, enchantments.get(e), true);
        }
        //
        item.setItemMeta(meta);
        return item;
    }
}
