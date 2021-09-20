package me.dkflab.bloodcoin.managers;

import me.dkflab.bloodcoin.BloodCoin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DataManager {

    private FileConfiguration config;
    private BloodCoin main;
    public DataManager(BloodCoin p) {
        main = p;
        main.saveDefaultConfig();
        config = main.getConfig();
    }

    public void init() {

    }

    public void setPoints(Player p, int i) {
        config.set(p.getUniqueId().toString(), i);
        saveConfig();
    }

    public void addPoints(Player p, int i)  {
        config.set(p.getUniqueId().toString(), getPoints(p)+i);
        saveConfig();
    }

    public void removePoints(Player p, int i) {
        config.set(p.getUniqueId().toString(), getPoints(p)-i);
        saveConfig();
    }

    public int getPoints(Player p) {
        if (config.get(p.getUniqueId().toString()) == null) {
            config.set(p.getUniqueId().toString(), 0);
        }
        saveConfig();
        return (int)config.get(p.getUniqueId().toString());
    }

    public void saveConfig() {
        main.saveConfig();
    }
}
