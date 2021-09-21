package me.dkflab.bloodcoin.inventories;

import me.dkflab.bloodcoin.BloodCoin;
import me.dkflab.bloodcoin.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;

public class StoreScreen implements InventoryHolder {

    private Inventory inv;

    private BloodCoin main;
    public StoreScreen(BloodCoin p) {
        inv = Bukkit.createInventory(this, 54, Utils.color("&cBlood Store"));
        main = p;
    }

    public void init(Player player) {
        for (int i = 0; i<54; i++) {
            inv.setItem(i,Utils.createItem("&r",Material.WHITE_STAINED_GLASS_PANE,null,false));
        }
        inv.setItem(21,Utils.createItem("&bArmor", Material.NETHERITE_HELMET, Collections.singletonList("&7View armor options"),true));
        inv.setItem(23, Utils.createItem("&bWeapons", Material.NETHERITE_SWORD, Collections.singletonList("&7View available weapons"),true));
        inv.setItem(31, Utils.createItem("&cBlood Coins", Material.BOOK, Collections.singletonList("&7You have &c" + main.getCurrency().getPoints(player) + "&7 blood coins!"),true));
    }

    public void event(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        Material mat = e.getCurrentItem().getType();
        if (mat.equals(Material.NETHERITE_HELMET)) {
            main.armorGUI().init();
            e.getWhoClicked().openInventory(main.armorGUI().getInventory());
        }
        if (mat.equals(Material.NETHERITE_SWORD)) {
            main.weaponsGUI().init();
            e.getWhoClicked().openInventory(main.weaponsGUI().getInventory());
        }
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }
}
