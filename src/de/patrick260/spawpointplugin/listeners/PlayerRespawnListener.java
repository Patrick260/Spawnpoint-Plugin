package de.patrick260.spawpointplugin.listeners;

import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import de.patrick260.spawpointplugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (config.getBoolean("settings.teleportOnDeath")) {

            Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), (float) config.getDouble("data.spawnpoint.yaw"), (float) config.getDouble("data.spawnpoint.pitch"));

            event.setRespawnLocation(location);

            event.getPlayer().sendMessage(languageManager.getText("messages.listeners.onPlayerRespawn.teleportMessage"));

        }

    }

}
