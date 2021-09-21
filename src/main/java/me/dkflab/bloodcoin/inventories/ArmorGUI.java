package me.dkflab.bloodcoin.inventories;

import me.dkflab.bloodcoin.BloodCoin;
import me.dkflab.bloodcoin.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArmorGUI implements InventoryHolder {

    private Inventory inv;
    private BloodCoin main;
    public ArmorGUI(BloodCoin p) {
        main = p;
        inv = Bukkit.createInventory(this, 54, Utils.color("&bArmor"));
    }

    public void init() {
        for (int i = 0; i<54; i++) {
            inv.setItem(i,Utils.createItem("&r", Material.WHITE_STAINED_GLASS_PANE,null,false));
        }

        inv.setItem(21,Utils.createItem(ChatColor.of("#8B4513") + "Leather", Material.LEATHER_HELMET, getLore(10),true));
        inv.setItem(22, Utils.createItem("&fIron", Material.IRON_HELMET, getLore(30),true));
        inv.setItem(23,Utils.createItem("&bDiamond", Material.DIAMOND_HELMET, getLore(50),true));
        inv.setItem(31, Utils.createItem("&6Gold", Material.GOLDEN_HELMET, getLore(100),true));
        inv.setItem(40, Utils.createItem("&8Netherite", Material.NETHERITE_HELMET, getLore(1000),true));
    }

    private List<String> getLore(int price) {
        List<String>lore = new ArrayList<>();
        lore.add(Utils.color("&7Full set of armor!"));
        lore.add(Utils.color("&7Protection 4"));
        lore.add(Utils.color("&7Unbreaking 3"));
        lore.add(Utils.color("&7Price: &6" + price));
        return lore;
    }

    public void event(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        Material mat = e.getCurrentItem().getType();
        if (mat.equals(Material.WHITE_STAINED_GLASS_PANE)) {
            return;
        }
        Player p = (Player) e.getWhoClicked();
        List<ItemStack> items = new ArrayList<>();
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();
        if (mat.equals(Material.LEATHER_HELMET)) {
            enchantments.put(Enchantment.DURABILITY, 3);
            enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            items.add(Utils.createArmorItem(mat, enchantments));
            items.add(Utils.createArmorItem(Material.LEATHER_CHESTPLATE, enchantments));
            items.add(Utils.createArmorItem(Material.LEATHER_LEGGINGS, enchantments));
            items.add(Utils.createArmorItem(Material.LEATHER_BOOTS, enchantments));
            main.confirmPurchase().purchaseItem(items, 10,p);
        }
        if (mat.equals(Material.IRON_HELMET)) {
            enchantments.put(Enchantment.DURABILITY, 3);
            enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            items.add(Utils.createArmorItem(mat, enchantments));
            items.add(Utils.createArmorItem(Material.IRON_CHESTPLATE, enchantments));
            items.add(Utils.createArmorItem(Material.IRON_LEGGINGS, enchantments));
            items.add(Utils.createArmorItem(Material.IRON_BOOTS, enchantments));
            main.confirmPurchase().purchaseItem(items, 30,p);
        }
        if (mat.equals(Material.GOLDEN_HELMET)) {
            enchantments.put(Enchantment.DURABILITY, 3);
            enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            items.add(Utils.createArmorItem(mat, enchantments));
            items.add(Utils.createArmorItem(Material.GOLDEN_CHESTPLATE, enchantments));
            items.add(Utils.createArmorItem(Material.GOLDEN_LEGGINGS, enchantments));
            items.add(Utils.createArmorItem(Material.GOLDEN_BOOTS, enchantments));
            main.confirmPurchase().purchaseItem(items, 50,p);
        }
        if (mat.equals(Material.DIAMOND_HELMET)) {
            enchantments.put(Enchantment.DURABILITY, 3);
            enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            items.add(Utils.createArmorItem(mat, enchantments));
            items.add(Utils.createArmorItem(Material.DIAMOND_CHESTPLATE, enchantments));
            items.add(Utils.createArmorItem(Material.DIAMOND_LEGGINGS, enchantments));
            items.add(Utils.createArmorItem(Material.DIAMOND_BOOTS, enchantments));
            main.confirmPurchase().purchaseItem(items, 100,p);
        }
        if (mat.equals(Material.NETHERITE_HELMET)) {
            enchantments.put(Enchantment.DURABILITY, 3);
            enchantments.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            items.add(Utils.createArmorItem(mat, enchantments));
            items.add(Utils.createArmorItem(Material.NETHERITE_CHESTPLATE, enchantments));
            items.add(Utils.createArmorItem(Material.NETHERITE_LEGGINGS, enchantments));
            items.add(Utils.createArmorItem(Material.NETHERITE_BOOTS, enchantments));
            main.confirmPurchase().purchaseItem(items, 1000,p);
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
