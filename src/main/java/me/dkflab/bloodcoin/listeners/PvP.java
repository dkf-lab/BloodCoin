package me.dkflab.bloodcoin.listeners;

import me.dkflab.bloodcoin.BloodCoin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PvP implements Listener {

    private BloodCoin main;
    public PvP(BloodCoin p) {
        main = p;
    }

    @EventHandler
    public void onKill(EntityDamageByEntityEvent e) {
        if (e.getEntityType().equals(EntityType.PLAYER)) {
            if (e.getDamager().getType() == EntityType.PLAYER) {
                if (((Player)e.getEntity()).getHealth() <= 0) {
                    main.getCurrency().addPoints((Player) e.getDamager(), 1);
                }
            }
        }
    }
}
