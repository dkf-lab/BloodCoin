package me.dkflab.bloodcoin.placeholders;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dkflab.bloodcoin.BloodCoin;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CoinAmount extends PlaceholderExpansion {

    private BloodCoin main;

    public CoinAmount(BloodCoin p) {
        main = p;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "bloodcoin";
    }

    @Override
    public @NotNull String getAuthor() {
        return "dkflab";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if (player == null) {
            return "";
        }
        if (params.equalsIgnoreCase("coins")) {
            return String.valueOf(main.getCurrency().getPoints(player));
        }
        return null;
    }
}
