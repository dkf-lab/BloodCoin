package me.dkflab.bloodcoin.inventories;

import me.dkflab.bloodcoin.BloodCoin;
import me.dkflab.bloodcoin.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collections;

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
        inv.setItem(21,Utils.createItem("&bArmor", Material.NETHERITE_HELMET, Collections.singletonList("&7View armor options"),true));
        inv.setItem(23, Utils.createItem("&bWeapons", Material.NETHERITE_SWORD, Collections.singletonList("&7View available weapons &cIN PROGRESS"),true));
        inv.setItem(31, Utils.createItem("&cBlood Coins", Material.BOOK, Collections.singletonList("&7You have " + "&c blood coins&7!"),true));
    }

    public void event(InventoryClickEvent e) {

    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
