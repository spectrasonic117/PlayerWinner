package com.spectrasonic.PlayerWinner.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabCompleterUtil {

    public static List<String> getOnlinePlayerNames(String prefix) {
        List<String> playerNames = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                playerNames.add(player.getName());
            }
        }
        return playerNames;
    }
}
