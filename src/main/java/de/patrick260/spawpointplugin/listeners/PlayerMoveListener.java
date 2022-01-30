package de.patrick260.spawpointplugin.listeners;

import de.patrick260.spawpointplugin.commands.SpawnCommand;
import de.patrick260.spawpointplugin.main.SpawnpointPlugin;
import de.patrick260.spawpointplugin.util.LanguageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final LanguageManager languageManager = SpawnpointPlugin.getPlugin().getLanguageManager();


    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        if (SpawnCommand.isPlayerInQueue(player)) {

            SpawnCommand.removePlayerFromQueue(player);

            player.sendMessage(languageManager.getText("messages.listeners.onPlayerMove.teleportCanceled"));

        }

    }

}
