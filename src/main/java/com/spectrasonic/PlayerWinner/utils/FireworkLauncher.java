package com.spectrasonic.PlayerWinner.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class FireworkLauncher {

    private static final List<Color> FIREWORK_COLORS = Arrays.asList(
            Color.AQUA,
            Color.RED,
            Color.YELLOW,
            Color.LIME,
            Color.PURPLE
    );

    public static void launchFireworks(Player player, JavaPlugin plugin) {
        Location loc = player.getLocation();
        World world = player.getWorld();

        // Schedule 5 fireworks, each firing every 10 ticks (0.5 seconds)
        new BukkitRunnable() {
            int fireworkCount = 0;

            @Override
            public void run() {
                if (fireworkCount >= 5) {
                    cancel();  // Stop after 5 fireworks
                    return;
                }

                Firework firework = world.spawn(loc, Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();

                // Customize firework with different colors for each launch
                FireworkEffect effect = FireworkEffect.builder()
                        .withColor(FIREWORK_COLORS.get(fireworkCount))
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .trail(true)
                        .flicker(true)
                        .build();
                meta.addEffect(effect);
                meta.setPower(1); // Set flight duration to 1

                firework.setFireworkMeta(meta);

                fireworkCount++;
            }
        }.runTaskTimer(plugin, 0, 10); // 10 ticks = 0.5 seconds
    }
}
