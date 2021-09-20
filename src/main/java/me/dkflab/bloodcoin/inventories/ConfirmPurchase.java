package me.dkflab.bloodcoin.inventories;

import me.dkflab.bloodcoin.BloodCoin;
import me.dkflab.bloodcoin.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ConfirmPurchase implements InventoryHolder {

    private HashMap<Player, HashMap<ItemStack, Integer>> purchaseMap = new HashMap<>();
    private HashMap<ItemStack, Integer> itemsMap = new HashMap<>();
    private Inventory inv;
    private BloodCoin main;
    public ConfirmPurchase(BloodCoin p) {
        inv = Bukkit.createInventory(this, 9, Utils.color("Confirm Purchase"));
        main = p;
    }

    public void event(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        Material mat = e.getCurrentItem().getType();
        Player p = (Player) e.getWhoClicked();
        if (mat.equals(Material.GREEN_STAINED_GLASS_PANE)) {
            if (purchaseMap.get(p) == null) {
                Utils.message(p, "&cAn error occurred.");
                return;
            }
            int price = 0;
            for (int i : purchaseMap.get(p).values()) {
                price = i;
                break;
            }
            if (price == 0) {
                Utils.message(p, "&cAn error occurred.");
                return;
            }
            if (!main.getCurrency().canPurchase(p, price)) {
                Utils.message(p, "&cInsufficient funds!");
                return;
            }
            for (ItemStack item : purchaseMap.get(p).keySet()) {
                p.getInventory().addItem(item);
            }
            Utils.message(p, "&aPurchase successful!");
        }
        if (mat.equals(Material.RED_STAINED_GLASS_PANE)) {
            e.getWhoClicked().closeInventory();
            Utils.message(e.getWhoClicked(), "&cPurchase cancelled!");
        }
    }

    private void init(ItemStack it) {
        for (int i = 0; i < 4; i++){
            inv.setItem(i,Utils.createItem("&aAccept", Material.GREEN_STAINED_GLASS_PANE, Collections.singletonList("&7Confirm the purchase"),false));
        }
        for (int i = 5; i < 9; i++){
            inv.setItem(i,Utils.createItem("&cDeny", Material.RED_STAINED_GLASS_PANE, Collections.singletonList("&7Cancel the purchase"),false));
        }
        inv.setItem(4,it);
    }

    public void purchaseItem(List<ItemStack> items, int price, Player p) {
        List<String> lore = new ArrayList<>();
        itemsMap.clear();
        for (ItemStack i : items) {
            lore.add(Utils.color("&71x " + i.getI18NDisplayName()));
            itemsMap.put(i, price);
        }
        purchaseMap.put(p,itemsMap);
        init(Utils.createItem("&fPurchase Summary", Material.BOOK, lore,true));
        p.openInventory(inv);
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
