package com.spectrasonic.PlayerWinner.commands;

import com.spectrasonic.PlayerWinner.utils.FireworkLauncher;
import com.spectrasonic.PlayerWinner.utils.TabCompleterUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class WinnerCommand implements CommandExecutor, TabCompleter {

    private final JavaPlugin plugin;

    public WinnerCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("version")) {
            sender.sendMessage(ChatColor.GOLD + "PlayerWinner version: "+ ChatColor.LIGHT_PURPLE + plugin.getDescription().getVersion());
            sender.sendMessage(ChatColor.GOLD + "Developed by: " + ChatColor.RED + plugin.getDescription().getAuthors());
            return true;
        }

        if (!(sender instanceof Player) || !sender.isOp()) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.YELLOW + "Usage: /winner <player>");
            return false;
        }

        Player winner = Bukkit.getPlayerExact(args[0]);
        if (winner == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        // Send title to all players
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendTitle(
                    ChatColor.GOLD + "\uD83C\uDF1F Ganador \uD83C\uDF1F",
                    ChatColor.AQUA + winner.getName(),
                    10, 70, 20
            );
        }

        // Launch fireworks for the winner
        FireworkLauncher.launchFireworks(winner, plugin);

        // Send chat message
        Bukkit.broadcastMessage(ChatColor.AQUA + "El Jugador " + ChatColor.GOLD + winner.getName() + ChatColor.AQUA + " ha ganado!");

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            return TabCompleterUtil.getOnlinePlayerNames(args[0]);
        }
        return null;
    }
}
