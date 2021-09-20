package me.dkflab.bloodcoin.managers;

import me.dkflab.bloodcoin.BloodCoin;
import org.bukkit.entity.Player;

public class CurrencyManager {

    private BloodCoin main;

    public CurrencyManager(BloodCoin p) {
        main = p;
    }

    public void addPoints(Player p, int points) {
        main.data().addPoints(p, points);
    }

    public int getPoints(Player p) {
        return main.data().getPoints(p);
    }

    public void removePoints(Player p, int points) {
        main.data().removePoints(p,points);
    }

    public boolean canPurchase(Player p, int points) {
        if (getPoints(p) < points) {
            return false;
        }
        removePoints(p, points);
        return true;
    }
}
