package me.dkflab.bloodcoin;

import me.dkflab.bloodcoin.inventories.ArmorGUI;
import me.dkflab.bloodcoin.inventories.ConfirmPurchase;
import me.dkflab.bloodcoin.inventories.StoreScreen;
import me.dkflab.bloodcoin.inventories.WeaponsGUI;
import me.dkflab.bloodcoin.listeners.GUI;
import me.dkflab.bloodcoin.listeners.PvP;
import me.dkflab.bloodcoin.managers.CurrencyManager;
import me.dkflab.bloodcoin.managers.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class BloodCoin extends JavaPlugin implements CommandExecutor {

    private StoreScreen storeScreen;
    private ConfirmPurchase confirmPurchase;
    private CurrencyManager currency;
    private ArmorGUI armorGUI;
    private WeaponsGUI weaponsGUI;
    private DataManager dataManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        storeScreen = new StoreScreen(this);
        confirmPurchase = new ConfirmPurchase(this);
        currency = new CurrencyManager(this);
        armorGUI = new ArmorGUI(this);
        weaponsGUI = new WeaponsGUI(this);
        dataManager = new DataManager(this);
        getServer().getPluginManager().registerEvents(new GUI(this),this);
        getServer().getPluginManager().registerEvents(new PvP(this),this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("bloodshop")) {
            if (!(sender instanceof Player)) {
                Utils.message(sender, "&cYou need to be a player to execute this command.");
            }
            Player p = (Player)sender;
            storeScreen.init(p);
            p.openInventory(storeScreen.getInventory());
        }
        return true;
    }

    public CurrencyManager getCurrency() {
        return this.currency;
    }

    public StoreScreen storeScreen() {
        return this.storeScreen;
    }

    public ConfirmPurchase confirmPurchase() {
        return this.confirmPurchase;
    }

    public ArmorGUI armorGUI() {
        return this.armorGUI;
    }

    public WeaponsGUI weaponsGUI() {
        return this.weaponsGUI;
    }

    public DataManager data() {
        return this.dataManager;
    }
}
