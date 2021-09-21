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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class WeaponsGUI implements InventoryHolder {

    private Inventory inv;
    private BloodCoin main;
    public WeaponsGUI(BloodCoin p) {
        main = p;
        inv = Bukkit.createInventory(this, 54, Utils.color("&bWeapons"));
    }

    public void init() {
        for (int i = 0; i<54; i++) {
            inv.setItem(i,Utils.createItem("&r", Material.WHITE_STAINED_GLASS_PANE,null,false));
        }
        inv.setItem(21,Utils.createItem(ChatColor.of("#8B4513") + "Wood", Material.WOODEN_SWORD, getLore(10),true));
        inv.setItem(22, Utils.createItem("&fIron", Material.IRON_SWORD, getLore(30),true));
        inv.setItem(23,Utils.createItem("&bDiamond", Material.DIAMOND_SWORD, getLore(50),true));
        inv.setItem(31, Utils.createItem("&6Gold", Material.GOLDEN_SWORD, getLore(100),true));
        inv.setItem(40, Utils.createItem("&8Netherite", Material.NETHERITE_SWORD, getLore(1000),true));
    }

    private List<String> getLore(int price) {
        List<String>lore = new ArrayList<>();
        lore.add(Utils.color("&7Enchants:"));
        lore.add(Utils.color("&7Sharpness 5"));
        lore.add(Utils.color("&7Unbreaking 3"));
        lore.add(Utils.color("&7Looting 3"));
        lore.add(Utils.color("&7Sweeping Edge 3"));
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
        HashMap<Enchantment, Integer> enchantments = new HashMap<>();

        enchantments.put(Enchantment.DURABILITY, 3);
        enchantments.put(Enchantment.DAMAGE_ALL, 5);
        enchantments.put(Enchantment.LOOT_BONUS_MOBS, 3);
        enchantments.put(Enchantment.SWEEPING_EDGE, 3);

        int price = 0;
        if (mat.equals(Material.WOODEN_SWORD)) {
            price = 10;
        }
        if (mat.equals(Material.IRON_SWORD)) {
            price = 30;
        }
        if (mat.equals(Material.GOLDEN_SWORD)) {
            price = 50;
        }
        if (mat.equals(Material.DIAMOND_SWORD)) {
            price = 100;
        }
        if (mat.equals(Material.NETHERITE_SWORD)) {
            price = 1000;
        }
        if (price == 0) {
            return;
        }

        main.confirmPurchase().purchaseItem(Collections.singletonList(Utils.createArmorItem(mat, enchantments)), price, (Player) e.getWhoClicked());
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
