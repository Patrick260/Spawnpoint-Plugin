package de.patrick260.spawpointPlugin.listeners;

import de.patrick260.spawpointPlugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    private FileConfiguration config = Main.getPlugin().getConfig();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), config.getFloat("data.spawnpoint.yaw"), config.getFloat("data.spawnpoint.pitch"));

        event.getEntity().teleport(location);

        event.getEntity().sendMessage("§cDu bist gestorben und wurdest deswegen zum Spawn teleportiert!");

    }

}
