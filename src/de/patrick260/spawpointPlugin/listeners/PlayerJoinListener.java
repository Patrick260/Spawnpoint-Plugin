package de.patrick260.spawpointPlugin.listeners;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private FileConfiguration config;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        config = Main.getPlugin().getConfig();

        if (config.getBoolean("settings.teleportOnJoin")) {

            Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), (float) config.getDouble("data.spawnpoint.yaw"), (float) config.getDouble("data.spawnpoint.pitch"));

            event.getPlayer().teleport(location);

        }

    }

}
