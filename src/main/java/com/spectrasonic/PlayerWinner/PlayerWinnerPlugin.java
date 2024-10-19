package com.spectrasonic.PlayerWinner;

import org.bukkit.plugin.java.JavaPlugin;
import com.spectrasonic.PlayerWinner.commands.WinnerCommand;
import org.bukkit.ChatColor;

import java.util.Objects;

public class PlayerWinnerPlugin extends JavaPlugin {

    private static final String divider = "------------------------------";
    private static final String prefix = ChatColor.AQUA + "[PlayerWinner]" + ChatColor.RESET + " ";

    public final String pluginVersion = getDescription().getVersion();
    public final String pluginName = getDescription().getName();
    public final String pluginAuthor = getDescription().getAuthors().toString();

    @Override
    public void onEnable() {
        // Register the /winner command and its alias /win
        Objects.requireNonNull(this.getCommand("winner")).setExecutor(new WinnerCommand(this));
        Objects.requireNonNull(this.getCommand("winner")).setTabCompleter(new WinnerCommand(this));

        getServer().getConsoleSender().sendMessage(divider);
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.WHITE + pluginName + ChatColor.GREEN + " Plugin Enabled!");
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.LIGHT_PURPLE + "Version: " + ChatColor.AQUA + pluginVersion);
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.GOLD + "Developed by " + ChatColor.RED + pluginAuthor);
        getServer().getConsoleSender().sendMessage(divider);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(divider);
        getServer().getConsoleSender().sendMessage(prefix + ChatColor.RED + pluginName + "plugin Disabled!");
        getServer().getConsoleSender().sendMessage(divider);

    }
}
