package com.spectrasonic.PlayerWinner.utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class FireworkLauncher {

    // Define a list of colors for the fireworks
    private static final List<Color> FIREWORK_COLORS = Arrays.asList(
            Color.AQUA,
            Color.RED,
            Color.YELLOW,
            Color.LIME,
            Color.PURPLE
    );

    // Method to launch 5 fireworks over time and apply glowing effect
    public static void launchFireworks(Player player, JavaPlugin plugin) {
        World world = player.getWorld();

        // Apply glowing effect in GOLD color
        player.setGlowing(true);

        // Schedule 5 fireworks to launch every 10 ticks (0.5 seconds)
        new BukkitRunnable() {
            int fireworkCount = 0;

            @Override
            public void run() {
                if (fireworkCount >= 5) {
                    // After 5 fireworks, remove the glowing effect and stop the task
                    player.setGlowing(false);
                    cancel();
                    return;
                }

                // Get the player's current position
                Location loc = player.getLocation();

                // Create and launch a firework at the player's current position
                Firework firework = world.spawn(loc, Firework.class);
                FireworkMeta meta = firework.getFireworkMeta();

                // Set firework color and effects
                FireworkEffect effect = FireworkEffect.builder()
                        .withColor(FIREWORK_COLORS.get(fireworkCount))
                        .with(FireworkEffect.Type.BALL_LARGE)
                        .trail(true)
                        .flicker(true)
                        .build();
                meta.addEffect(effect);
                meta.setPower(1);  // Firework flight duration of 1

                firework.setFireworkMeta(meta);

                fireworkCount++;
            }
        }.runTaskTimer(plugin, 0, 10); // 10 ticks = 0.5 seconds
    }
}
