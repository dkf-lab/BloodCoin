package me.dkflab.bloodcoin.listeners;

import me.dkflab.bloodcoin.BloodCoin;
import me.dkflab.bloodcoin.inventories.ArmorGUI;
import me.dkflab.bloodcoin.inventories.ConfirmPurchase;
import me.dkflab.bloodcoin.inventories.StoreScreen;
import me.dkflab.bloodcoin.inventories.WeaponsGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUI implements Listener {

    private BloodCoin main;
    public GUI(BloodCoin p) {
        main = p;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            return;
        }
        if (e.getClickedInventory().getHolder() instanceof StoreScreen) {
            e.setCancelled(true);
            main.storeScreen().event(e);
        }
        if (e.getClickedInventory().getHolder() instanceof ConfirmPurchase) {
            e.setCancelled(true);
            main.confirmPurchase().event(e);
        }
        if (e.getClickedInventory().getHolder() instanceof WeaponsGUI) {
            e.setCancelled(true);
            main.weaponsGUI().event(e);
        }
        if (e.getClickedInventory().getHolder() instanceof ArmorGUI) {
            e.setCancelled(true);
            main.armorGUI().event(e);
        }
    }
}
