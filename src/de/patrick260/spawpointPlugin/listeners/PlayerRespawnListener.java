package de.patrick260.spawpointPlugin.listeners;

import de.patrick260.spawpointPlugin.main.Main;
import de.patrick260.spawpointPlugin.util.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private FileConfiguration config = Main.getPlugin().getConfig();

    private LanguageManager languageManager = Main.getPlugin().getLanguageManager();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Location location = new Location(Bukkit.getWorld(config.getString("data.spawnpoint.world")), config.getDouble("data.spawnpoint.x"), config.getDouble("data.spawnpoint.y"), config.getDouble("data.spawnpoint.z"), config.getFloat("data.spawnpoint.yaw"), config.getFloat("data.spawnpoint.pitch"));

        event.setRespawnLocation(location);

        char s = languageManager.getText("messages.listeners.onPlayerRespawn.teleportMessage").charAt(0);
        Main.getPlugin().getConsole().sendMessage((int)s + "L");
        event.getPlayer().sendMessage(languageManager.getText("messages.listeners.onPlayerRespawn.teleportMessage"));

    }

}
