package de.patrick260.spawpointplugin.listeners;

import de.patrick260.spawpointplugin.commands.SpawnCommand;
import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import de.patrick260.spawpointplugin.util.LanguageManager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();


    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {

        final FileConfiguration config = SpawnpointPlugin.getPlugin().getConfig();

        if (config.getBoolean("settings.cancelTeleportOnMove.on")) {

            final Player player = event.getPlayer();

            Location oldLocation = SpawnCommand.getOldPlayerLocation(player);

            if (SpawnCommand.isPlayerInQueue(player) && ((oldLocation.getX() - event.getTo().getX()) >= config.getDouble("settings.cancelTeleportOnMove.minMoved.x")
                                                            || (oldLocation.getX() - event.getTo().getX()) <= (config.getDouble("settings.cancelTeleportOnMove.minMoved.x")
                                                                - config.getDouble("settings.cancelTeleportOnMove.minMoved.x") * 2)
                                                            || (oldLocation.getZ() - event.getTo().getZ()) >= config.getDouble("settings.cancelTeleportOnMove.minMoved.z")
                                                            || (oldLocation.getZ() - event.getTo().getZ()) <= (config.getDouble("settings.cancelTeleportOnMove.minMoved.z")
                                                                - config.getDouble("settings.cancelTeleportOnMove.minMoved.z") * 2)
                                                            || (oldLocation.getY() - event.getTo().getY()) >= config.getDouble("settings.cancelTeleportOnMove.minMoved.y")
                                                            || (oldLocation.getY() - event.getTo().getY()) <= (config.getDouble("settings.cancelTeleportOnMove.minMoved.y")
                                                                - config.getDouble("settings.cancelTeleportOnMove.minMoved.y") * 2)
                                                        )) {

                SpawnCommand.removePlayerFromQueue(player);

                player.sendMessage(languageManager.getText("messages.listeners.onPlayerMove.teleportCanceled"));

            }

        }

    }

}
